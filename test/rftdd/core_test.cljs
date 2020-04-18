(ns rftdd.core-test
  (:require
    [cljs.test :refer-macros [deftest is testing]]
    [re-frame.core :as rf]
    [day8.re-frame.test :as rf-test]
    [rftdd.test-utils :as test-utils]
    [rftdd.core :refer [init-state!]]))

(test-utils/setup-diffs!)

(deftest main-test
  (rf-test/run-test-sync
    (init-state!)

    (testing "Initial app state is valid")))
