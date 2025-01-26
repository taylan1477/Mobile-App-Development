package com.taylanozgurozdemir.yemektariflerim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.taylanozgurozdemir.yemektariflerim.databinding.FragmentLoginBinding

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

                // Yemek listesine geçiş yap
                findNavController().navigate(R.id.action_loginFragment_to_yemekListFragment)
            } else {
                // Başarısız giriş
                Toast.makeText(requireContext(), "Giriş başarısız! Kullanıcı adı veya şifre hatalı.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}