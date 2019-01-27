(ns canvas.point-test
  (:require [clojure.test :refer :all]
            [canvas.canvas :refer :all]
            [canvas.point :refer :all]))

(let [canvas (canvas 10 8)]
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
               (point canvas "*" 4 5))))))

(run-tests)
