# sBC Searching in EBooks Collections

This project is a companion project to [eBC](https://github.com/esb-dev/eBC). It allows to search EBook collections in a webbased approach.

The project has two components:

- The backend is a RESTful web service developed in Kotlin with the [Javalin](https://javalin.io) framework. It has the following features:
		
	- ```http://localhost:7001/collections``` shows which collections of ebooks are available 
	- ```http://localhost:7001/ebooks?coll=<collection>&query=<lucene-query>&num=<numResults>``` retrieves the requested book entries in the collection
	- ```http://localhost:7001``` is home of a single page application that uses the two web services above
	
- The frontend is a single page application developed with vue.js

sBC is just for searching EBook collections. To create and update such
collection see [eBC](https://github.com/esb-dev/eBC).

## License

sBC is licensed under the Eclipse Public License (EPL). It uses libraries 
that are licensed under the Apache License 2.0.
