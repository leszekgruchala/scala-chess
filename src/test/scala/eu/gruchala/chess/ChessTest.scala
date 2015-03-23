package eu.gruchala.chess

import org.scalatest.{WordSpec, Matchers}

class ChessTest extends WordSpec with Matchers {

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
        Chess.combinations(1, 1, List("Q")) shouldBe Set(Set((Position(0, 0), Queen)))
      }
    }

    "given board 3 x 3" should {
      "return 4 unique board combinations with 2 Kings and 1 Rook" in {
        val combinations = Chess.combinations(3, 3, List("K", "K", "R"))

        combinations should have size 4
        combinations shouldBe Set(
          Set((Position(0, 0), King), (Position(2, 0), King), (Position(1, 2), Rook)),
          Set((Position(0, 0), King), (Position(0, 2), King), (Position(2, 1), Rook)),
          Set((Position(0, 2), King), (Position(2, 2), King), (Position(1, 0), Rook)),
          Set((Position(2, 0), King), (Position(2, 2), King), (Position(0, 1), Rook))
        )
      }
    }

    "given board 4 x 4" should {
      "return 16 unique board combinations with 1 Queen" in {
        Chess.combinations(4, 4, List("Q")) should have size 16
      }

      "return 8 unique board combinations with 2 Rooks and 4 Knights" in {
        Chess.combinations(4, 4, List("R", "R", "N", "N", "N", "N")) should have size 8
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
