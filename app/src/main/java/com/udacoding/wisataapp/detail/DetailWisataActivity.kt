package com.udacoding.wisataapp.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.udacoding.wisataapp.R
import kotlinx.android.synthetic.main.activity_detail_wisata.*

class DetailWisataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)

        val nama = intent.getStringExtra("jdl")
        val desk = intent.getStringExtra("desk")
        val img = intent.getStringExtra("img")

        jdlDetail.text = nama
        deskDetail.text = desk

        Glide.with(this).load(img).into(imgDettail)


    }
}