(ns yadnd5ecs.test-runner
  (:require
   [cljs.test :refer-macros [run-tests]]
   [yadnd5ecs.core-test]))

(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'yadnd5ecs.core-test))
    0
    1))
