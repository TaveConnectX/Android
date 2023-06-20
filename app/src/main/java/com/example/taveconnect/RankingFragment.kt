package com.example.taveconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taveconnect.adapter.CustomAdapter
import com.example.taveconnect.databinding.FragmentRankingBinding

class RankingFragment : Fragment(R.layout.fragment_ranking) {
    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileList = ArrayList<RankProfile>()
        profileList.add(RankProfile(1, "김아린"))
        profileList.add(RankProfile(2, "박상연"))
        profileList.add(RankProfile(3, "이동준"))
        profileList.add(RankProfile(4, "이은미"))
        profileList.add(RankProfile(5, "정서린"))
        profileList.add(RankProfile(6, "조용준"))

        // 어댑터 생성
        val rankRecyclerAdapter = CustomAdapter(profileList)
        binding.rvRank.adapter = rankRecyclerAdapter
        binding.rvRank.layoutManager = LinearLayoutManager(this.activity)
    }


}