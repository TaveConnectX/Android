package com.example.taveconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taveconnect.databinding.FragmentDifficultyBinding

class DifficultyFragment : Fragment(R.layout.fragment_difficulty) {
    private var _binding: FragmentDifficultyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDifficultyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}