(ns canvas.point-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.point :refer :all]
            [canvas.rectangle :refer :all]))

(let [canvas               (canvas 10 8)
      canvas-with-rectange (rectangle canvas 2 2 8 6)]

  (deftest get-point-by-coordinates
    (testing "is within canvas"
             (is (= " " (at canvas 1 1)))
             (is (= "x" (at canvas-with-rectange 2 2))))

    (testing "is outside canvas"
             (is (nil? (at canvas -1 1)))
             (is (nil? (at canvas 20 2))))

    (testing "is on border"
             (is (= "-" (at canvas 5 0)))
             (is (= "|" (at canvas 0 5)))))

  (deftest valid-points
    (testing "is within canvas"
             (is
              (=
               (list
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " " " " " "*" " " " " " " " " "|")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-"))
               (point canvas "*" 4 5))))

    (testing "is on blank space"
             (is
              (=
               (list
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-")
                (list "|" " " " " " " " " " " " " " " " " "|")
                (list "|" " " "x" "x" "x" "x" "x" "x" "x" "|")
                (list "|" " " "x" " " " " " " " " " " "x" "|")
                (list "|" " " "x" " " " " " " " " " " "x" "|")
                (list "|" " " "x" " " "*" " " " " " " "x" "|")
                (list "|" " " "x" "x" "x" "x" "x" "x" "x" "|")
                (list "-" "-" "-" "-" "-" "-" "-" "-" "-" "-"))
               (point canvas-with-rectange "*" 4 5)))))

  (deftest invalid-points
    (testing "is outside canvas"
             (is (= canvas (point canvas "*" 24 5))))

    (testing "is on a occupied spot"
             (is (= canvas-with-rectange (point canvas-with-rectange "*" 2 2)))))

  (deftest empty-detection
    (testing "empty cell"
             (is (true? (is-empty? canvas [3 3]))))

    (testing "top border"
             (is (false? (is-empty? canvas [3 0]))))

    (testing "left border"
             (is (false? (is-empty? canvas [0 3]))))

    (testing "right border"
             (is (false? (is-empty? canvas [0 7]))))

    (testing "bottom border"
             (is (false? (is-empty? canvas [9 3]))))

    (testing "negative index"
             (is (false? (is-empty? canvas [-9 3]))))

    (testing "too large index"
             (is (false? (is-empty? canvas [900 3]))))

    (testing "occupied point"
             (is (false? (is-empty? canvas-with-rectange [2 2])))))

  (deftest neighbouring-points
    (testing "returns all 8 neighbours if empty"
             (is (= [[2 2] [3 2] [4 2] [2 3] [4 3] [2 4] [3 4] [4 4]] (neighbours canvas 3 3))))

    (testing "returns only empty cells around"
             (is (= [[2 1] [1 2]] (neighbours canvas-with-rectange 1 1))))

    (testing "returns empty vector when all neighbours occupied"
             (let [canvas-with-small-rectangle (rectangle canvas 1 1 3 3)]
             (is (= [] (neighbours canvas-with-small-rectangle 2 2)))))))


(run-tests)
