package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var board = Array(9) { "" }  // מצב הלוח
    private var currentPlayer = "X"
    private var gameActive = true

    private lateinit var statusText: TextView
    private lateinit var playAgainButton: Button
    
    // רשימה של ה-ID של כל הכפתורים בלוח כדי להימנע מ-getIdentifier
    private val buttonIds = intArrayOf(
        R.id.button0, R.id.button1, R.id.button2,
        R.id.button3, R.id.button4, R.id.button5,
        R.id.button6, R.id.button7, R.id.button8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        playAgainButton = findViewById(R.id.playAgainButton)

        // אתחול טקסט הסטטוס עם המשאב הנכון
        statusText.text = getString(R.string.turn_status, currentPlayer)

        playAgainButton.setOnClickListener {
            resetGame()
        }
    }

    // פונקציה שמופעלת כשמישהו לוחץ על תא
    fun onCellClick(view: View) {
        if (!gameActive) return

        val button = view as Button
        val index = button.tag.toString().toInt()

        if (board[index] != "") return  // התא תפוס

        board[index] = currentPlayer
        button.text = currentPlayer

        // שינוי צבע הטקסט של הכפתור לפי השחקן
        if (currentPlayer == "X") {
            button.setTextColor(ContextCompat.getColor(this, R.color.playerXColor))
        } else {
            button.setTextColor(ContextCompat.getColor(this, R.color.playerOColor))
        }

        if (checkWin(currentPlayer)) {
            statusText.text = getString(R.string.player_wins, currentPlayer)
            statusText.textSize = 40f // הגדלת הטקסט בסיום
            if (currentPlayer == "X") {
                statusText.setTextColor(ContextCompat.getColor(this, R.color.playerXColor))
            } else {
                statusText.setTextColor(ContextCompat.getColor(this, R.color.playerOColor))
            }
            gameActive = false
            playAgainButton.visibility = View.VISIBLE
        } else if (isDraw()) {
            statusText.text = getString(R.string.draw)
            statusText.textSize = 40f
            statusText.setTextColor(ContextCompat.getColor(this, R.color.black))
            gameActive = false
            playAgainButton.visibility = View.VISIBLE
        } else {
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            statusText.text = getString(R.string.turn_status, currentPlayer)
            statusText.setTextColor(ContextCompat.getColor(this, R.color.black)) // צבע רגיל בזמן משחק
        }
    }

    // בדיקת ניצחון
    private fun checkWin(player: String): Boolean {
        val winPositions = arrayOf(
            intArrayOf(0, 1, 2), // שורות
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), // עמודות
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), // אלכסונים
            intArrayOf(2, 4, 6)
        )

        for (pos in winPositions) {
            if (board[pos[0]] == player &&
                board[pos[1]] == player &&
                board[pos[2]] == player) {
                return true
            }
        }
        return false
    }

    // בדיקת תיקו
    private fun isDraw(): Boolean {
        return board.none { it == "" }
    }

    // איפוס המשחק
    private fun resetGame() {
        board = Array(9) { "" }
        currentPlayer = "X"
        gameActive = true
        statusText.text = getString(R.string.turn_status, currentPlayer)
        statusText.textSize = 28f // החזרת הגודל המקורי
        statusText.setTextColor(ContextCompat.getColor(this, R.color.black))
        playAgainButton.visibility = View.GONE

        // ניקוי כל הכפתורים באמצעות ה-ID הישירים
        for (id in buttonIds) {
            val button = findViewById<Button>(id)
            button.text = ""
            button.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }
}
