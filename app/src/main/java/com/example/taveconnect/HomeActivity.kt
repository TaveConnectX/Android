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
        Log.d("GameActivity", "Index = $gameIndex")
        val col1 = intent.getIntArrayExtra("r_col1") ?: IntArray(6)
        val col2 = intent.getIntArrayExtra("r_col2") ?: IntArray(6)
        val col3 = intent.getIntArrayExtra("r_col3") ?: IntArray(6)
        val col4 = intent.getIntArrayExtra("r_col4") ?: IntArray(6)
        val col5 = intent.getIntArrayExtra("r_col5") ?: IntArray(6)
        val col6 = intent.getIntArrayExtra("r_col6") ?: IntArray(6)
        val col7 = intent.getIntArrayExtra("r_col7") ?: IntArray(6)

        val bundle = Bundle().apply {
            putInt("gameIndex", gameIndex)
            putIntArray("col1", col1)
            putIntArray("col2", col2)
            putIntArray("col3", col3)
            putIntArray("col4", col4)
            putIntArray("col5", col5)
            putIntArray("col6", col6)
            putIntArray("col7", col7)
        }

        val listFragment = ListFragment()
        listFragment.arguments = bundle
        showFragment(listFragment)
    }

    // 초기 Fragment 설정
    fun showInit() {
        val transaction = manager.beginTransaction()
            .add(R.id.fv_main, ListFragment())
        transaction.commit()
    }

    private fun showFragment(fragment: Fragment) {
        manager.beginTransaction().replace(R.id.fv_main, fragment).commit()
    }

    private fun initBottomNav() {
        binding.btnNavHome.setOnItemSelectedListener { menuItem ->
            val fragment: Fragment = when (menuItem.itemId) {
                R.id.main -> ListFragment()
                R.id.explanation -> ExplainFragment()
                R.id.mypage -> MyPageFragment()
                R.id.rank -> RankingFragment()
                else -> ListFragment() // 기본값으로 ListFragment 사용
            }
            fragment.arguments = ListFragment().arguments // 이전에 전달한 데이터 적용

            showFragment(fragment)
            return@setOnItemSelectedListener true
        }
    }
}