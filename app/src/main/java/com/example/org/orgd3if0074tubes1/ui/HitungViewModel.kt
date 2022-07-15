package com.example.org.orgd3if0074tubes1.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.org.orgd3if0074tubes1.db.BmiDao
import com.example.org.orgd3if0074tubes1.db.BmiEntity
import com.example.org.orgd3if0074tubes1.model.HasilHitungan
import com.example.org.orgd3if0074tubes1.model.KategoriPerhitungan
import com.example.org.orgd3if0074tubes1.model.PengObjekkan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HitungViewModel(private val db: BmiDao) : ViewModel() {
    private val HasilHitungan = MutableLiveData<HasilHitungan?>()
    private val navigasi = MutableLiveData<KategoriPerhitungan?>()

    fun PengObjekkan(Panjang : Float, Lebar : Float){
        val data = BmiEntity(
            Panjang = Panjang,
            Lebar = Lebar
        )
        HasilHitungan.value = data.PengObjekkan()

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insert(data)
            }
        }
    }
    fun getHasilPerhitungan() : LiveData<HasilHitungan?> = HasilHitungan
    fun mulaiNavigasi(){
        navigasi.value = getHasilPerhitungan().value?.kategori
    }
    fun selesaiNavigasi(){
        navigasi.value = null
    }
    fun getNavigasi() : LiveData<KategoriPerhitungan?> = navigasi
}