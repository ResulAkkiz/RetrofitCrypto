package com.project.retrofitcryptokotlin.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.retrofitcryptokotlin.R
import com.project.retrofitcryptokotlin.model.Rate

class CoinRecyclerViewAdapter(private val rateList:ArrayList<Rate>, private val colorList:Array<String>,var listener:Listener) : RecyclerView.Adapter<CoinRecyclerViewAdapter.CoinViewHolder>() {
    interface Listener{
        fun onClick(rate: Rate)
    }

    inner class CoinViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bind(rate:Rate,colorHEX:String){
            itemView.findViewById<TextView>(R.id.rateTextView).text=rate.rate.toString()
            itemView.findViewById<TextView>(R.id.coinNameTextView).text=rate.assetIdQuote.toString()
            itemView.setBackgroundColor(Color.parseColor(colorHEX))
            itemView.setOnClickListener {
                listener.onClick(rate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.coin_single_row,parent,false)

        return CoinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(rateList[position],colorList[position%10])
    }
}