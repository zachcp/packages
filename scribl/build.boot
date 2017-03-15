(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.6.0" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.1.4")
(def +version+     (str +lib-version+ "-0"))


(task-options!
  push {:ensure-clean false}
  pom  {:project      'cljsjs/scribl
        :version      +version+
        :description  "A canvas-based graphics library for drawing genes and genomes"
        :url          "http://chmille4.github.io/Scribl/"
        :license      {"MIT" "https://opensource.org/licenses/MIT"}
        :scm          {:url "http://github.com/chmille4/Scribl"}})

(deftask package
  []
  (comp
    (download
      :url (format "https://github.com/chmille4/Scribl/archive/v%s.zip"
                   +lib-version+)
      :checksum "d5ed0ae6c6bfe4673c2f8c1a0cc3607e"
      :unzip true)
    (sift :move {#"^Scribl-[^\/]*/src/*.js"
                 "cljsjs/development/"
				 
				 #"^Scribl-[^\/]*/src/Scribl.js"
				 "cljsjs/development/Scribl.inc.js"
								  
				 #"^Scribl-[^\/]*/examples/demos.css"
                 "cljsjs/scribl/common/scribl.css"
				 
                 #"^Scribl-[^\/]*/Scribl.1.1.4.min.js"
                 "cljsjs/production/Scribl.1.1.4.min.inc.js"})
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.scribl")
    (pom)
    (jar)))
