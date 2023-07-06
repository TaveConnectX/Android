package com.example.taveconnect.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taveconnect.databinding.ItemRankListBinding
import com.example.taveconnect.rank.RankData

class CustomAdapter(private val profileList: ArrayList<RankData>) :
        RecyclerView.Adapter<CustomAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val binding = ItemRankListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(viewHolder: CustomAdapter.Holder, position: Int) {
        viewHolder.rank.text = profileList[position].rank.toString()
        viewHolder.name.text = profileList[position].name
    }

    override fun getItemCount(): Int = profileList.size

    // viewBinding으로 받아올 binding
    inner class Holder(val binding: ItemRankListBinding) : RecyclerView.ViewHolder(binding.root) {
        val rank = binding.tvRankInt
        val name = binding.itemTvNameRank
    }

}

