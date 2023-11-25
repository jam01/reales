package io.github.jam01.json_schema

import java.net.URLDecoder
import java.nio.charset.Charset

// TODO: consider making a record
final case class JsonPointer(refTokens: collection.immutable.Seq[String] = Seq("")) {
  if (refTokens.isEmpty) throw new IllegalArgumentException("invalid JSON Pointer")

  def appendRefToken(refTok: String): JsonPointer = JsonPointer(refTokens.appended(refTok))

  def appendRefTokens(refToks: String*): JsonPointer = JsonPointer(refTokens.appendedAll(refToks))

  override def toString: String = JsonPointer.toString(refTokens)
}

object JsonPointer {
  def apply(s: String): JsonPointer = {
    JsonPointer(URLDecoder.decode(s, Charset.defaultCharset()).split("/", -1)
      .map(_.replace("~0", "~")
        .replace("~1", "/") // TODO: replace w/regex?
      ))
  }

  def strValueOf(it: collection.IterableOnce[String]): String = {
    val itr = it.iterator
    if (!itr.hasNext) throw new IllegalArgumentException("invalid JSON Pointer")
    toString(itr)
  }

  private def toString(it: collection.IterableOnce[String]): String = {
    it.iterator
      .map(_.replace("~", "~0")
        .replace("/", "~1"))
      .mkString("/")
  }
}
