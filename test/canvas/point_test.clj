(ns canvas.point-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.point :refer :all]
            [canvas.rectangle :refer :all]))

(let [canvas               (canvas 10 8)
      canvas-with-rectange (rectangle canvas 2 2 8 6)]
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
             (is (= canvas-with-rectange (point canvas-with-rectange "*" 2 2))))))

(run-tests)
