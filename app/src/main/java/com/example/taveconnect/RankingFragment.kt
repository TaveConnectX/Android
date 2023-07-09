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

        myRankingAPI()
        usersRankingAPI()


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



    fun myRankingAPI() {
        val rankAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        rankAPI.getMyRanking()
            .enqueue(object: Callback<MyRankData> {
                override fun onResponse(call: Call<MyRankData>, response: Response<MyRankData>) {
                    if (response.isSuccessful) {
                        Log.d("MyRankAPI", "성공 ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<MyRankData>, t: Throwable) {
                    Log.d("MyRankAPI", "실패")
                }
            })

    }


    fun usersRankingAPI() {
        val rankAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        rankAPI.getUsersRanking()
            .enqueue(object: Callback<UsersRankData> {
                override fun onResponse(call: Call<UsersRankData>, response: Response<UsersRankData>) {
                    if (response.isSuccessful) {
                        Log.d("UsersRankAPI", "성공 ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<UsersRankData>, t: Throwable) {
                    Log.d("UsersRankAPI", "실패")
                }
            })

    }

}
