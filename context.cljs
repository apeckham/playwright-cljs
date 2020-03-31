(require '[kitchen-async.promise :as p]
         '[playwright :refer [firefox]])

(comment "https://github.com/microsoft/playwright#evaluate-in-browser-context")

(p/let [browser (.. firefox launch)
        context (.. browser newContext)
        page (.. context newPage)
        _ (.. page (goto "https://www.example.com/"))
        dimensions (js->clj (.. page (evaluate (fn []
                                                   #js {:width             (.. js/document -documentElement -clientWidth)
                                                        :height            (.. js/document -documentElement -clientHeight)
                                                        :deviceScaleFactor (.. js/window -devicePixelRatio)}))))]
       (prn dimensions)
       (p/do
         (.. browser close)))