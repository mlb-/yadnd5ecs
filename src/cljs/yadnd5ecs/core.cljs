(ns yadnd5ecs.core
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]))

(enable-console-print!)

(def app-state (atom {:text "Hello Chestnut!"}))

(defcomponent app-view
  [app owner]
  (render [_]
          (dom/h1 (:text app))))

(defn main []
  (om/root
    app-view
    app-state
    {:target (. js/document (getElementById "app"))}))
