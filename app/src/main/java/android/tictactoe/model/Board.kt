package android.tictactoe.model

import kotlin.Array

enum class Player { X, O }

enum class GameState { ALIVE, ENDED }

class Cell(var player: Player? = null) {

    override fun toString(): String {
        return when (this.player) {
            Player.X -> "X"
            Player.O -> "O"
            else -> "-"
        }
    }
}

class Board() {

    private val sqSize: Int = 3
    val board: Array<Array<Cell>> = Array(sqSize) { Array(sqSize) { Cell() } }
    var state: GameState? = null
    var turn: Player? = Player.X

    fun move(row: Int, column: Int): Boolean {
        return validMove(row, column).also {
            if (it) {
                getCell(row, column)?.player = turn
                changeTurn()
            }
        }
    }

    private fun changeTurn() { turn = if (turn == Player.X) Player.O else Player.X }

    private fun getCell(row: Int, column: Int) : Cell? {
        if (!validGet(row) || !validGet(column)) { throw IllegalArgumentException("Invalid position in the grid") }
        return board[row][column]
    }

    private fun validGet(value: Int): Boolean = value in 0 until sqSize

    private fun validMove(row: Int, column: Int): Boolean = getCell(row, column).toString() == "-"

}



