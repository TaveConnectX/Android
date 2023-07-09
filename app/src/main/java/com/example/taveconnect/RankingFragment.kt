package com.example.taveconnect

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taveconnect.adapter.CustomAdapter
import com.example.taveconnect.databinding.FragmentRankingBinding
import com.example.taveconnect.game.GameReviewData
import com.example.taveconnect.rank.MyRankData
import com.example.taveconnect.rank.RankData
import com.example.taveconnect.rank.UsersRankData
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


        val profileList = ArrayList<RankData>()


        val rankRecyclerAdapter = CustomAdapter(profileList)


        binding.rvRank.adapter = rankRecyclerAdapter
        binding.rvRank.layoutManager = LinearLayoutManager(requireContext())

        val rankAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        rankAPI.getUsersRanking().enqueue(object: Callback<UsersRankData> {
            override fun onResponse(call: Call<UsersRankData>, response: Response<UsersRankData>) {
                if (response.isSuccessful) {
                    val userDataList = response.body()?.toList()
                    userDataList?.forEachIndexed { index, userData ->
                        profileList.add(RankData(userData.ranking, userData.picture, userData.name))
                    }
                    rankRecyclerAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<UsersRankData>, t: Throwable) {
                Log.d("UsersRankAPI", "실패")
            }
        })


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
