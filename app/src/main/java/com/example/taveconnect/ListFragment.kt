package com.example.taveconnect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taveconnect.databinding.FragmentListBinding

class
ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // btnNewGame 버튼 클릭 이벤트 처리
        binding.btnNewGame.setOnClickListener {
            val intent = Intent(activity, DifficultyActivity::class.java)
            startActivity(intent)
        }

        // btnPreviousGame 버튼 클릭 이벤트 처리
        binding.btnPrevious.setOnClickListener {
            val intent = Intent(activity, ReviewActivity::class.java)
            startActivity(intent)
        }

        binding.btnGameIng.setOnClickListener {
            val homeActivity = activity as? HomeActivity
            if (homeActivity?.goMainButtonClicked == true) {
                val intent = Intent(activity, LoadActivity::class.java)
                Toast.makeText(context, "이전 게임을 불러옵니다.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            else {
                Toast.makeText(context, "이어할 게임이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}