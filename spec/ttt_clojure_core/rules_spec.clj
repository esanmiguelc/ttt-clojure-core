(ns ttt-clojure-core.rules-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-core.rules :refer :all]))

(defn full-board []
  ["X" "O" "X"
   "X" "O" "O"
   "O" "X" "X"])

(defn- winner-left-to-right []
  ["X" 2   3
   4 "X"  6
   7  8  "X"])

(defn- winner-column []
  ["X"  2   3
   "X"  5   6 
   "X"  8   9])

(defn- winner-row []
  [ 1   2   3
   "X" "X" "X"
   7   8   9])

(describe "Rules"
  (describe "is there a winner?"
    (it "returns false when board is empty"
      (should= false (winner? (vec (range 1 10)) "X")))

    (it "returns false when the player has played but not won"
      (should= false (winner? ["X"  2  "X"
                               4   5   6
                               7   8   9] "X")))

    (it "returns true when the mark has hit three in a row on first row"
      (should= true (winner? (winner-row) "X")))

    (it "returns true when the mark has hit three in a row on column"
      (should= true (winner? (winner-column) "X")))

    (it "returns true when the mark has hit three in a row on diagonal"
      (should= true (winner? (winner-left-to-right) "X"))))

  (describe "same player in row"
    (it "is true when the player took a row"
      (should= true (row-winner? (winner-row) "X")))
    (it "is false when not winner"
      (should= false (row-winner? [ 1   2   3
                                   "X"  5  "X"
                                   7   8   9] "X"))))

  (describe "same player in column"
    (it "is true when the player took a column"
      (should= true (column-winner? (winner-column) "X")))

    (it "is false when the player has not taken a column"
      (should= false (column-winner? ["X"  2   3
                                      4  "X"  6 
                                      "X"  8   9] "X"))))
  (describe "winning in diagonals"
    (it "should win in a left to right combination"
      (should= true (diagonal-winner? (winner-left-to-right)  "X")))

    (it "should win in a right to left combination"
      (should= true (diagonal-winner? [ 1   2  "O"
                                        4  "O"  6
                                       "O"  8   9] "O"))))

  (describe "contains a mark"
    (it "does not contain a mark"
      (should= false (contains-mark? 1 "X"))) 

    (it "does contain a mark"
      (should= true (contains-mark? "X" "X")))) 

  (describe "current participant"
    (it "returns X for first turn"
      (should= "X" (current-participant? (vec (range 1 10)))))

    (it "returns O for second turn"
      (should= "O" (current-participant? ["X" 2 3
                                           4  5 6
                                           7  8 9] )))
    (it "returns x for third turn"
      (should= "X" (current-participant? ["X" 2  3
                                           4 "O" 6
                                           7  8  9])))
    (it "returns O for sixth turn"
      (should= "O" (current-participant?  ["X" "O" "X"
                                            4  "O"  6
                                           "X"  8   9])))
    )

  (describe "my turn to play?"
    (it "is true when it is players' turn"
      (should= true (my-turn? (vec (range 1 10)) "X")))
    (it "is false when it is not a players' turn"
      (should= false (my-turn? (vec (range 1 10)) "O")))
    )

  (describe "switch participant"
    (it "changes the current mark"
      (should= "O" (switch-participant "X" "O"))))

  (describe "Game over"
    (it "is false when there are no winners"
      (should= false (game-over? (vec (range 1 10)))))

    (it "returns true when there is a winner"
      (should= true (game-over? (winner-column))))

    (it "returns over when the board is full and there are no winners"
      (should= true (game-over? (full-board))))))

