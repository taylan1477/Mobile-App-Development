package com.taylanozgurozdemir.birinciquizuygulamasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.taylanozgurozdemir.birinciquizuygulamasi.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Giriş butonuna tıklama olayı
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            // Kullanıcı adı ve şifre kontrolü
            if (username == "taylan" && password == "12345") {
                // Başarılı giriş
                Toast.makeText(requireContext(), "Giriş başarılı!", Toast.LENGTH_SHORT).show()
            } else {
                // Başarısız giriş
                Toast.makeText(requireContext(), "Giriş başarısız! Kullanıcı adı veya şifre hatalı.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

