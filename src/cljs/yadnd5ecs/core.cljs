(ns yadnd5ecs.core
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]))

(enable-console-print!)

(def app-state (atom {:name {:player "Bob"
                             :character "Bruenor"}}))

(defcomponent name-view
  [name owner]
  (render
   [_]
   (let [{:keys [player
                 character]} name]
     (dom/span
      (dom/h1 (str "Name: " player))
      (dom/h1 (str "Character: " character))))))

(defcomponent app-view
  [app owner]
  (render
   [_]
   (dom/div
    (dom/h1 (str "Hello " (-> app :name :character)
                 (when-let [name (-> app :name :player)]
                   (str " (" name ")"))
                 "!"))
    (om/build name-view (:name app)))))

(defn main []
  (om/root
    app-view
    app-state
    {:target (. js/document (getElementById "app"))}))
