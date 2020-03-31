(require '[kitchen-async.promise :as p]
         '[playwright :refer [webkit devices]])

(comment "https://github.com/microsoft/playwright#mobile-and-geolocation")

(def iphone-11 (aget devices "iPhone 11 Pro"))

(p/let [browser (.. webkit launch)
        context (.. browser (newContext
                              (clj->js {:viewport    (.. iphone-11 -viewport)
                                        :userAgent   (.. iphone-11 -userAgent)
                                        :geolocation {:longitude 12.492507 :latitude 41.889938}
                                        :permissions ["geolocation"]})))
        page (.. context newPage)]
       (p/do
         (.. page (goto "https://www.where-am-i.net/"))
         (.. page (screenshot #js {:path "colosseum-iphone.png"}))
         (.. browser close)))