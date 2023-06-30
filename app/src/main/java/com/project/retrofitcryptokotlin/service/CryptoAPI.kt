package com.project.retrofitcryptokotlin.service

import com.project.retrofitcryptokotlin.model.CoinModel
import com.project.retrofitcryptokotlin.model.Rate
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    @GET("exchangerate/USD?apikey=45C1FDED-8994-4FFD-AF59-70D6024F9564")
    fun getData():Observable<CoinModel>
}