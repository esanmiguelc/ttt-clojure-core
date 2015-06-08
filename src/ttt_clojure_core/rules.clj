(ns ttt-clojure-core.rules
  (:require [ttt-clojure-core.board :as board :only [:board-full? :rows :columns :diagonals :board-size]]))

(def first-participant
  "X")

(def second-participant
  "O")

(defn- combination-check [combination mark]
  (if (some true? (map #(apply = mark %) combination)) true false))

(defn row-winner? [board mark]
  (combination-check (board/rows board) mark))

(defn column-winner? [board mark]
  (combination-check (board/columns board) mark))

(defn diagonal-winner? [board mark]
  (combination-check (board/diagonals board) mark))

(defn winner? [board mark]
   (or (row-winner? board mark)
        (column-winner? board mark)
        (diagonal-winner? board mark)))

(defn- board-even? [board]
  (even? (board/board-size board)))

(defn current-participant? [board]
  (if (board-even? board)
    (if (odd? (count (board/available-moves board)))
      second-participant
      first-participant)
    (if (odd? (count (board/available-moves board)))
      first-participant
      second-participant)
    )
  )

(defn my-turn? [board my-mark]
  (= my-mark (current-participant? board)))

(defn switch-participant [participant-one participant-two]
  participant-two)

(defn game-over? [board]
  (or (winner? board first-participant) 
      (winner? board second-participant) 
      (board/board-full? board)))

(defn contains-mark? [element mark]
  (= element mark))
