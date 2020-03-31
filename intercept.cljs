(require '[kitchen-async.promise :as p]
         '[playwright :refer [webkit]])

(comment "https://github.com/microsoft/playwright#intercept-network-requests")

(p/let [browser (.. webkit launch)
        context (.. browser newContext)
        page (.. context newPage)]
       (.route page "**" (fn [route]
                             (prn (.. route request url))
                             (.. route continue)))

       (p/do
         (.. page (goto "http://todomvc.com"))
         (.. browser close)))