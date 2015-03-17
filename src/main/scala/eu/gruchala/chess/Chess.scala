package eu.gruchala.chess

object Chess {

  def combinations(rows: Int, cols: Int, definitions: Seq[String]): Set[Set[Piece]] = {
    val cleanBoard: Set[Position] =
      (for {
        row <- 0 until rows
        col <- 0 until cols
      } yield Position(row, col)).toSet

    def toType(kind: String)(position: Position): Piece = kind match {
      case "K" => King(position)
      case "R" => Rook(position)
      case "Q" => Queen(position)
      case "B" => Bishop(position)
      case "N" => Knight(position)
      case unknown => throw new IllegalStateException(s"Piece type $unknown is not supported")
    }

    def placePieces(defs: Seq[String], board: Set[Position], acc: Set[Piece]): Set[Set[Piece]] = defs match {
      case Nil => Set(acc)
      case head :: tail =>
        board
          .map(toType(head))
          .withFilter(piece => !acc.exists(usedPiece => piece.threatens(usedPiece.position)))
          .flatMap(piece =>
            placePieces(
              tail,
              board.filterNot(freePosition => piece.position == freePosition || piece.threatens(freePosition)),
              acc + piece)
          )
    }

    placePieces(definitions, cleanBoard, Set.empty)
  }
}