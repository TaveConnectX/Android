package com.example.taveconnect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            val intent2 = Intent("ButtonClickedBroadcast")
            val intent3 = Intent("ifUnclickedBroadcast")
            activity?.sendBroadcast(intent3)
            activity?.sendBroadcast(intent2)
            activity?.startActivity(intent)
        }

        binding.btnNewStart.setOnClickListener {
            val intent = Intent(activity, DifficultyActivity::class.java)
            startActivity(intent)
        }
    }
}

