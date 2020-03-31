(require '[kitchen-async.promise :as p]
         '[playwright :refer [chromium firefox webkit]])

(comment "https://github.com/microsoft/playwright#page-screenshot")

(doseq [playwright-browser [chromium firefox webkit]]
       (p/let [browser (.. playwright-browser launch)
               context (.. browser newContext)
               page (.. context newPage)]
              (p/do
                (.. page (goto "http://whatsmyuseragent.org/"))
                (.. page (screenshot #js {:path (str "example-" (.. playwright-browser name) ".png")}))
                (.. browser close))))