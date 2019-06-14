/* Search eBooks Collection sBC
   Main for loading frontend and api for webservice

   Copyright (c) 2019 Burkhardt Renz, THM. All rights reserved.
   The use and distribution terms for this software are covered by the
   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php).
   By using this software in any fashion, you are agreeing to be bound by
   the terms of this license.
 */

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get

fun main(args: Array<String>) {

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(7000)

    app.routes {
        get("/collections") { ctx ->
            ctx.json(Collections.getCollections())
        }

        get("/ebooks?") { ctx ->
            ctx.json(QueryProc.search(ctx.queryParam("coll"),
                    ctx.queryParam("query")))
        }
    }
}