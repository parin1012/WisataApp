package com.udacoding.wisataapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.udacoding.wisataapp.adapter.WisataAdapter
import com.udacoding.wisataapp.model.ResponseServer
import com.udacoding.wisataapp.model.Wisata
import com.udacoding.wisataapp.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isConnect()) {


            ConfigNetwork.getRetrofit().getDataWisata().enqueue(object : Callback<ResponseServer> {
                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    Log.d("Eror server", t.message)
                    progress.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    Log.d("response server", response.message())
                    // cek respon server
                    if (response.isSuccessful) {
                        progress.visibility = View.GONE
                        val status = response.body()?.status_code
                        if (status == 200) {
                            val data = response.body()?.data
                            showData(data)
                        }
                    }
                }

            })

        }else {
            progress.visibility = View.GONE
            Toast.makeText(this,"Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
        }
    }

    fun isConnect() : Boolean{
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showData(data: ArrayList<Wisata>?) {
       listWisata.adapter= WisataAdapter(data)
    }
}