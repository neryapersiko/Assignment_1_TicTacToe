package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var board = Array(9) { "" }  // Board state
    private var currentPlayer = "X"
    private var gameActive = true

    private lateinit var statusText: TextView
    private lateinit var playAgainButton: Button
    
    // List of IDs for all grid buttons to avoid using getIdentifier
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

        // Initialize status text with the correct resource
        statusText.text = getString(R.string.turn_status, currentPlayer)

        playAgainButton.setOnClickListener {
            resetGame()
        }
    }

    // Function triggered when a cell is clicked
    fun onCellClick(view: View) {
        if (!gameActive) return

        val button = view as Button
        val index = button.tag.toString().toInt()

        if (board[index] != "") return  // Cell is already occupied

        board[index] = currentPlayer
        button.text = currentPlayer

        // Change button text color based on the current player
        if (currentPlayer == "X") {
            button.setTextColor(ContextCompat.getColor(this, R.color.playerXColor))
        } else {
            button.setTextColor(ContextCompat.getColor(this, R.color.playerOColor))
        }

        if (checkWin(currentPlayer)) {
            statusText.text = getString(R.string.player_wins, currentPlayer)
            statusText.textSize = 40f // Increase text size on win
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
            statusText.setTextColor(ContextCompat.getColor(this, R.color.black)) // Default color during gameplay
        }
    }

    // Check for a winning combination
    private fun checkWin(player: String): Boolean {
        val winPositions = arrayOf(
            intArrayOf(0, 1, 2), // Rows
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), // Columns
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), // Diagonals
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

    // Check for a draw
    private fun isDraw(): Boolean {
        return board.none { it == "" }
    }

    // Reset the game to initial state
    private fun resetGame() {
        board = Array(9) { "" }
        currentPlayer = "X"
        gameActive = true
        statusText.text = getString(R.string.turn_status, currentPlayer)
        statusText.textSize = 28f // Reset text size
        statusText.setTextColor(ContextCompat.getColor(this, R.color.black))
        playAgainButton.visibility = View.GONE

        // Clear all buttons using direct IDs
        for (id in buttonIds) {
            val button = findViewById<Button>(id)
            button.text = ""
            button.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }
}
