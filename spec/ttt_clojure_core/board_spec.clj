(ns ttt-clojure.board.board-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-core.board :refer :all]))

(defn- initial-board []
  (vec (range 1 10)))

(defn- initial-4-board []
  (vec (range 1 17)))

(describe "the tic tac toe board"
  (it "has a board"
    (should= (initial-board) board))
  (it "has a size"
    (should= 3 (board-size (initial-board))))
  (it "make a move"
    (should= [1 2 "x" 4 5 6 7 8 9] (make-move board 3 "x")))
  (it "makes two moves"
    (should= [1 2 "x" 4 5 "o" 7 8 9] (make-move (make-move board 6 "o") 3 "x")))
  (it "returns true when the board is full"
    (should= true (board-full? ["X" "O" "X" "O" "X" "O" "X" "O" "X"])))
  (it "returns false when the board is not full"
    (should= false (board-full? (initial-board))))
  (it "returns false when the board is not full and has a move"
    (should= false (board-full? (make-move board 2 "X"))))

  (describe "splitting the board"
    (it "splits a 3x3 board evenly"
      (should= '((1 2 3) (4 5 6) (7 8 9)) (split-board (initial-board)))))
  (it "splits a 4x4 board evenly"
    (should= '((1 2 3 4)
               (5 6 7 8)
               (9 10 11 12)
               (13 14 15 16)) (split-board (initial-4-board))))

  (describe "Available moves"
    (it "knows when a given move is taken"
      (should= true (taken? ["X" 2 3
                              4  5 6
                              7  8 9] 1)))
    (it "has all the available moves"
      (should= (initial-board) (available-moves (initial-board))))
    (it "has has all the moves remaining"
      (should= '(2 3 4 5 6 7 8 9) (available-moves (make-move board 1 "X"))))
    (it "has 2 moves remaining"
      (should= '(2 3) (available-moves ["X"  2   3
                                        "O" "X" "O"
                                        "X" "O" "X"])))
    )

  (describe "the board angles"
    (it "has rows"
      (should= [[1 2 3] [4 5 6] [7 8 9]] (rows (initial-board))))
    (it "has columns"
      (should= [[1 4 7] [2 5 8] [3 6 9]] (columns (initial-board))))
    (it "has diagonals"
      (should= [[1 5 9] [3 5 7]] (diagonals (initial-board))))))
