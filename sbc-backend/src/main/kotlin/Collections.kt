/* Search eBooks Collection sBC
   Get collections from config file

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
     * CollEntry has text (= name) and value (= index) of an ebook collection
     */
    data class CollEntry(val text: String, val value: Int)

    /**
     * returns names of collections of ebooks as a list of CollEntries
     */
    fun getCollections(): List<CollEntry> {
        return pathList.mapIndexed { index, path -> CollEntry(path2name(path), index) }
    }

    /**
     * returns base path for all of our collections
     */
    fun getCollBase() = pathList.map{ path -> path2base(path) }.first()

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
            return map[key] as? List<String> ?: emptyList()
        } catch (e: Exception) {
            return emptyList()
        }
    }
    
    private val pathList = parseEdn(readFile(configFileName))

    private fun path2name(path: String) = path.split("/").last()
    
    private fun path2base(path: String): String {
        val len = path.length - path2name(path).length - 1
        return path.take(len)
    } 

} 