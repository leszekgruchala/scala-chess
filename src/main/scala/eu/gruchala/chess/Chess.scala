package eu.gruchala.chess

object Chess {

  def combinations(rows: Int, cols: Int, definitions: Seq[String]): Set[Set[(Position, Piece)]] = {
    val cleanBoard: Set[Position] =
      (for {
        row <- 0 until rows
        col <- 0 until cols
      } yield Position(row, col)).toSet

    def placePieces(defs: Seq[String], board: Set[Position], acc: Set[(Position, Piece)]): Set[Set[(Position, Piece)]] = defs match {
      case Nil => Set(acc)
      case head :: tail =>
        val piece = Piece(head)
        board
          .withFilter(position => !acc.exists(used => piece.threatens(position, used._1)))
          .flatMap(position =>
            placePieces(
              tail,
              board.filterNot(freePosition => position == freePosition || piece.threatens(position, freePosition)),
              acc + ((position, piece)))
          )
    }

    placePieces(definitions, cleanBoard, Set.empty)
  }
}