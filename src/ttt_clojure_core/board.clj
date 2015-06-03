(ns ttt-clojure-core.board)

(defn board-size [board]
  (int (Math/sqrt (count board))))

(defn split-board [board]
  (partition (board-size board) board))

(defn- left-to-right [board]
  (take-nth (inc (board-size board)) board))

(defn- right-to-left [board]
  (rest (drop-last (take-nth (dec (board-size board)) board))))

(def board
  (vec (range 1 10))) 

(defn make-move [board position mark]
  (assoc board (dec position) mark))

(defn board-full? [board] 
  (every? string? board))

(defn available-moves [board]
  (filter integer? board))

(defn taken? [board move]
  (if (some #(= move %) board) false true))

(defn rows [board]
  (split-board board))

(defn columns [board]
  (apply mapv vector (split-board board)))

(defn diagonals [board]
  (vector (left-to-right board) (right-to-left board)))
