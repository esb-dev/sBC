/* Search eBooks Collection sBC
   Get basedirs of collections from config file

   Copyright (c) 2019 Burkhardt Renz, THM. All rights reserved.
   The use and distribution terms for this software are covered by the
   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php).
   By using this software in any fashion, you are agreeing to be bound by
   the terms of this license.
 */

import us.bpsm.edn.Keyword.newKeyword
import us.bpsm.edn.parser.Parser
import us.bpsm.edn.parser.Parsers
import us.bpsm.edn.parser.Parsers.defaultConfiguration
import java.io.File

object Collections {

    /**
     * returns basedirs of collections of ebooks as a list of strings
     */
    fun getCollections(): List<String> {
        return parseEdn(readFile(configFileName))
    }

    private val configFileName = System.getProperty("user.home") + "/.ebcconfig"

    private fun readFile(fileName: String): String = File(fileName).readText(Charsets.UTF_8)

    @Suppress("UNCHECKED_CAST")
    private fun parseEdn(ednString: String): List<String> {
        try {
            val parseable = Parsers.newParseable(ednString)
            val parser = Parsers.newParser(defaultConfiguration())
            var item: Any
            var map: Map<Any, Any>
            val key = newKeyword("basedirs")
            do {
                item = parser.nextValue(parseable)
                //println(item)
                map = item as? Map<Any, Any> ?: HashMap<Any, Any>()
            } while (item != Parser.END_OF_INPUT && map[key] == null)
            return map.get(key) as? List<String> ?: emptyList()
        } catch (e: Exception) {
            return emptyList()
        }
    }
} 