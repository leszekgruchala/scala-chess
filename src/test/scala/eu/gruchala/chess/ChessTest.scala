package eu.gruchala.chess

import org.scalatest.{WordSpec, Matchers}

import scala.language.implicitConversions

object Implicits {
  implicit def tupleToPosition(pos: (Int, Int)): Position = Position(pos._1, pos._2)
}

class ChessTest extends WordSpec with Matchers {
  import Implicits._

  "Chess game" when {
    "given board 0 x 0" should {
      "return 0 unique board combinations with 0 pieces" in {
        Chess.combinations(0, 0, List.empty) shouldBe Set(Set.empty)
      }
      "return 0 unique board combinations with 1 Queen" in {
        Chess.combinations(0, 0, List("Q")) shouldBe Set()
      }
    }

    "given board 1 x 1" should {
      "return 0 unique board combinations with 0 pieces" in {
        Chess.combinations(1, 1, List.empty) shouldBe Set(Set.empty)

      }
      "return 1 unique board combinations with 1 Queen" in {
        Chess.combinations(1, 1, List("Q")) shouldBe Set(Set(Queen(0, 0)))
      }
    }

    "given board 3 x 3" should {
      "return 4 unique board combinations with 2 Kings and 1 Rook" in {
        val combinations = Chess.combinations(3, 3, List("K", "K", "R"))

        combinations should have size 4
        combinations shouldBe Set(
          Set(King((0, 0)), King((2, 0)), Rook((1, 2))),
          Set(King((0, 0)), King((0, 2)), Rook((2, 1))),
          Set(King((0, 2)), King((2, 2)), Rook((1, 0))),
          Set(King((2, 0)), King((2, 2)), Rook((0, 1)))
        )
      }
    }

    "given board 4 x 4" should {
      "return 16 unique board combinations with 1 Queen" in {
        Chess.combinations(4, 4, List("Q")) should have size 16
      }

      "return 8 unique board combinations with 2 Rooks and 4 Knights" in {
        val combinations = Chess.combinations(4, 4, List("R", "R", "N", "N", "N", "N"))

        combinations should have size 8
        combinations shouldBe Set(
          Set(Knight((3, 2)), Rook((0, 1)), Rook((2, 3)), Knight((1, 2)), Knight((3, 0)), Knight((1, 0))),
          Set(Knight((3, 3)), Rook((2, 2)), Knight((1, 3)), Knight((3, 1)), Rook((0, 0)), Knight((1, 1))),
          Set(Rook((3, 3)), Knight((2, 2)), Knight((0, 0)), Knight((0, 2)), Rook((1, 1)), Knight((2, 0))),
          Set(Knight((3, 3)), Knight((1, 3)), Knight((3, 1)), Rook((0, 2)), Knight((1, 1)), Rook((2, 0))),
          Set(Knight((0, 1)), Knight((2, 3)), Knight((2, 1)), Knight((0, 3)), Rook((1, 2)), Rook((3, 0))),
          Set(Rook((3, 2)), Knight((0, 1)), Knight((2, 3)), Knight((2, 1)), Knight((0, 3)), Rook((1, 0))),
          Set(Knight((3, 2)), Rook((2, 1)), Rook((0, 3)), Knight((1, 2)), Knight((3, 0)), Knight((1, 0))),
          Set(Knight((2, 2)), Rook((1, 3)), Rook((3, 1)), Knight((0, 0)), Knight((0, 2)), Knight((2, 0))))
      }
    }
  }
}

class Solution extends WordSpec with Matchers {
  "Chess game with expensive calculation" when {
    "given board 7 x 7" should {
      "return 3063828 unique board combinations with 2 Kings, 2 Queens, 2 Bishops and 1 Knight" in {
        Chess.combinations(7, 7, List("K", "K", "Q", "Q", "B", "B", "N")) should have size 3063828
      }
    }
  }
}
