package com.example.taveconnect

import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityGameBinding
import java.util.Random

private var turn: Int = 0

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    // Fragment 매니저
    val manager = supportFragmentManager
    val soundPool = SoundPool.Builder().build()

    var col1 = IntArray(6) { 0 }
    var col2 = IntArray(6) { 0 }
    var col3 = IntArray(6) { 0 }
    var col4 = IntArray(6) { 0 }
    var col5 = IntArray(6) { 0 }
    var col6 = IntArray(6) { 0 }
    var col7 = IntArray(6) { 0 }

    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        c_col1 = col1.clone()
        c_col2 = col2.clone()
        c_col3 = col3.clone()
        c_col4 = col4.clone()
        c_col5 = col5.clone()
        c_col6 = col6.clone()
        c_col7 = col7.clone()

        // 효과음
        var soundId = soundPool.load(this, R.raw.hit, 1)

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
        fun onImageViewClick1(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }

        fun onImageViewClick2(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }

        fun onImageViewClick3(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }

        fun onImageViewClick4(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }

        fun onImageViewClick5(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }

        fun onImageViewClick6(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }

        fun onImageViewClick7(view: View) {

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
                    soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                    if(checkFourConnectedB(arrays) == true) {
                        countDownTimer.cancel()
                        turn = 2
                        setTurn(turn)
                        break
                    }
                    turn = 1
                    setTurn(turn)
                    Handler(Looper.getMainLooper()).postDelayed({
                        whiteRandom()
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        turn = 0
                        setTurn(turn)
                        countDownTimer.onFinish()
                        countDownTimer.start()
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer.cancel()
                            turn = 3
                            setTurn(turn)
                        }
                    }, 3000)
                    break
                }
                i++
            }
        }


        binding.ivGm11.setOnClickListener {
            onImageViewClick1(it)
        }
        binding.ivGm12.setOnClickListener {
            onImageViewClick1(it)
        }
        binding.ivGm13.setOnClickListener {
            onImageViewClick1(it)
        }
        binding.ivGm14.setOnClickListener {
            onImageViewClick1(it)
        }
        binding.ivGm15.setOnClickListener {
            onImageViewClick1(it)
        }
        binding.ivGm16.setOnClickListener {
            onImageViewClick1(it)
        }

        binding.ivGm21.setOnClickListener {
            onImageViewClick2(it)
        }
        binding.ivGm22.setOnClickListener {
            onImageViewClick2(it)
        }
        binding.ivGm23.setOnClickListener {
            onImageViewClick2(it)
        }
        binding.ivGm24.setOnClickListener {
            onImageViewClick2(it)
        }
        binding.ivGm25.setOnClickListener {
            onImageViewClick2(it)
        }
        binding.ivGm26.setOnClickListener {
            onImageViewClick2(it)
        }

        binding.ivGm31.setOnClickListener {
            onImageViewClick3(it)
        }
        binding.ivGm32.setOnClickListener {
            onImageViewClick3(it)
        }
        binding.ivGm33.setOnClickListener {
            onImageViewClick3(it)
        }
        binding.ivGm34.setOnClickListener {
            onImageViewClick3(it)
        }
        binding.ivGm35.setOnClickListener {
            onImageViewClick3(it)
        }
        binding.ivGm36.setOnClickListener {
            onImageViewClick3(it)
        }

        binding.ivGm41.setOnClickListener {
            onImageViewClick4(it)
        }
        binding.ivGm42.setOnClickListener {
            onImageViewClick4(it)
        }
        binding.ivGm43.setOnClickListener {
            onImageViewClick4(it)
        }
        binding.ivGm44.setOnClickListener {
            onImageViewClick4(it)
        }
        binding.ivGm45.setOnClickListener {
            onImageViewClick4(it)
        }
        binding.ivGm46.setOnClickListener {
            onImageViewClick4(it)
        }

        binding.ivGm51.setOnClickListener {
            onImageViewClick5(it)
        }
        binding.ivGm52.setOnClickListener {
            onImageViewClick5(it)
        }
        binding.ivGm53.setOnClickListener {
            onImageViewClick5(it)
        }
        binding.ivGm54.setOnClickListener {
            onImageViewClick5(it)
        }
        binding.ivGm55.setOnClickListener {
            onImageViewClick5(it)
        }
        binding.ivGm56.setOnClickListener {
            onImageViewClick5(it)
        }

        binding.ivGm61.setOnClickListener {
            onImageViewClick6(it)
        }
        binding.ivGm62.setOnClickListener {
            onImageViewClick6(it)
        }
        binding.ivGm63.setOnClickListener {
            onImageViewClick6(it)
        }
        binding.ivGm64.setOnClickListener {
            onImageViewClick6(it)
        }
        binding.ivGm65.setOnClickListener {
            onImageViewClick6(it)
        }
        binding.ivGm66.setOnClickListener {
            onImageViewClick6(it)
        }

        binding.ivGm71.setOnClickListener {
            onImageViewClick7(it)
        }
        binding.ivGm72.setOnClickListener {
            onImageViewClick7(it)
        }
        binding.ivGm73.setOnClickListener {
            onImageViewClick7(it)
        }
        binding.ivGm74.setOnClickListener {
            onImageViewClick7(it)
        }
        binding.ivGm75.setOnClickListener {
            onImageViewClick7(it)
        }
        binding.ivGm76.setOnClickListener {
            onImageViewClick7(it)
        }

    }

    // 4목 완성 확인
    fun checkFourConnectedB(arrays: Array<IntArray>): Boolean {
        // Check horizontal direction for black (1) and white (2) stones
        for (row in arrays.indices) {
            for (col in 0 until arrays[row].size - 3) {
                if ((arrays[row][col] == 1 || arrays[row][col] == 2) &&
                    arrays[row][col] == arrays[row][col + 1] &&
                    arrays[row][col] == arrays[row][col + 2] &&
                    arrays[row][col] == arrays[row][col + 3]
                ) {
                    return true
                }
            }
        }

        // Check vertical direction for black (1) and white (2) stones
        for (col in arrays[0].indices) {
            for (row in 0 until arrays.size - 3) {
                if ((arrays[row][col] == 1 || arrays[row][col] == 2) &&
                    arrays[row][col] == arrays[row + 1][col] &&
                    arrays[row][col] == arrays[row + 2][col] &&
                    arrays[row][col] == arrays[row + 3][col]
                ) {
                    return true
                }
            }
        }

        // Check diagonal direction (\) for black (1) and white (2) stones
        for (row in 0 until arrays.size - 3) {
            for (col in 0 until arrays[row].size - 3) {
                if ((arrays[row][col] == 1 || arrays[row][col] == 2) &&
                    arrays[row][col] == arrays[row + 1][col + 1] &&
                    arrays[row][col] == arrays[row + 2][col + 2] &&
                    arrays[row][col] == arrays[row + 3][col + 3]
                ) {
                    return true
                }
            }
        }

        // Check diagonal direction (/) for black (1) and white (2) stones
        for (row in 0 until arrays.size - 3) {
            for (col in 3 until arrays[row].size) {
                if ((arrays[row][col] == 1 || arrays[row][col] == 2) &&
                    arrays[row][col] == arrays[row + 1][col - 1] &&
                    arrays[row][col] == arrays[row + 2][col - 2] &&
                    arrays[row][col] == arrays[row + 3][col - 3]
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
            val intent = Intent(this, EndActivity::class.java)
            intent.putExtra("t", turn)
            startActivity(intent)
            reset()
            turn = 0    }
        else if(t==3)
        {   tv_turn.text = "You Lost.."
            val intent = Intent(this, EndActivity::class.java)
            intent.putExtra("t", turn)
            startActivity(intent)
            reset()
            turn = 0     }
    }


    fun reset() {
        print(arrays)
        col1 = IntArray(6) { 0 }
        col2 = IntArray(6) { 0 }
        col3 = IntArray(6) { 0 }
        col4 = IntArray(6) { 0 }
        col5 = IntArray(6) { 0 }
        col6 = IntArray(6) { 0 }
        col7 = IntArray(6) { 0 }
        binding.ivGm11.setImageResource(R.drawable.nothing)
        binding.ivGm12.setImageResource(R.drawable.nothing)
        binding.ivGm13.setImageResource(R.drawable.nothing)
        binding.ivGm14.setImageResource(R.drawable.nothing)
        binding.ivGm15.setImageResource(R.drawable.nothing)
        binding.ivGm16.setImageResource(R.drawable.nothing)
        binding.ivGm11.setImageResource(R.drawable.nothing)
        binding.ivGm12.setImageResource(R.drawable.nothing)
        binding.ivGm13.setImageResource(R.drawable.nothing)
        binding.ivGm14.setImageResource(R.drawable.nothing)
        binding.ivGm15.setImageResource(R.drawable.nothing)
        binding.ivGm16.setImageResource(R.drawable.nothing)
        binding.ivGm21.setImageResource(R.drawable.nothing)
        binding.ivGm22.setImageResource(R.drawable.nothing)
        binding.ivGm23.setImageResource(R.drawable.nothing)
        binding.ivGm24.setImageResource(R.drawable.nothing)
        binding.ivGm25.setImageResource(R.drawable.nothing)
        binding.ivGm26.setImageResource(R.drawable.nothing)
        binding.ivGm31.setImageResource(R.drawable.nothing)
        binding.ivGm32.setImageResource(R.drawable.nothing)
        binding.ivGm33.setImageResource(R.drawable.nothing)
        binding.ivGm34.setImageResource(R.drawable.nothing)
        binding.ivGm35.setImageResource(R.drawable.nothing)
        binding.ivGm36.setImageResource(R.drawable.nothing)
        binding.ivGm41.setImageResource(R.drawable.nothing)
        binding.ivGm42.setImageResource(R.drawable.nothing)
        binding.ivGm43.setImageResource(R.drawable.nothing)
        binding.ivGm44.setImageResource(R.drawable.nothing)
        binding.ivGm45.setImageResource(R.drawable.nothing)
        binding.ivGm46.setImageResource(R.drawable.nothing)
        binding.ivGm51.setImageResource(R.drawable.nothing)
        binding.ivGm52.setImageResource(R.drawable.nothing)
        binding.ivGm53.setImageResource(R.drawable.nothing)
        binding.ivGm54.setImageResource(R.drawable.nothing)
        binding.ivGm55.setImageResource(R.drawable.nothing)
        binding.ivGm56.setImageResource(R.drawable.nothing)
        binding.ivGm61.setImageResource(R.drawable.nothing)
        binding.ivGm62.setImageResource(R.drawable.nothing)
        binding.ivGm63.setImageResource(R.drawable.nothing)
        binding.ivGm64.setImageResource(R.drawable.nothing)
        binding.ivGm65.setImageResource(R.drawable.nothing)
        binding.ivGm66.setImageResource(R.drawable.nothing)
        binding.ivGm71.setImageResource(R.drawable.nothing)
        binding.ivGm72.setImageResource(R.drawable.nothing)
        binding.ivGm73.setImageResource(R.drawable.nothing)
        binding.ivGm74.setImageResource(R.drawable.nothing)
        binding.ivGm75.setImageResource(R.drawable.nothing)
        binding.ivGm76.setImageResource(R.drawable.nothing)
        print(arrays)
    }


    // BurgerFragment 클릭 이벤트
    fun showBurger() {
        binding.btnBurger.setOnClickListener {
            val intent = Intent(this, BurgerActivity::class.java)
            intent.putExtra("arrays", arrays)
            startActivity(intent)
        }
    }
}
