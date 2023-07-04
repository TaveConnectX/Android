package com.example.taveconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.taveconnect.databinding.FragmentMenuBinding

class MenuFragment: Fragment(R.layout.fragment_menu) {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var callback : OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoMain.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)

            val intent2 = Intent(activity, LoadActivity::class.java)
            val gameActivity = activity as GameActivity
            val col1 = gameActivity.col1
            val col2 = gameActivity.col2
            val col3 = gameActivity.col3
            val col4 = gameActivity.col4
            val col5 = gameActivity.col5
            val col6 = gameActivity.col6
            val col7 = gameActivity.col7
            intent2.putExtra("col1", col1)
            intent2.putExtra("col2", col2)
            intent2.putExtra("col3", col3)
            intent2.putExtra("col4", col4)
            intent2.putExtra("col5", col5)
            intent2.putExtra("col6", col6)
            intent2.putExtra("col7", col7)
        }

        binding.btnNewStart.setOnClickListener {
            val intent = Intent(activity, DifficultyActivity::class.java)
            startActivity(intent)
        }

    }
}

