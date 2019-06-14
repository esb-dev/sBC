/* Search eBooks Collection sBC
   Searching in Lucene index

   Copyright (c) 2019 Burkhardt Renz, THM. All rights reserved.
   The use and distribution terms for this software are covered by the
   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php).
   By using this software in any fashion, you are agreeing to be bound by
   the terms of this license.
 */

import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.analysis.util.CharArraySet
import org.apache.lucene.document.Document
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.util.Version.LUCENE_44
import java.nio.file.Paths


object QueryProc {

    /**
     *  returns list of BookEntry for the ebooks fulfilling query found in coll 
     */
    fun search(coll: String?, query: String?): List<BookEntry> {
        //println("coll: $coll, query: $query")
        val indexDir = coll + "/LuceneIdx"
        val entries = mutableListOf<BookEntry>()
        DirectoryReader.open(FSDirectory.open(Paths.get(indexDir).toFile())).use { reader ->
            val searcher = IndexSearcher(reader)
            val analyzer = StandardAnalyzer(LUCENE_44, CharArraySet(LUCENE_44, stopwords, true))
            val qryParser = QueryParser(LUCENE_44, "content", analyzer)
            val qry = qryParser.parse(query)
            val results = searcher.search(qry, noResults)
            results.scoreDocs.forEach {
                //val doc = searcher.doc(it.doc)
                //val score = it.score
                //println(doc2book(doc, score))
                entries.add(doc2book(searcher.doc(it.doc), it.score))
            }
        }
        return entries
    }
}

private val noResults = 50

private val stopwords = listOf(
        // english words
        "a", "an", "and", "are", "as", "at", "be", "but", "by",
        "for", "if", "in", "into", "is", "it",
        "no", "not", "of", "on", "or", "such",
        "that", "the", "their", "then", "there", "these",
        "they", "this", "to", "was", "will", "with",
        // german words
        "einer", "eine", "eines", "einem", "einen",
        "der", "die", "das", "dass", "daß",
        "du", "er", "sie", "es",
        "was", "wer", "wie", "wir",
        "und", "oder", "ohne", "mit",
        "am", "im", "in", "aus", "auf",
        "ist", "sein", "war", "wird",
        "ihr", "ihre", "ihres",
        "als", "für", "von", "mit",
        "dich", "dir", "mich", "mir",
        "mein", "sein", "kein",
        "durch", "wegen", "wird")

data class BookEntry(
        val path: String,
        val date: String,
        val author: String,
        val title: String,
        val size: String,
        val ext: String,
        val score: Float,
        val type: String)

fun doc2book(doc: Document, score: Float) = BookEntry(
        doc.get("path"),
        doc.get("date"),
        doc.get("author"),
        doc.get("title"),
        doc.get("size"),
        doc.get("ext"),
        score,
        doc.get("type"))
