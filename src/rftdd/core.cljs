(ns ^:figwheel-hooks rftdd.core
  (:require
    [goog.dom :as gdom]
    [reagent.dom :as rd]
    [re-frame.core :as rf]))


;; https://docs.thecatapi.com/api-reference/breeds/breeds-list
(def cat-breeds-url "https://api.thecatapi.com/v1/breeds")

;; Example http effect
#_{:http-xhrio {:method          :get
                :uri             "https://example.com"
                :response-format (ajax/json-response-format {:keywords? true})
                :on-success      [:good-http-result]}}

;;;;; STATE ;;;;;
(def initial-state
  {})

;;;;; EVENTS ;;;;;
(rf/reg-event-fx
  :initialize-db
  (fn [{:keys [db]}]
    {:db initial-state}))

;;;;; SUBSCRIPTIONS ;;;;;

;;;;; VIEW ;;;;;
(defn hello-world []
  [:div "Hello world!"])

;;;;; INIT APP ;;;;;
(defn ^:after-load mount-app-element []
  (when-let [el (gdom/getElement "app")]
    (rd/render [hello-world] el)))

(defn init-state! []
  (rf/dispatch-sync [:initialize-db]))

(defn ^:export init []
  (init-state!)
  (mount-app-element))
