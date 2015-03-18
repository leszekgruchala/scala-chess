package eu.gruchala.chess

import scala.util.{Failure, Success, Try}

object Play extends App {

  private val usageInfo =
    """
      |Here's how to play the game in sbt
      |runMain eu.gruchala.chess.Play 4 4 R R N N N N
      |
      |As the result of the program you will get printed all unique chess board combinations where
      |all of the pieces can be placed on the board without threatening each other.
      |
      |Available pieces:
      |K - King
      |Q - Queen
      |R - Rook
      |B - Bishop
      |N - Knight
      |""".stripMargin

  println("Thinking... please wait\n")
  val result = for {
    row    <- Try(args(0).toInt)
    col    <- Try(args(1).toInt)
    pieces <- Try(args.toList.slice(2, args.length))
  } yield Chess.combinations(row, col, pieces)
  result match {
    case Success(set) => println(s"The number of unique combinations for given board and pieces is ${set.size}")
    case Failure(e) => println(e + "\n" + usageInfo)
  }
}
