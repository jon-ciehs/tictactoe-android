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

    lateinit var board: Array<Array<Cell>>
    lateinit var state: GameState
    lateinit var turn: Player

    init {
        restart()
    }

    fun restart() {
        board = Array(sqSize) { Array(sqSize) { Cell() } }
        state = GameState.ALIVE
        turn = Player.X
    }

    fun getCell(row: Int, column: Int) : Cell {
        if (!validGet(row) || !validGet(column)) { throw IllegalArgumentException("Invalid position in the grid") }
        return board[row][column]
    }

    fun move(row: Int, column: Int): Boolean {
        return validMove(row, column).also {
            if (it) {
                getCell(row, column).player = turn
                if (checkWinner()) { state = GameState.ENDED } else changeTurn()
            }
        }
    }

    private fun checkWinner(): Boolean {
        for (row in 0 until sqSize) {
            for (column in 0 until sqSize) {
                val player: String = getCellValue(row, column)

                if (player == "-") continue

                if (column + 2 < sqSize &&
                        player == getCellValue(row, (column+1)) &&
                        player == getCellValue(row, (column+2))) { return true }

                if (row + 2 < sqSize) {
                    if (player == getCellValue(row+1, column) &&
                        player == getCellValue(row+2, column)) { return true }

                    if (column + 2 < sqSize &&
                        player == getCellValue(row+1, (column+1)) &&
                        player == getCellValue(row+2, (column+2))) { return true }

                    if (column - 2 >= 0 &&
                        player == getCellValue(row+1, (column-1)) &&
                        player == getCellValue(row+2, (column-2))) { return true }
                }
            }
        }
        return false
    }

    private fun getCellValue(row: Int, column: Int): String {
        return getCell(row, column).toString()
    }

    private fun changeTurn() { turn = if (turn == Player.X) Player.O else Player.X }

    private fun validGet(value: Int): Boolean = value in 0 until sqSize

    private fun validMove(row: Int, column: Int): Boolean = getCell(row, column).toString() == "-"

}
