(ns ttt-clojure-core.participants.hard-ai
  (:require [ttt-clojure-core.rules :as rules :refer :all]
            [ttt-clojure-core.board :as board :refer :all]
            [ttt-clojure-core.runner.game-runner :as runner :refer :all]))

(defn- score-board [board my-mark opponent-mark]
  (cond
    (rules/winner? board my-mark) 10
    (rules/winner? board opponent-mark) -10
    :else 0))

(defn- minimax [board my-mark opponent depth]
  (let [available-moves (board/available-moves board)]
    (if (or (rules/game-over? board) (> depth 5)) 
      (/ (score-board board my-mark opponent) depth)
      (if (rules/my-turn? board my-mark)
        (apply max (map #(minimax (board/make-move board % my-mark) 
                                  my-mark opponent (inc depth)) available-moves))
        (apply min (map #(minimax (board/make-move board % opponent) 
                                  my-mark opponent (inc depth)) available-moves))))))

(defn get-move [board my-mark opponent]
  (loop [available-moves (board/available-moves board) 
         best-move nil
         best-score Double/NEGATIVE_INFINITY]
    (if (empty? available-moves)
      best-move
      (let [score (minimax (board/make-move board (first available-moves) my-mark) my-mark opponent 1)]
        (if (> score best-score)
          (recur (rest available-moves) (first available-moves) score)
          (recur (rest available-moves) best-move best-score))))))
