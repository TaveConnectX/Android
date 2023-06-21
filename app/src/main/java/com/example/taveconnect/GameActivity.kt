package com.example.taveconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< Updated upstream
=======
        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)

        // 타이머 구현
        val tv_sec = findViewById<TextView>(R.id.tv_second)
        val sec = intent.getLongExtra("seconds", 0)
        val countDownTimer = object : CountDownTimer(sec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val num = (millisUntilFinished / 1000).toInt()
                tv_sec!!.text = Integer.toString(num + 1)
            }


            override fun onFinish() { }
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
>>>>>>> Stashed changes
    }
}