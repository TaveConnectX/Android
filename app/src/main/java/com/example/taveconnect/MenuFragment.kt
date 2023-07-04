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
        }

        binding.btnNewStart.setOnClickListener {
            val intent = Intent(activity, DifficultyActivity::class.java)
            startActivity(intent)
        }

    }
}

