(require '[kitchen-async.promise :as p]
         '[playwright :refer [firefox]])

(comment "https://github.com/microsoft/playwright#evaluate-in-browser-context")

(p/let [browser (.launch firefox)
        context (.newContext browser)
        page (.newPage context)
        _ (.goto page "https://www.example.com/")
        dimensions (js->clj (.evaluate page (fn []
                                                #js {:width             (.. js/document -documentElement -clientWidth)
                                                     :height            (.. js/document -documentElement -clientHeight)
                                                     :deviceScaleFactor (.. js/window -devicePixelRatio)})))]
       (prn dimensions)
       (p/do
         (.close browser)))