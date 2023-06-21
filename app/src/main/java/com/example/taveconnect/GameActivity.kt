package com.example.taveconnect

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityGameBinding
import com.example.taveconnect.databinding.FragmentBurgerBinding
import java.util.Random

private var turn: Int = 0

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    // Fragment 매니저
    val manager = supportFragmentManager

    val col1 = IntArray(6) { 0 }
    val col2 = IntArray(6) { 0 }
    val col3 = IntArray(6) { 0 }
    val col4 = IntArray(6) { 0 }
    val col5 = IntArray(6) { 0 }
    val col6 = IntArray(6) { 0 }
    val col7 = IntArray(6) { 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 타이머 구현
        val tv_sec = findViewById<TextView>(R.id.tv_second)
        val difficulty = intent.getStringExtra("difficulty")
        val sec = when (difficulty) {
            "easy" -> 60000 // 쉬운 난이도의 타이머 시간 (예: 60초)
            "normal" -> 30000 // 보통 난이도의 타이머 시간 (예: 30초)
            "hard" -> 15000 // 어려운 난이도의 타이머 시간 (예: 15초)
            else -> 30000 // 기본값으로 설정할 타이머 시간 (예: 30초)
        }
        val countDownTimer = object : CountDownTimer(sec.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val num = (millisUntilFinished / 1000).toInt()
                tv_sec!!.text = Integer.toString(num + 1)

                if(num==0)
                {
                    if(turn == 0)
                    {
                        turn = 3
                        setTurn(turn)
                    }
                    else if(turn == 1)
                    {
                        turn = 2
                        setTurn(turn)
                    }
                }
            }

            override fun onFinish() {

            }
        }.start()

        showBurger()


        // 1열
        binding.btnGm1.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_1_"
            while (i < col1.size) {
                if (col1[i] == 0 && turn == 0) {
                    col1[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }

        binding.btnGm2.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_2_"
            while (i < col2.size) {
                if (col2[i] == 0 && turn == 0) {
                    col2[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }

        binding.btnGm3.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_3_"
            while (i < col3.size) {
                if (col3[i] == 0 && turn == 0) {
                    col3[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }

        binding.btnGm4.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_4_"
            while (i < col4.size) {
                if (col4[i] == 0 && turn == 0) {
                    col4[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }

        binding.btnGm5.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_5_"
            while (i < col5.size) {
                if (col5[i] == 0 && turn == 0) {
                    col5[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }

        binding.btnGm6.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_6_"
            while (i < col6.size) {
                if (col6[i] == 0 && turn == 0) {
                    col6[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }

        binding.btnGm7.setOnClickListener {

            countDownTimer.onFinish()
            countDownTimer.start()
            var i = 0
            val coo = "iv_gm_7_"
            while (i < col7.size) {
                if (col7[i] == 0 && turn == 0) {
                    col7[i] = 1
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_black)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        turn = 2
                        setTurn(turn)
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                    }, 3000)
                    break
                }
                i++
            }
        }
    }

    // 4목 완성 확인
    fun checkFourConnectedB(arrays: Array<IntArray>): Boolean {
        // 가로 방향으로 1이 4개로 연결되는지 확인
        for (row in arrays.indices) {
            for (col in 0 until arrays[row].size - 3) {
                if (arrays[row][col] == 1 &&
                    arrays[row][col + 1] == 1 &&
                    arrays[row][col + 2] == 1 &&
                    arrays[row][col + 3] == 1
                ) {
                    return true
                }
            }
        }

        // 세로 방향으로 1이 4개로 연결되는지 확인
        for (col in arrays[0].indices) {
            for (row in 0 until arrays.size - 3) {
                if (arrays[row][col] == 1 &&
                    arrays[row + 1][col] == 1 &&
                    arrays[row + 2][col] == 1 &&
                    arrays[row + 3][col] == 1
                ) {
                    return true
                }
            }
        }

        // 대각선 방향(\)으로 1이 4개로 연결되는지 확인
        for (row in 0 until arrays.size - 3) {
            for (col in 0 until arrays[row].size - 3) {
                if (arrays[row][col] == 1 &&
                    arrays[row + 1][col + 1] == 1 &&
                    arrays[row + 2][col + 2] == 1 &&
                    arrays[row + 3][col + 3] == 1
                ) {
                    return true
                }
            }
        }

        // 대각선 방향(/)으로 1이 4개로 연결되는지 확인
        for (row in 0 until arrays.size - 3) {
            for (col in 3 until arrays[row].size) {
                if (arrays[row][col] == 1 &&
                    arrays[row + 1][col - 1] == 1 &&
                    arrays[row + 2][col - 2] == 1 &&
                    arrays[row + 3][col - 3] == 1
                ) {
                    return true
                }
            }
        }
        return false
    }

    // 백돌 랜덤 함수
    private fun whiteRandom() {
        val random = Random()
        val ranNum = random.nextInt(6) + 1

        if(ranNum == 1) {
            var i = 0
            val coo = "iv_gm_1_"
            while (i < col1.size) {
                if (col1[i] == 0) {
                    col1[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
        else if(ranNum == 2) {
            var i = 0
            val coo = "iv_gm_2_"
            while (i < col2.size) {
                if (col2[i] == 0) {
                    col2[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
        else if(ranNum == 3) {
            var i = 0
            val coo = "iv_gm_3_"
            while (i < col3.size) {
                if (col3[i] == 0) {
                    col3[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
        else if(ranNum == 4) {
            var i = 0
            val coo = "iv_gm_4_"
            while (i < col4.size) {
                if (col4[i] == 0) {
                    col4[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
        else if(ranNum == 5) {
            var i = 0
            val coo = "iv_gm_5_"
            while (i < col5.size) {
                if (col5[i] == 0) {
                    col5[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
        else if(ranNum == 6) {
            var i = 0
            val coo = "iv_gm_6_"
            while (i < col6.size) {
                if (col6[i] == 0) {
                    col6[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
        else {
            var i = 0
            val coo = "iv_gm_7_"
            while (i < col7.size) {
                if (col7[i] == 0) {
                    col7[i] = 2
                    i++
                    val coord = coo + i
                    val packageName = packageName
                    val ivId = resources.getIdentifier(coord, "id", packageName)
                    val imageView = findViewById<ImageView>(ivId)
                    imageView.setImageResource(R.drawable.ic_white)
                    break
                }
                i++
            }
        }
    }

    fun setTurn(t: Int) {
        val tv_turn = findViewById<TextView>(R.id.tv_yourturn)
        if(t == 1)
        {   tv_turn.text = "Opponent Turn" }
        else if(t == 0)
        {   tv_turn.text = "Your Turn"  }
        else if(t==2)
        {   tv_turn.text = "You Win!!"
            binding.btnGm1.isEnabled = false
            binding.btnGm2.isEnabled = false
            binding.btnGm3.isEnabled = false
            binding.btnGm4.isEnabled = false
            binding.btnGm5.isEnabled = false
            binding.btnGm6.isEnabled = false
            binding.btnGm7.isEnabled = false    }
        else if(t==3)
        {   tv_turn.text = "You Lost.."
            binding.btnGm1.isEnabled = false
            binding.btnGm2.isEnabled = false
            binding.btnGm3.isEnabled = false
            binding.btnGm4.isEnabled = false
            binding.btnGm5.isEnabled = false
            binding.btnGm6.isEnabled = false
            binding.btnGm7.isEnabled = false    }
    }




    // BurgerFragment 클릭 이벤트
    fun showBurger() {
        binding.btnBurger.setOnClickListener {
            Toast.makeText(this, "버거 프래그먼트 불러오기", Toast.LENGTH_SHORT).show()
        }
    }

}

// 4개 연결 확인하고 이기는 거 출력되게 수정하기(함수 변경 + 조합 + 순서 조합해서)
// 이기거나 지면 화면 새로 띄울지 얘기해보기
// 효과음이나 배경음악 쓸 건지

