package com.example.taveconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.taveconnect.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    val manager = supportFragmentManager

    var goMainButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showInit()
        initBottomNav()

        val gameIndex = intent.getIntExtra("reIndex", 0)
        Log.d("GameActivity", "Index = "+gameIndex)
        col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        val intent2 = Intent(this, ReviewActivity::class.java)
        intent2.putExtra("reIndex", gameIndex)
        intent2.putExtra("r_col1", col1)
        intent2.putExtra("r_col2", col2)
        intent2.putExtra("r_col3", col3)
        intent2.putExtra("r_col4", col4)
        intent2.putExtra("r_col5", col5)
        intent2.putExtra("r_col6", col6)
        intent2.putExtra("r_col7", col7)

    }


    // 초기 Fragment 설정
    fun showInit() {
        val transaction = manager.beginTransaction()
            .add(R.id.fv_main, ListFragment())
        transaction.commit()
    }

    private fun Fragment.changeFragment() {
        manager.beginTransaction().replace(R.id.fv_main, this).commit()
    }

    private fun initBottomNav() {

        binding.btnNavHome.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.main -> {
                    ListFragment().changeFragment()
                }
                R.id.explanation -> {
                    ExplainFragment().changeFragment()
                }
                R.id.mypage -> {
                    MyPageFragment().changeFragment()
                }
                R.id.rank -> {
                    RankingFragment().changeFragment()
                }
            }
            return@setOnItemSelectedListener true
        }
    }


}