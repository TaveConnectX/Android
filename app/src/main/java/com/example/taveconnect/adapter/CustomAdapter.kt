package com.example.taveconnect.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taveconnect.GlobalApplication
import com.example.taveconnect.R
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

        val pictureUrl = profileList[position].picture
        if (pictureUrl.isNotEmpty()) {
            Glide.with(viewHolder.itemView.context)
                .load(pictureUrl)
                .circleCrop()
                .into(viewHolder.picture)
        } else {
            // picture가 없는 경우 기본 이미지
            Glide.with(viewHolder.itemView.context)
                .load(R.drawable.ic_happy)
                .circleCrop()
                .into(viewHolder.picture)
        }

    }

    override fun getItemCount(): Int = profileList.size


    // viewBinding으로 받아올 binding
    inner class Holder(val binding: ItemRankListBinding) : RecyclerView.ViewHolder(binding.root) {
        val rank = binding.tvRankInt
        val picture = binding.itemIvProfileRank
        val name = binding.itemTvNameRank
    }

}

