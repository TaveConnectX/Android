package com.example.taveconnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taveconnect.R
import com.example.taveconnect.RankProfile
import com.example.taveconnect.databinding.ItemRankListBinding

class CustomAdapter(private val profileList: ArrayList<RankProfile>) :
        RecyclerView.Adapter<CustomAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CustomAdapter.Holder {
        val binding = ItemRankListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(viewHolder: CustomAdapter.Holder, position: Int) {
        viewHolder.rank.text = profileList[position].rank.toString()
        viewHolder.name.text = profileList[position].name

    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    // viewBinding으로 받아올 binding
    inner class Holder(val binding: ItemRankListBinding) : RecyclerView.ViewHolder(binding.root) {
        val rank = binding.tvRankInt
        val name = binding.itemTvNameRank
    }
}

