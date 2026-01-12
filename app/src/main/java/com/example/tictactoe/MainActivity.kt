package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var board = Array(9) { "" }  // מצב הלוח
    private var currentPlayer = "X"
    private var gameActive = true

    private lateinit var statusText: TextView
    private lateinit var playAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        playAgainButton = findViewById(R.id.playAgainButton)

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

        if (checkWin(currentPlayer)) {
            statusText.text = "Player $currentPlayer wins!"
            gameActive = false
            playAgainButton.visibility = View.VISIBLE
        } else if (isDraw()) {
            statusText.text = "It's a draw!"
            gameActive = false
            playAgainButton.visibility = View.VISIBLE
        } else {
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            statusText.text = "Turn: $currentPlayer"
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
        statusText.text = "Turn: $currentPlayer"
        playAgainButton.visibility = View.GONE

        // ניקוי כל הכפתורים
        for (i in 0..8) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            findViewById<Button>(buttonId).text = ""
        }
    }
}
