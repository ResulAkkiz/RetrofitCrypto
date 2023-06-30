package com.project.retrofitcryptokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rate {
    @SerializedName("time")
    @Expose
    var time: String? = null

    @SerializedName("asset_id_quote")
    @Expose
    var assetIdQuote: String? = null

    @SerializedName("rate")
    @Expose
    var rate: Double? = null
}