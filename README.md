Scala Chess
===========

Solution for variation of "Eight queens puzzle"


This project provides a solution for a problem to find all unique configurations of a set of normal chess pieces
on a chess board with dimensions M×N where none of the pieces is in a position to take any of the others. 
Assuming the colour of the piece does not matter, and that there are no pawns among the pieces.

How to run
---------
From `sbt`

```
runMain eu.gruchala.chess.Play 4 4 R R Kt Kt Kt Kt
```

Available pieces:

 * K - King
 * Q - Queen
 * R - Rook
 * B - Bishop
 * N - Knight