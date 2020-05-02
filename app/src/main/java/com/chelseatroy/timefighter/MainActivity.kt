package com.chelseatroy.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var timeRemainingTextView: TextView
    internal lateinit var scoreTextView: TextView

    internal lateinit var tapMeButton: Button

    internal var score = 0
    internal var gameStarted = false

    internal lateinit var countDownTimer: CountDownTimer
    internal val start: Long = 5000
    internal var increment: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeRemainingTextView = findViewById(R.id.timerTextView)
        scoreTextView = findViewById(R.id.gameScoreTextView)
        tapMeButton = findViewById(R.id.button)
        setUpGame()

        tapMeButton.setOnClickListener({ view ->
            incrementScore()
        })
    }

    private fun setUpGame() {
        gameStarted = false
        score = 0

        scoreTextView.setText(getString(R.string.your_score_1_d, score))
        timeRemainingTextView.setText(
            getString(
                R.string.time_remaining_1_d,
                this.start / this.increment
            )
        )

        countDownTimer = object : CountDownTimer(start, increment) {
            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {

                timeRemainingTextView.setText(
                    getString(
                        R.string.time_remaining_1_d,
                        millisUntilFinished / increment
                    )
                )
            }
        }
    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }

        score += 1
        scoreTextView.setText(getString(R.string.your_score_1_d, score))
    }


    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_over, score), Toast.LENGTH_LONG).show()
        setUpGame()
    }
}
