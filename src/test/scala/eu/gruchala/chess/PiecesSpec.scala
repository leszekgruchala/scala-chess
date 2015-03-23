package eu.gruchala.chess

import org.scalatest.{FlatSpec, Matchers}

import scala.language.implicitConversions

object Implicits {
  implicit def tupleToPosition(pos: (Int, Int)): Position = Position(pos._1, pos._2)
}

class PiecesSpec extends FlatSpec with Matchers {
  import Implicits._

  val position = (3, 3)
  val board: Set[Position] =
    (for {
      row <- 0 to 5
      col <- 0 to 5
    } yield Position(row, col)).toSet

  def performTest(expectedMoves: Set[Position], piece: Piece) = {
    val illegalMoves = (board - position) -- expectedMoves
    expectedMoves foreach(piece.threatens(position, _) shouldBe true)
    illegalMoves foreach(piece.threatens(position, _) shouldBe false)
  }

  val rookMoves: Set[Position] = Set((0, 3), (1, 3), (2, 3), (4, 3), (5, 3), (3, 0), (3, 1), (3, 2), (3, 4), (3, 5))
  val bishopMoves: Set[Position] = Set((1, 5), (2, 4), (4, 2), (5, 1), (0, 0), (1, 1), (2, 2), (4, 4), (5, 5))
  val queenMoves: Set[Position] = bishopMoves ++ rookMoves

  behavior of "Pieces movements"

  it should "be right for Queen" in {
    performTest(queenMoves, Queen)
  }

  it should "be right for Rook" in {
    performTest(rookMoves, Rook)
  }

  it should "be right for Bishop" in {
    performTest(bishopMoves, Bishop)
  }

  it should "be right for Knight" in {
    val knightMoves: Set[Position] = Set((1, 2), (1, 4), (2, 1), (2, 5), (4, 1), (4, 5), (5, 2), (5, 4))
    performTest(knightMoves, Knight)
  }

  it should "be right for King" in {
    val kingMoves: Set[Position] = Set((2, 2), (2, 3), (2, 4), (3, 2), (3, 4), (4, 2), (4, 3), (4, 4))
    performTest(kingMoves, King)
  }
}
