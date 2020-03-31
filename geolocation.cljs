(require '[kitchen-async.promise :as p]
         '[playwright :refer [webkit devices]])

(def iphone-11 (aget devices "iPhone 11 Pro"))

(p/let [browser (.launch webkit)
        context (.newContext browser
                             (clj->js {:viewport    (.-viewport iphone-11)
                                       :userAgent   (.-userAgent iphone-11)
                                       :geolocation {:longitude 12.492507 :latitude 41.889938}
                                       :permissions ["geolocation"]}))
        page (.newPage context)]
       (p/do
         (.goto page "https://www.where-am-i.net/")
         (.screenshot page #js {:path "colosseum-iphone.png"})
         (.close browser)))