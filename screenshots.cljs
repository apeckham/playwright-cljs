(require '[kitchen-async.promise :as p]
         'playwright)

(comment "https://github.com/microsoft/playwright#page-screenshot")

(doseq [browser-type ["chromium" "firefox" "webkit"]]
       (p/let [browser (.launch (aget playwright browser-type))
               context (.newContext browser)
               page (.newPage context)]
              (p/do
                (.goto page "http://whatsmyuseragent.org/")
                (.screenshot page #js {:path (str "example-" browser-type ".png")})
                (.close browser))))