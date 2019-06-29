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
import io.javalin.core.util.Header
import io.javalin.staticfiles.Location
import java.awt.EventQueue

private lateinit var app: Javalin

fun main(args: Array<String>) {
    EventQueue.invokeLater(::createAndShowGUI)
}

private fun createAndShowGUI() {
    Gui()
}

fun startServer() {
    
    app = Javalin.create().apply {
        enableStaticFiles("/public")
        enableStaticFiles(Collections.getCollBase(), Location.EXTERNAL)
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start()
    
    app.before { ctx ->
        ctx.header(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
    }

    app.routes {
        get("/collections") { ctx ->
            ctx.json(Collections.getCollections())
        }
        
        get("/ebooks?") { ctx ->
            ctx.json(QueryProc.search(ctx.queryParam("coll"),
                    ctx.queryParam("query"),
                    ctx.queryParam("num")))
        }
    }
}

fun stopServer() {
    app.stop()
}