(ns ttt-clojure-core.runner.game-runner
  (:require [ttt-clojure-core.board :as board :only [:make-move]]
            [ttt-clojure-core.rules :as rules :only [:switch-participant]]))

(defn play [board first-participant second-participant moves]
  (loop [board board
         moves moves]
    (if (empty? moves)
      board 
      (recur
        (board/make-move board (first moves) (rules/current-participant? board))
        (rest moves)))))


