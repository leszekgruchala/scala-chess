package eu.gruchala.chess

case class Position(row: Int, col: Int)

trait Piece {
  def threatens(from: Position, on: Position): Boolean
}

object Piece {

  def apply(`type`: String) = `type` match {
    case "K" => King
    case "R" => Rook
    case "Q" => Queen
    case "B" => Bishop
    case "N" => Knight
    case unknown => throw new IllegalStateException(s"Piece type $unknown is not supported")
  }

  private[chess] object Queen extends Piece {

    override def threatens(from: Position, on: Position): Boolean =
      sameRow(from, on) || sameCol(from, on) || diagonal(from, on)
  }

  private[chess] object Rook extends Piece {

    override def threatens(from: Position, on: Position): Boolean =
      sameRow(from, on) || sameCol(from, on)
  }

  private[chess] object Bishop extends Piece {

    override def threatens(from: Position, on: Position): Boolean =
      diagonal(from, on)
  }

  private[chess] object Knight extends Piece {

    override def threatens(from: Position, on: Position): Boolean = {
      val toRow = accordingToRow(from, on)
      val toCol = accordingToCol(from, on)
      (toRow == 2 && toCol == 1) || (toRow == 1 && toCol == 2)
    }
  }

  private[chess] object King extends Piece {

    override def threatens(from: Position, on: Position): Boolean =
      accordingToRow(from, on) <= 1 && accordingToCol(from, on) <= 1
  }

  private def accordingToRow(from: Position, on: Position): Int = (from.row - on.row).abs
  private def accordingToCol(from: Position, on: Position): Int = (from.col - on.col).abs
  private def sameRow(from: Position, on: Position): Boolean = from.row == on.row
  private def sameCol(from: Position, on: Position): Boolean = from.col == on.col
  private def diagonal(from: Position, on: Position): Boolean = accordingToRow(from, on) == accordingToCol(from, on)
}