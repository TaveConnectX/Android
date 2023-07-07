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

        val gamePaused = arguments?.getBoolean("gamePaused") ?: false

        // btnNewGame 버튼 클릭 이벤트 처리
        binding.btnNewGame.setOnClickListener {
            val intent = Intent(requireActivity(), DifficultyActivity::class.java)
            /*val gameActivity = activity as? GameActivity
            gameActivity?.reset()
            requireActivity().finish() // 현재 액티비티 종료*/
            startActivity(intent)
        }


        binding.btnPrevious.setOnClickListener {
            val intent = Intent(requireActivity(), ReviewActivity::class.java)
            startActivity(intent)
        }

        binding.btnGameIng.setOnClickListener {
            val intent = Intent(requireActivity(), GameActivity::class.java)
            intent.putExtra("gamePaused", true)
            intent.putExtra("resumeGame", true) // 이어하기 정보 추가

            val gameIndex = arguments?.getInt("reIndex", 0)
            val col1 = arguments?.getIntArray("r_col1") ?: IntArray(6)
            val col2 = arguments?.getIntArray("r_col2") ?: IntArray(6)
            val col3 = arguments?.getIntArray("r_col3") ?: IntArray(6)
            val col4 = arguments?.getIntArray("r_col4") ?: IntArray(6)
            val col5 = arguments?.getIntArray("r_col5") ?: IntArray(6)
            val col6 = arguments?.getIntArray("r_col6") ?: IntArray(6)
            val col7 = arguments?.getIntArray("r_col7") ?: IntArray(6)

            val intent2 = Intent(activity, ReviewActivity::class.java)
            intent2.putExtra("reIndex", gameIndex)
            intent2.putExtra("r_col1", col1)
            intent2.putExtra("r_col2", col2)
            intent2.putExtra("r_col3", col3)
            intent2.putExtra("r_col4", col4)
            intent2.putExtra("r_col5", col5)
            intent2.putExtra("r_col6", col6)
            intent2.putExtra("r_col7", col7)

            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}