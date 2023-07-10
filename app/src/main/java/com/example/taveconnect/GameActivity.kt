package com.example.taveconnect

import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taveconnect.databinding.ActivityGameBinding
import com.example.taveconnect.game.*
import com.example.taveconnect.retrofit.RetrofitClient
import com.example.taveconnect.retrofit.RetroiftAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random


private var turn: Int = 0

private var col1 = IntArray(6) { 0 }
private var col2 = IntArray(6) { 0 }
private var col3 = IntArray(6) { 0 }
private var col4 = IntArray(6) { 0 }
private var col5 = IntArray(6) { 0 }
private var col6 = IntArray(6) { 0 }
private var col7 = IntArray(6) { 0 }
private var choice = "normal"

class GameActivity : AppCompatActivity() {

    var gamePaused = false
    private lateinit var binding: ActivityGameBinding
    val soundPool = SoundPool.Builder().build()

    private var countDownTimer: CountDownTimer? = null

    var canOrCannot = 2
    var choice_c = "normal"

    override fun onPause() {
        super.onPause()
        val resumeGame = intent.getBooleanExtra("resumeGame", false)
        countDownTimer?.cancel()
        Log.d("GameActivity", "countDownTimer cancel() called")
        if(resumeGame) {
            countDownTimer?.cancel()
            Log.d("GameActivity", "countDownTimer cancel() called")
        }
        c_col1 = col1.clone()
        c_col2 = col2.clone()
        c_col3 = col3.clone()
        c_col4 = col4.clone()
        c_col5 = col5.clone()
        c_col6 = col6.clone()
        c_col7 = col7.clone()
        choice_c = choice
        Log.d("GameActivity", "onPause() called")



        gamePaused = true
    }

    public override fun onResume() {
        super.onResume()
        Log.d("GameActivity", "onResume() called")
        val resumeGame = intent.getBooleanExtra("resumeGame", false)
        Log.d("GameActivity", "resumeGame "+resumeGame.toString())
        Log.d("GameActivity", "gamePaused "+gamePaused.toString())
        // 게임 액티비티가 다시 재개되는 경우에 수행할 동작을 여기에 작성
        // 예: 게임 재개, 타이머 다시 시작 등
        if (resumeGame || gamePaused) {
            // 게임이 일시 중지된 상태에서 재개되는 경우에 수행할 동작
            Log.d("GameActivity", "이전 게임 called")
            if(gamePaused)
                countDownTimer?.start()
            // 게임 상태 배열 복원
            col1 = c_col1.clone()
            col2 = c_col2.clone()
            col3 = c_col3.clone()
            col4 = c_col4.clone()
            col5 = c_col5.clone()
            col6 = c_col6.clone()
            col7 = c_col7.clone()
            choice = choice_c

            var arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)

            if(checkFourConnectedB(arrays))
            {
                val intent_d = Intent(this, DifficultyActivity::class.java)
                reset()
                Toast.makeText(this, "이어할 게임이 없어 새 게임을 시작합니다.", Toast.LENGTH_SHORT).show()
                startActivity(intent_d)

            } else if(arrays == {0})
                Toast.makeText(this, "이어할 게임이 없어 새 게임을 시작합니다.", Toast.LENGTH_SHORT).show()

            // 1열 이미지뷰 상태 복원
            for (i in 0 until col1.size) {
                val coord = "iv_gm_1_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col1[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col1[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 2열 이미지뷰 상태 복원
            for (i in 0 until col2.size) {
                val coord = "iv_gm_2_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col2[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col2[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 3열 이미지뷰 상태 복원
            for (i in 0 until col3.size) {
                val coord = "iv_gm_3_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col3[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col3[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 4열 이미지뷰 상태 복원
            for (i in 0 until col4.size) {
                val coord = "iv_gm_4_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col4[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col4[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 5열 이미지뷰 상태 복원
            for (i in 0 until col5.size) {
                val coord = "iv_gm_5_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col5[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col5[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 6열 이미지뷰 상태 복원
            for (i in 0 until col6.size) {
                val coord = "iv_gm_6_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col6[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col6[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 7열 이미지뷰 상태 복원
            for (i in 0 until col7.size) {
                val coord = "iv_gm_7_" + (i + 1)
                val packageName = packageName
                val ivId = resources.getIdentifier(coord, "id", packageName)
                val imageView = findViewById<ImageView>(ivId)
                if (col7[i] == 1) {
                    imageView.setImageResource(R.drawable.ic_black)
                } else if (col7[i] == 2) {
                    imageView.setImageResource(R.drawable.ic_white)
                } else {
                    // 이미지뷰를 초기화해야 하는 경우
                    imageView.setImageDrawable(null)
                }
            }

            // 게임의 다른 변수 및 상태 복원
            // 예: 타이머 상태, 턴 상태 등의 복원

            // 게임을 다시 시작하기 위해 필요한 로직 실행
            // 예: 핸들러를 사용하여 흰색 돌 랜덤 위치 설정 등의 로직 실행

            gamePaused = false // 게임이 재개되었으므로 gamePaused 변수를 false로 설정
        }

        else {
            reset()
            Log.d("GameActivity", "리셋 called")
            gamePaused = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    var index: Int = 0
    var c_col1 = IntArray(6) { 0 }
    var c_col2 = IntArray(6) { 0 }
    var c_col3 = IntArray(6) { 0 }
    var c_col4 = IntArray(6) { 0 }
    var c_col5 = IntArray(6) { 0 }
    var c_col6 = IntArray(6) { 0 }
    var c_col7 = IntArray(6) { 0 }

    var r_col1 = IntArray(6) { 0 }
    var r_col2 = IntArray(6) { 0 }
    var r_col3 = IntArray(6) { 0 }
    var r_col4 = IntArray(6) { 0 }
    var r_col5 = IntArray(6) { 0 }
    var r_col6 = IntArray(6) { 0 }
    var r_col7 = IntArray(6) { 0 }

    var num = 0

    var reIndex = 0

    var arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent2 = Intent(this, ReviewActivity::class.java)
        //val intent = Intent(this, DifficultyActivity::class.java)

        intent2.putExtra("gameIndex", reIndex)

        c_col1 = col1.clone()
        c_col2 = col2.clone()
        c_col3 = col3.clone()
        c_col4 = col4.clone()
        c_col5 = col5.clone()
        c_col6 = col6.clone()
        c_col7 = col7.clone()



        gameStartAPI()



        // 효과음
        var soundId = soundPool.load(this, R.raw.hit, 1)

        // 타이머 구현
        val tv_sec = findViewById<TextView>(R.id.tv_second)
        var sec = 30000
        val difficulty = intent.getStringExtra("difficulty")
        GlobalApplication.prefs.setString("difficulty", difficulty.toString())
        Log.d("난이도", "${GlobalApplication.prefs.getString(" difficulty ", "")}")


        if (difficulty != null) {
            choice = difficulty
            sec = when (difficulty) {
                "easy" -> 60000 // 쉬운 난이도의 타이머 시간 (예: 60초)
                "normal" -> 30000 // 보통 난이도의 타이머 시간 (예: 30초)
                "hard" -> 15000 // 어려운 난이도의 타이머 시간 (예: 15초)
                else -> 30000 // 기본값으로 설정할 타이머 시간 (예: 30초)
            }
        } else{
            sec = when (choice) {
                "easy" -> 60000 // 쉬운 난이도의 타이머 시간 (예: 60초)
                "normal" -> 30000 // 보통 난이도의 타이머 시간 (예: 30초)
                "hard" -> 15000 // 어려운 난이도의 타이머 시간 (예: 15초)
                else -> 30000 // 기본값으로 설정할 타이머 시간 (예: 30초)
            }
        }


        Log.d("GameActivity", "difficulty 값은 " + difficulty)


        countDownTimer = object : CountDownTimer(sec.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                num = (millisUntilFinished / 1000).toInt()
                tv_sec!!.text = Integer.toString(num + 1)

                if(num==0)
                {
                    if(turn == 0)
                    {
                        turn = 3
                        setTurn(turn)
                        canOrCannot = 3
                    }
                    else if(turn == 1)
                    {
                        turn = 2
                        setTurn(turn)
                        canOrCannot = 2
                    }
                }
                else if(num==5)
                    Log.d("GameActivity", "남은 시간 "+num+"초")
            }

            override fun onFinish() {
                num = 30000
            }

        }.start()

        showBurger()

        // 1열
        fun onImageViewClick1(view: View) {
            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_1_"
                while (i < col1.size) {
                    if (col1[i] == 0 && turn == 0) {
                        col1[i] = 1
                        c_col1[i] = 1
                        reIndex ++
                        r_col1[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        fun onImageViewClick2(view: View) {

            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_2_"
                while (i < col2.size) {
                    if (col2[i] == 0 && turn == 0) {
                        col2[i] = 1
                        c_col2[i] = 1
                        reIndex ++
                        r_col2[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        fun onImageViewClick3(view: View) {
            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_3_"
                while (i < col3.size) {
                    if (col3[i] == 0 && turn == 0) {
                        col3[i] = 1
                        c_col3[i] = 1
                        reIndex ++
                        r_col3[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        fun onImageViewClick4(view: View) {
            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_4_"
                while (i < col4.size) {
                    if (col4[i] == 0 && turn == 0) {
                        col4[i] = 1
                        c_col4[i] = 1
                        reIndex ++
                        r_col4[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        fun onImageViewClick5(view: View) {
            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_5_"
                while (i < col5.size) {
                    if (col5[i] == 0 && turn == 0) {
                        col5[i] = 1
                        c_col5[i] = 1
                        reIndex ++
                        r_col5[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        fun onImageViewClick6(view: View) {
            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_6_"
                while (i < col6.size) {
                    if (col6[i] == 0 && turn == 0) {
                        col6[i] = 1
                        c_col6[i] = 1
                        reIndex ++
                        r_col6[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        fun onImageViewClick7(view: View) {
            if (countDownTimer != null) {
                countDownTimer!!.onFinish()
                countDownTimer!!.start()
                var i = 0
                val coo = "iv_gm_7_"
                while (i < col7.size) {
                    if (col7[i] == 0 && turn == 0) {
                        col7[i] = 1
                        c_col7[i] = 1
                        reIndex ++
                        r_col7[i] = reIndex
                        Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
                        i++
                        val coord = coo + i
                        val packageName = packageName
                        val ivId = resources.getIdentifier(coord, "id", packageName)
                        val imageView = findViewById<ImageView>(ivId)
                        imageView.setImageResource(R.drawable.ic_black)
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                        val arrays = arrayOf(col1, col2, col3, col4, col5, col6, col7)
                        if(checkFourConnectedB(arrays) == true) {
                            countDownTimer!!.onFinish()
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
                            countDownTimer!!.onFinish()
                            countDownTimer!!.start()
                            if(checkFourConnectedB(arrays) == true) {
                                countDownTimer!!.onFinish()
                                turn = 3
                                setTurn(turn)
                            }
                        }, 3000)
                        break
                    }
                    i++
                }
            }
        }

        binding.ivGm11.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 1)), index + 1))
            onImageViewClick1(it)
        }
        binding.ivGm12.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 1)), index + 1))
            onImageViewClick1(it)
        }
        binding.ivGm13.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 1)), index + 1))
            onImageViewClick1(it)
        }
        binding.ivGm14.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 1)), index + 1))
            onImageViewClick1(it)
        }
        binding.ivGm15.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 1)), index + 1))
            onImageViewClick1(it)
        }
        binding.ivGm16.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 1)), index + 1))
            onImageViewClick1(it)
        }

        binding.ivGm21.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 2)), index + 1))
            onImageViewClick2(it)
        }
        binding.ivGm22.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 2)), index + 1))
            onImageViewClick2(it)
        }
        binding.ivGm23.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 2)), index + 1))
            onImageViewClick2(it)
        }
        binding.ivGm24.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 2)), index + 1))
            onImageViewClick2(it)
        }
        binding.ivGm25.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 2)), index + 1))
            onImageViewClick2(it)
        }
        binding.ivGm26.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 2)), index + 1))
            onImageViewClick2(it)
        }

        binding.ivGm31.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 3)), index + 1))
            onImageViewClick3(it)
        }
        binding.ivGm32.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 3)), index + 1))
            onImageViewClick3(it)
        }
        binding.ivGm33.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 3)), index + 1))
            onImageViewClick3(it)
        }
        binding.ivGm34.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 3)), index + 1))
            onImageViewClick3(it)
        }
        binding.ivGm35.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 3)), index + 1))
            onImageViewClick3(it)
        }
        binding.ivGm36.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 3)), index + 1))
            onImageViewClick3(it)
        }
        binding.ivGm41.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 4)), index + 1))
            onImageViewClick4(it)
        }
        binding.ivGm42.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 4)), index + 1))
            onImageViewClick4(it)
        }
        binding.ivGm43.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 4)), index + 1))
            onImageViewClick4(it)
        }
        binding.ivGm44.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 4)), index + 1))
            onImageViewClick4(it)
        }
        binding.ivGm45.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 4)), index + 1))
            onImageViewClick4(it)
        }
        binding.ivGm46.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 4)), index + 1))
            onImageViewClick4(it)
        }

        binding.ivGm51.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 5)), index + 1))
            onImageViewClick5(it)
        }
        binding.ivGm52.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 5)), index + 1))
            onImageViewClick5(it)
        }
        binding.ivGm53.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 5)), index + 1))
            onImageViewClick5(it)
        }
        binding.ivGm54.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 5)), index + 1))
            onImageViewClick5(it)
        }
        binding.ivGm55.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 5)), index + 1))
            onImageViewClick5(it)
        }
        binding.ivGm56.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 5)), index + 1))
            onImageViewClick5(it)
        }

        binding.ivGm61.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick6(it)
        }
        binding.ivGm62.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick6(it)
        }
        binding.ivGm63.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick6(it)
        }
        binding.ivGm64.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick6(it)
        }
        binding.ivGm65.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick6(it)
        }
        binding.ivGm66.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick6(it)
        }

        binding.ivGm71.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index + 1))
            onImageViewClick7(it)
        }
        binding.ivGm72.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 7)), index + 1))
            onImageViewClick7(it)
        }
        binding.ivGm73.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 7)), index + 1))
            onImageViewClick7(it)
        }
        binding.ivGm74.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 7)), index + 1))
            onImageViewClick7(it)
        }
        binding.ivGm75.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 7)), index + 1))
            onImageViewClick7(it)
        }
        binding.ivGm76.setOnClickListener {
            gameTurnAPI(GameTurnDTO(difficulty, GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 7)), index + 1))
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
                    c_col1[i] = 2
                    reIndex ++
                    r_col1[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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
                    c_col2[i] = 2
                    reIndex ++
                    r_col2[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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
                    c_col3[i] = 2
                    reIndex ++
                    r_col3[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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
                    c_col4[i] = 2
                    reIndex ++
                    r_col4[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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
                    c_col5[i] = 2
                    reIndex ++
                    r_col5[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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
                    c_col6[i] = 2
                    reIndex ++
                    r_col6[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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
                    c_col7[i] = 2
                    reIndex ++
                    r_col7[i] = reIndex
                    Log.d("GameActivity", "인덱스 값은 "+reIndex.toString())
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


        if (t == 1) {
            tv_turn.text = "Opponent Turn"
            index++
        } else if (t == 0) {
            tv_turn.text = "Your Turn"
            index++
        } else if (t == 2) {
            gameEndAPI(GameEndDTO(
                difficulty = GlobalApplication.prefs.getString("difficulty", ""),
                gameIdx = GlobalApplication.prefs.getInt("gameIdx", 0),
                list = arrayOf(arrayOf(6, 1)),
                turn = index,
                winner = 1
            ))


            tv_turn.text = "You Win!!"
            val intent = Intent(this, EndActivity::class.java)
            Log.d("GameActivity", "reIndex 값 : " + reIndex)
            intent.putExtra("t", turn)
            intent.putExtra("reIndex", reIndex)
            intent.putExtra("r_col1", r_col1)
            intent.putExtra("r_col2", r_col2)
            intent.putExtra("r_col3", r_col3)
            intent.putExtra("r_col4", r_col4)
            intent.putExtra("r_col5", r_col5)
            intent.putExtra("r_col6", r_col6)
            intent.putExtra("r_col7", r_col7)
            startActivity(intent)
        } else if (t == 3) {
            gameEndAPI(GameEndDTO(GlobalApplication.prefs.getString("difficulty", ""), GlobalApplication.prefs.getInt("gameIdx", 0), list = arrayOf(arrayOf(6, 6)), index, 2))




            tv_turn.text = "You Lost.."
            val intent = Intent(this, EndActivity::class.java)
            Log.d("GameActivity", "reIndex 값 : " + reIndex)
            intent.putExtra("t", turn)
            intent.putExtra("reIndex", reIndex)
            intent.putExtra("r_col1", r_col1)
            intent.putExtra("r_col2", r_col2)
            intent.putExtra("r_col3", r_col3)
            intent.putExtra("r_col4", r_col4)
            intent.putExtra("r_col5", r_col5)
            intent.putExtra("r_col6", r_col6)
            intent.putExtra("r_col7", r_col7)
            startActivity(intent)
        }
    }

    fun reset() {
        turn = 0
        setTurn(turn)
        index = 0
        arrays = emptyArray()
        col1 = IntArray(6) { 0 }
        col2 = IntArray(6) { 0 }
        col3 = IntArray(6) { 0 }
        col4 = IntArray(6) { 0 }
        col5 = IntArray(6) { 0 }
        col6 = IntArray(6) { 0 }
        col7 = IntArray(6) { 0 }
        c_col1 = IntArray(6) { 0 }
        c_col2 = IntArray(6) { 0 }
        c_col3 = IntArray(6) { 0 }
        c_col4 = IntArray(6) { 0 }
        c_col5 = IntArray(6) { 0 }
        c_col6 = IntArray(6) { 0 }
        c_col7 = IntArray(6) { 0 }

        val imageViews = arrayOf(
            arrayOf(binding.ivGm11, binding.ivGm12, binding.ivGm13, binding.ivGm14, binding.ivGm15, binding.ivGm16),
            arrayOf(binding.ivGm21, binding.ivGm22, binding.ivGm23, binding.ivGm24, binding.ivGm25, binding.ivGm26),
            arrayOf(binding.ivGm31, binding.ivGm32, binding.ivGm33, binding.ivGm34, binding.ivGm35, binding.ivGm36),
            arrayOf(binding.ivGm41, binding.ivGm42, binding.ivGm43, binding.ivGm44, binding.ivGm45, binding.ivGm46),
            arrayOf(binding.ivGm51, binding.ivGm52, binding.ivGm53, binding.ivGm54, binding.ivGm55, binding.ivGm56),
            arrayOf(binding.ivGm61, binding.ivGm62, binding.ivGm63, binding.ivGm64, binding.ivGm65, binding.ivGm66),
            arrayOf(binding.ivGm71, binding.ivGm72, binding.ivGm73, binding.ivGm74, binding.ivGm75, binding.ivGm76)
        )

        for (i in imageViews.indices) {
            for (j in imageViews[i].indices) {
                imageViews[i][j].setImageResource(R.drawable.nothing)
            }
        }
    }

    //   Fragment 클릭 이벤트
    fun showBurger() {
        binding.btnBurger.setOnClickListener {
            val intent = Intent(this, BurgerActivity::class.java)
            //intent.putExtra("gamePaused", gamePaused) // gamePaused 변수를 Intent에 담아서 전달
            startActivity(intent)
        }
    }


    fun gameStartAPI() {
        // API
        val gameAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)
        var difficulty = intent.getStringExtra("difficulty")


        gameAPI.getGameStart(difficulty.toString())
            .enqueue(object: retrofit2.Callback<GameStartData> {
                override fun onResponse(
                    call: Call<GameStartData>,
                    response: Response<GameStartData>
                ) {
                    if (response.isSuccessful) {
                        response?.body()?.gameIdx?.let {
                            GlobalApplication.prefs.setInt("gameIdx",
                                it)
                        }



                        GlobalApplication.prefs.setString("gameList", "${java.util.Arrays.deepToString(response.body()?.list)}")
                        Log.d("GameStart", "성공 ${response.body().toString()}")

                    }
                }

                override fun onFailure(call: Call<GameStartData>, t: Throwable) {
                    Log.d("GameStart", "실패")
                }
            })

    }

    fun gameTurnAPI(gameTurnDTO: GameTurnDTO) {
        // API
        val gameAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        gameAPI.getGameTurn(gameTurnDTO)
            .enqueue(object: Callback<GameTurnData> {
                override fun onResponse(
                    call: Call<GameTurnData>,
                    response: Response<GameTurnData>
                ) {
                    if (response.isSuccessful) {
                        Log.d("GameTurnAPI", "성공 ${response.body().toString()}")
                        Log.d("GameList", "${GlobalApplication.prefs.getString("gameList", "")}")
                    }
                }

                override fun onFailure(call: Call<GameTurnData>, t: Throwable) {
                    Log.d("GameTurnAPI", "실패")
                }
            })
    }


    fun gameEndAPI(gameEndDTO: GameEndDTO) {
        val gameAPI = RetrofitClient.getInstance().create(RetroiftAPI::class.java)

        gameAPI.getGameEnd(gameEndDTO)
            .enqueue(object: Callback<GameEndData> {
                override fun onResponse(call: Call<GameEndData>, response: Response<GameEndData>) {
                    if (response.isSuccessful) {
                        Log.d("GameEndAPI", "성공 ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<GameEndData>, t: Throwable) {
                    Log.d("GameEndAPI", "실패")
                }
            })

    }
}
