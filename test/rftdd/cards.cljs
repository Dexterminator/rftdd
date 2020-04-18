(ns rftdd.cards
  (:require
    [rftdd.core :as core]
    [devcards.core :as dc :refer-macros [defcard-rg]]
    [day8.re-frame-10x.inlined-deps.reagent.v0v10v0.reagent.core :as r]))

(defcard-rg example
  [core/hello-world])

(dc/start-devcard-ui!)
