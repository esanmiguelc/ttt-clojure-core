(ns ttt-clojure-core.participants.easy-ai
  (:require [ttt-clojure-core.board :as board :refer :all]))

(defn take-turn [board]
  (dec (rand-nth (board/available-moves board))))
