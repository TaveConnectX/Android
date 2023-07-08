package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        val gameIndex = arguments?.getInt("gameIndex", 0)
        Log.d("GameActivity", "fragment Index = $gameIndex")
        val col1 = arguments?.getIntArray("col1") ?: IntArray(6)
        val col2 = arguments?.getIntArray("col2") ?: IntArray(6)
        val col3 = arguments?.getIntArray("col3") ?: IntArray(6)
        val col4 = arguments?.getIntArray("col4") ?: IntArray(6)
        val col5 = arguments?.getIntArray("col5") ?: IntArray(6)
        val col6 = arguments?.getIntArray("col6") ?: IntArray(6)
        val col7 = arguments?.getIntArray("col7") ?: IntArray(6)

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
            Log.d("GameActivity", "btnClicked &gameIndex")
            intent.putExtra("reIndex", gameIndex)
            intent.putExtra("r_col1", col1)
            intent.putExtra("r_col2", col2)
            intent.putExtra("r_col3", col3)
            intent.putExtra("r_col4", col4)
            intent.putExtra("r_col5", col5)
            intent.putExtra("r_col6", col6)
            intent.putExtra("r_col7", col7)
            startActivity(intent)
        }

        binding.btnGameIng.setOnClickListener {
            val intent = Intent(requireActivity(), GameActivity::class.java)
            intent.putExtra("gamePaused", true)
            intent.putExtra("resumeGame", true) // 이어하기 정보 추가
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}