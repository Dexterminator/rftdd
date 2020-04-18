(ns rftdd.test-utils
  (:require
    [cljs.test :refer-macros [is] :refer [empty-env]]
    [re-frame.core :as rf]
    [cljs-test-display.core :as display]
    [goog.dom.classlist :as classlist]
    [goog.dom :as gdom]
    [cljs.pprint :as pp]
    [pjstadig.print :as p]
    [pjstadig.util :as util]
    [rftdd.core :refer [init-state!]]
    [clojure.string :as str]))

(def dummy-env (empty-env))

(defn setup-diffs! []
  (set! display/add-fail-node!
        (fn add-fail-node! [m]
          (let [out (binding [cljs.test/*current-env* dummy-env] ;; we don't want `humane-test-output` to modify the env
                      (with-out-str
                        (util/report- (p/convert-event m))))
                clean-out (->> (str/split out #"\n")
                               (drop-while #(not (str/starts-with? % "expected")))
                               (str/join "\n")
                               (str (with-out-str (pp/pprint (:expected m))) "\n"))
                node (display/div :test-fail
                                  (display/contexts-node)
                                  (display/div :fail-body
                                               (when-let [message (:message m)]
                                                 (display/div :test-message message))
                                               (display/n :pre {}
                                                          (display/n :code {} clean-out))))
                curr-node (display/current-node)]
            (js/console.log clean-out)
            (classlist/add curr-node "has-failures")
            (classlist/add (display/current-node-parent) "has-failures")
            (gdom/appendChild curr-node node)))))

(defn mock-api [expected-uri response-data]
  (rf/reg-fx
    :api
    (fn [{:keys [uri on-success]}]
      (is (= expected-uri uri))
      (rf/dispatch (conj on-success response-data)))))
