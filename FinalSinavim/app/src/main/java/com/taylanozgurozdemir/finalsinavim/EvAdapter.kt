import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.taylanozgurozdemir.finalsinavim.R

class EvAdapter(private val evListesi: List<Ev>) : RecyclerView.Adapter<EvAdapter.EvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ev, parent, false)
        return EvViewHolder(view)
    }

    override fun onBindViewHolder(holder: EvViewHolder, position: Int) {
        val ev = evListesi[position]
        holder.bind(ev)
    }

    override fun getItemCount(): Int = evListesi.size

    class EvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val createdByText: TextView = itemView.findViewById(R.id.textView1)
        private val evImageView: ImageView = itemView.findViewById(R.id.imageView3)
        private val baslikText: TextView = itemView.findViewById(R.id.textView2)
        private val metrekareText: TextView = itemView.findViewById(R.id.textView3)
        private val odaSayisiText: TextView = itemView.findViewById(R.id.textView4)
        private val fiyatText: TextView = itemView.findViewById(R.id.textView5)
        private val sehirText: TextView = itemView.findViewById(R.id.textView6)

        @SuppressLint("SetTextI18n")
        fun bind(ev: Ev) {

            // Picasso kullanarak resim yükleme
            Picasso.get()
                .load(ev.imageUrl) // Görselin URL'sini yükle
                .into(evImageView)

            // Diğer bilgiler
            baslikText.text = ev.title
            metrekareText.text = "${ev.size} Metrekare"
            odaSayisiText.text = ev.roomCount
            fiyatText.text = "${ev.price} TL"
            sehirText.text = ev.city

            // İlanı oluşturan kullanıcının adını göster
            createdByText.text = "İlan Sahibi: ${ev.createdBy.substringBefore("@")}"
        }
    }
}
