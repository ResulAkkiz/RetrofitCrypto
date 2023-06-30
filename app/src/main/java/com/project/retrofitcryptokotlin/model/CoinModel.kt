package com.project.retrofitcryptokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoinModel {
    @SerializedName("asset_id_base")
    @Expose
    var assetIdBase: String? = null

    @SerializedName("rates")
    @Expose
    var rates: List<Rate>? = null
}