
package com.example.sanggame

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {

    private var userScore = 0
    private var computerScore = 0

    private lateinit var resultText: TextView
    private lateinit var scoreText: TextView

    private val choices = listOf("سنگ", "کاغذ", "قیچی")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(16, 24, 39))
        }

        fun addText(text: String, size: Float): TextView {
            return TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                setPadding(8, 16, 8, 16)
                layout.addView(this)
            }
        }

        fun addButton(text: String, action: () -> Unit) {
            val button = Button(this).apply {
                this.text = text
                setOnClickListener { action() }
            }
            layout.addView(
                button,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }

        addText("سنگ، کاغذ، قیچی", 28f)
        resultText = addText("یکی را انتخاب کن!", 20f)
        scoreText = addText("", 18f)

        addButton("🪨 سنگ") { play("سنگ") }
        addButton("📄 کاغذ") { play("کاغذ") }
        addButton("✂️ قیچی") { play("قیچی") }

        addButton("شروع دوباره") {
            userScore = 0
            computerScore = 0
            resultText.text = "بازی از نو شروع شد!"
            updateScore()
        }

        setContentView(layout)
        updateScore()
    }

    private fun play(userChoice: String) {
        val computerChoice = choices[Random.nextInt(choices.size)]

        val result = when {
            userChoice == computerChoice -> "مساوی شدید!"

            (userChoice == "سنگ" && computerChoice == "قیچی") ||
            (userChoice == "کاغذ" && computerChoice == "سنگ") ||
            (userChoice == "قیچی" && computerChoice == "کاغذ") -> {
                userScore++
                "آفرین! شما برنده شدید 🎉"
            }

            else -> {
                computerScore++
                "کامپیوتر برنده شد!"
            }
        }

        resultText.text =
            "انتخاب شما: $userChoice\n" +
            "انتخاب کامپیوتر: $computerChoice\n\n$result"

        updateScore()
    }

    private fun updateScore() {
        scoreText.text = "شما: $userScore | کامپیوتر: $computerScore"
    }
}
