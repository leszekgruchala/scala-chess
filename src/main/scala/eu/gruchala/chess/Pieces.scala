package eu.gruchala.chess

case class Position(row: Int, col: Int)

sealed trait Piece {
  def position: Position
  def threatens(on: Position): Boolean
  protected def accordingToRow(on: Position): Int = (position.row - on.row).abs
  protected def accordingToCol(on: Position): Int = (position.col - on.col).abs
  protected def sameRow(on: Position): Boolean = position.row == on.row
  protected def sameCol(on: Position): Boolean = position.col == on.col
  protected def diagonal(on: Position): Boolean = accordingToRow(on) == accordingToCol(on)
}

case class Queen(position: Position) extends Piece {

  override def threatens(on: Position): Boolean =
    sameRow(on) || sameCol(on) || diagonal(on)
}

case class Rook(position: Position) extends Piece {

  override def threatens(on: Position): Boolean =
    sameRow(on) || sameCol(on)
}

case class Bishop(position: Position) extends Piece {

  override def threatens(on: Position): Boolean =
    diagonal(on)
}

case class Knight(position: Position) extends Piece {

  override def threatens(on: Position): Boolean = {
      val toRow = accordingToRow(on)
      val toCol = accordingToCol(on)
      (toRow == 2 && toCol == 1) || (toRow == 1 && toCol == 2)
  }
}

case class King(position: Position) extends Piece {

  override def threatens(on: Position): Boolean =
    accordingToRow(on) <= 1 && accordingToCol(on) <= 1
}