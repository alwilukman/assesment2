package com.example.org.orgd3if0074tubes1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.org.orgd3if0074tubes1.R
import com.example.org.orgd3if0074tubes1.databinding.FragmentSaranBinding
import com.example.org.orgd3if0074tubes1.model.KategoriPerhitungan


class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(args.kategori)
    }
    private fun updateUI(kategoriPerhitungan: KategoriPerhitungan){
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when(kategoriPerhitungan){
            KategoriPerhitungan.PERSEGI -> {
                actionBar?.title = "Persegi"
                binding.textView.text = "Persegi"
                binding.imageView.setImageResource(R.drawable.kubus)
            }
            KategoriPerhitungan.PERSEGIPANJANG -> {
                actionBar?.title = "Persegi Panjang"
                binding.textView.text = "Persegi Panjang"
                binding.imageView.setImageResource(R.drawable.panjang)
            }
        }
    }
}