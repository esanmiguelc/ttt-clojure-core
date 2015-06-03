(ns ttt-clojure-core.runner.game-runner-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-core.runner.game-runner :refer :all]))

(defn empty-board []
  (vec (range 1 10)))

(describe "Runs the game"
  (it "plays out one move"
    (should= ["X" 2 3 4 5 6 7 8 9] (play (empty-board) "X" "O" [1])))

  (it "plays out two moves"
    (should= ["X" 2  3
              4 "O" 6 
              7  8  9] (play (empty-board) "X" "O" [1 5])))

  (it "plays out three moves"
    (should= ["X" 2  3
              4 "O" 6 
              7  8  "X"] (play (empty-board) "X" "O" [1 5 9])))
  )

