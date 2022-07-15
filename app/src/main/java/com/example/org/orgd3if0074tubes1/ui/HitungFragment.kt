package com.example.org.orgd3if0074tubes1.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.org.orgd3if0074tubes1.R
import com.example.org.orgd3if0074tubes1.databinding.FragmentHitungBinding
import com.example.org.orgd3if0074tubes1.db.BmiDb
import com.example.org.orgd3if0074tubes1.model.HasilHitungan
import com.example.org.orgd3if0074tubes1.model.KategoriPerhitungan
import com.example.org.orgd3if0074tubes1.ui.hitung.HitungViewModelFactory

class HitungFragment : Fragment()  {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = BmiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    (R.id.action_hitungFragment_to_historiFragment)
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            PengObjekkan()
        }
        binding.saranButton.setOnClickListener { viewModel.mulaiNavigasi() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilPerhitungan().observe(requireActivity(), {showResult(it)})
        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if(it == null) return@observe
            findNavController().navigate(HitungFragmentDirections
                .actionHitungFragmentToSaranFragment(it))
            viewModel.selesaiNavigasi()
        })
    }
    private fun shareData(){
        val message = getString(R.string.bagikan_template,
        binding.panjang.text, binding.lebar.text, binding.txtLuas.text, binding.textView3.text)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun PengObjekkan() {
        val panjang = binding.panjang.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, "Panjang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return

        }
        val lebar = binding.lebar.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, "Lebar Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            return
        }

        viewModel.PengObjekkan(
            panjang.toFloat(),
            lebar.toFloat()
        )
    }
    private fun getKategori(kategoriPerhitungan: KategoriPerhitungan) : String{
        val stringRes = when (kategoriPerhitungan) {
            KategoriPerhitungan.PERSEGI -> R.string.persegi
            KategoriPerhitungan.PERSEGIPANJANG -> R.string.persegi_Panjang
        }
        return getString(stringRes)

    }

    private fun showResult(result: HasilHitungan?){
        if(result == null)return
        else
            binding.txtLuas.text = getString(R.string.luas_x, result.jumlah)
            binding.textView3.text = getString(R.string.kategori_x, getKategori(result.kategori))
            binding.buttonGroup.visibility = View.VISIBLE
    }


}