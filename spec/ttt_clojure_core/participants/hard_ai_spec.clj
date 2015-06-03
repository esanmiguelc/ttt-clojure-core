(ns ttt-clojure-core.participants.hard-ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-core.participants.hard-ai :refer :all]))

(describe "getting the best move"
  (it "takes 8 to win the game"
    (should= 8 (get-move ["X" "O" "X"
                          4  "O"  6
                          "X"  8   9] "O" "X")))

  (it "takes 2 to block the move"
    (should= 2 (get-move ["X" 2 "X"
                          4 "O" 6
                          7  8  9] "O" "X")))

  (it "takes last move available"
    (should= 9 (get-move  ["X" "O" "X"
                           "O" "X" "O"
                           "O" "X"  9] "X" "O")))

  (it "takes the middle"
    (should= 5 (get-move ["X"  2   3
                          4   5   6
                          7   8   9] "O" "X")))
  )

