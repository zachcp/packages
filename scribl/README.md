# cljsjs/scribl

[](dependency)
```clojure
[cljsjs/scribl "1.1.4-0"] ;; latest release
```
[](/dependency)

This JAR comes with `deps.cljs`, as used by the [Foreign Libs][flibs]
feature of the ClojureScript compiler. After adding the above dependency
to your project, you can require the packaged `lamejs` library as
follows:

```clojure
(ns application.core
  (:require [cljsjs.scribl]))

;; Somewhere in your code

(let [can (js/document.getElementById "scribl")
	  chart (js/Scribl can 400)
	  gene1  (.addGene chart 5 750 +)]

  (.draw chart))
```

[flibs]: https://github.com/clojure/clojurescript/wiki/Packaging-Foreign-Dependencies

