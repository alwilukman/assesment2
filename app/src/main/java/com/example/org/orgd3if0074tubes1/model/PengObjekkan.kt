package com.example.org.orgd3if0074tubes1.model

import com.example.org.orgd3if0074tubes1.db.BmiEntity

fun BmiEntity.PengObjekkan(): HasilHitungan {
    val panjang = Panjang
    val lebar = Lebar
    val hasil = Panjang * Lebar

    val kategori =
        if(panjang == lebar){
        KategoriPerhitungan.PERSEGI
        }
        else
            KategoriPerhitungan.PERSEGIPANJANG
    return HasilHitungan(hasil, kategori)
}