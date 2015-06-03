(ns ttt-clojure-core.participants.easy-ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-core.participants.easy-ai :refer :all]))

(describe "Easy AI"
  (it "takes a number from the board"
    (should-be-a Long (take-turn (vec (range 1 10))))))
