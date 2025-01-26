package com.taylanozgurozdemir.finalsinavim

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class EvEkleActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private var imageUri: Uri? = null
    private lateinit var imageView: ImageView
    private lateinit var saveButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ev_ekle)

        // Firestore ve Storage başlat
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        // Görünüm öğelerini bağla
        imageView = findViewById(R.id.imageView)
        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val sizeEditText = findViewById<EditText>(R.id.sizeEditText)
        val roomCountEditText = findViewById<EditText>(R.id.roomCountEditText)
        val priceEditText = findViewById<EditText>(R.id.priceEditText)
        val cityEditText = findViewById<EditText>(R.id.cityEditText)
        saveButton = findViewById(R.id.saveButton)

        // Resim seçmek için tıklama
        imageView.setOnClickListener {
            checkPermissionAndPickImage()
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val size = sizeEditText.text.toString()
            val roomCount = roomCountEditText.text.toString()
            val price = priceEditText.text.toString()
            val city = cityEditText.text.toString()

            if (title.isNotEmpty() && size.isNotEmpty() && roomCount.isNotEmpty() && price.isNotEmpty() && city.isNotEmpty() && imageUri != null) {
                // Save button'u devre dışı bırak
                saveButton.isEnabled = false
                uploadImageAndSaveData(title, size, roomCount, price, city)
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun ve bir resim seçin.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermissionAndPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 ve üstü
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                pickImage()
            }
        } else { // Android 12 ve altı
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                pickImage()
            }
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun uploadImageAndSaveData(title: String, size: String, roomCount: String, price: String, city: String) {
        val filename = UUID.randomUUID().toString()
        val storageRef = storage.reference.child("house_images/$filename")

        imageUri?.let {
            storageRef.putFile(it)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        saveHouseToFirestore(uri.toString(), title, size, roomCount, price, city)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Resim yüklenirken hata: ${e.message}", Toast.LENGTH_SHORT).show()
                    saveButton.isEnabled = true
                }
        }
    }

    private fun saveHouseToFirestore(
        imageUrl: String,
        title: String,
        size: String,
        roomCount: String,
        price: String,
        city: String
    ) {
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email ?: "Bilinmeyen Kullanıcı"

        val house = hashMapOf(
            "imageUrl" to imageUrl,
            "title" to title,
            "size" to size,
            "roomCount" to roomCount,
            "price" to price,
            "city" to city,
            "createdBy" to currentUserEmail // Kullanıcının emailini ekliyoruz
        )

        firestore.collection("houses")
            .add(house)
            .addOnSuccessListener {
                Toast.makeText(this, "Ev ilanı başarıyla eklendi.", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Ev ilanı eklenirken hata: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                imageUri = uri
                // URI izinlerini al
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                imageView.setImageURI(uri)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage()
            } else {
                Toast.makeText(
                    this,
                    "Galeriden resim seçmek için izin vermelisiniz.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_REQUEST_CODE = 2000
    }
}
