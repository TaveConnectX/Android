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
import com.example.taveconnect.rank.RankData
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
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

        val api = RetrofitClient.getInstance().create(RetroiftAPI::class.java)
        api.getRanking()
            .enqueue(object: Callback<RankData> {
                override fun onResponse(call: Call<RankData>, response: Response<RankData>) {
                    profileList.add(RankData(response.code(), response.body().toString()))
                    Log.d("Rank", "성공 ${api.getRanking().request()?.headers}")
                }

                override fun onFailure(call: Call<RankData>, t: Throwable) {
                    Log.d("Rank", "실패 ${t.message}")
                }
            })



        profileList.add(RankData(1, "김아린"))
        profileList.add(RankData(2, "박상연"))
        profileList.add(RankData(3, "이동준"))
        profileList.add(RankData(4, "이은미"))
        profileList.add(RankData(5, "정서린"))
        profileList.add(RankData(6, "조용준"))
        profileList.add(RankData(7, "김아린"))
        profileList.add(RankData(8, "박상연"))
        profileList.add(RankData(9, "이동준"))
        profileList.add(RankData(10, "이은미"))
        profileList.add(RankData(11, "정서린"))
        profileList.add(RankData(12, "조용준"))


        Log.d("Data", profileList.toString())

        // 어댑터 생성
        val rankRecyclerAdapter = CustomAdapter(profileList)

        binding.rvRank.adapter = rankRecyclerAdapter
        binding.rvRank.layoutManager = LinearLayoutManager(this.context) // 수정된 부분

        // 어댑터에 데이터 변경을 알리기 위해 notifyDataSetChanged() 호출
        rankRecyclerAdapter.notifyDataSetChanged()
    }
}
