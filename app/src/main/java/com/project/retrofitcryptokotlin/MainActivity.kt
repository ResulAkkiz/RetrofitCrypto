package com.project.retrofitcryptokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.project.retrofitcryptokotlin.adapter.CoinRecyclerViewAdapter
import com.project.retrofitcryptokotlin.databinding.ActivityMainBinding
import com.project.retrofitcryptokotlin.model.CoinModel
import com.project.retrofitcryptokotlin.model.Rate
import com.project.retrofitcryptokotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val baseURL: String = "https://rest.coinapi.io/v1/"
    private var cryptoList = ArrayList<Rate>()
    private var colorList: Array<String>? =null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadData()
        colorList=resources.getStringArray(R.array.colors)
        binding.coinRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun loadData() {
        val retrofit =
            Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<CoinModel> {
            override fun onResponse(call: Call<CoinModel>, response: Response<CoinModel>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        if (body.rates != null)
                            for (singleRate in body.rates!!) {

                                cryptoList.add(singleRate)
                            }
                    }

                }

                binding.coinRecyclerView.adapter = CoinRecyclerViewAdapter(cryptoList,colorList!!,object :CoinRecyclerViewAdapter.Listener{
                    override fun onClick(rate: Rate) {
                        Toast.makeText(this@MainActivity,rate.assetIdQuote!!,Toast.LENGTH_SHORT).show()
                    }

                })
            }

            override fun onFailure(call: Call<CoinModel>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }


}


