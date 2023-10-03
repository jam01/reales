package upickle.jsoniter

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}
import io.github.jam01.rea.attributes.Value
import io.github.jam01.reales.domain.CollectionResource
import upickle.core.Visitor
import upickle.jsoniter.JsoniterScalaCodec.NIDX

import java.nio.charset.StandardCharsets

// see: https://github.com/rallyhealth/weePickle/pull/105
// https://github.com/plokhotnyuk/jsoniter-scala/blob/v2.23.5/jsoniter-scala-circe/shared/src/main/scala/io/circe/JsoniterScalaCodec.scala
// https://github.com/evolution-gaming/play-json-tools/blob/v1.0.0/play-json-jsoniter/shared/src/main/scala/play/api/libs/json/JsonValueCodecJsValue.scala
// https://github.com/com-lihaoyi/upickle/pull/467#issuecomment-1473358589
object JsoniterScalaCodec {
  private val NIDX = -1

  def defaultNumberParser[J]: (JsonReader, Visitor[_, J]) => J = (in, v) => {
    in.setMark()
    var digits, index = 0
    var decIndex, expIndex = -1
    var b = in.nextByte()
    if (b == '-') {
      b = in.nextByte()
      index += 1
    }
    while ((b >= '0' && b <= '9') && in.hasRemaining()) {
      b = in.nextByte()
      digits += 1
    }
    in.rollbackToMark()
    if ((b | 0x20) != 'e' && b != '.') {
      if (digits < 19) {
        if (digits < 10) v.visitInt32(in.readInt(), NIDX)
        else v.visitInt64(in.readLong(), NIDX)
      } else {
        val x = in.readBigInt(null)
        if (x.isValidLong) v.visitInt64(x.longValue, NIDX)
        else v.visitString(x.toString(), NIDX) // see: https://github.com/com-lihaoyi/upickle/blob/3.1.3/ujson/src/ujson/JsVisitor.scala#L33
        // alternative: v.visitFloat64StringParts(x.toString(), -1, -1, NIDX)
      }
    } else {
      val x = in.readBigDecimal(null)
      if (x.isValidD)
    }
//    } else {
//      index += digits
//      if (b == '.') {
//        decIndex = index
//        while ((b >= '0' && b <= '9') && in.hasRemaining()) {
//          b = in.nextByte()
//          index += 1
//        }
//      }
//
//      if ((b | 0x20) == 'e') {
//        expIndex = index
//        b = in.nextByte()
//        index += 1
//        if (b == '-' || b == '+') {
//          b = in.nextByte()
//          index += 1
//        }
//        while ((b >= '0' && b <= '9') && in.hasRemaining()) {
//          b = in.nextByte()
//          index += 1
//        }
//      }
//
//      val cs = new String(in.readRawValAsBytes(), StandardCharsets.US_ASCII)
//      if (cs.length -1 != index) in.decodeError("invalid number")
//      v.visitFloat64StringParts(cs, decIndex, expIndex, NIDX)
//    }
  }
}

final class JsoniterScalaCodec[J](
                                maxDepth: Int,
                                numberParser: JsonReader => J,
                                v: Visitor[_, J]) extends JsonValueCodec[J] {
  override def nullValue: J = null.asInstanceOf[J]

  override def decodeValue(in: JsonReader, default: J): J =
    decode(in, maxDepth, v)

  override def encodeValue(x: J, out: JsonWriter): Unit =
    throw new UnsupportedOperationException("only supports decoding")

  private[this] def decode(in: JsonReader, depth: Int, v: Visitor[_, J]): J = {
    val x = new CollectionResource(null, null, null, Value.of(123, null),
      null, null)

    val b = in.nextToken()
    if (b == '"') {
      in.rollbackToken()
      v.visitString(in.readString(null), NIDX)
    } else if (b == 'f' || b == 't') {
      in.rollbackToken()
      if (in.readBoolean()) v.visitTrue(NIDX)
      else v.visitFalse(NIDX)
    } else if (b >= '0' && b <= '9' || b == '-') {
      in.rollbackToken()
      numberParser(in)
    } else if (b == '[') {
      val depthM1 = depth - 1
      if (depthM1 < 0) in.decodeError("depth limit exceeded")
      val isEmpty = in.isNextToken(']')
      val arrV = v.visitArray(if (isEmpty) 0 else -1, NIDX).narrow
      if (!isEmpty) {
        in.rollbackToken()
        while ({
          arrV.visitValue(decode(in, depthM1, arrV.subVisitor.asInstanceOf), NIDX)
          in.isNextToken(',')
        }) ()
        if (!in.isCurrentToken(']')) in.arrayEndOrCommaError()
      }
      arrV.visitEnd(NIDX)
    } else if (b == '{') {
      val depthM1 = depth - 1
      if (depthM1 < 0) in.decodeError("depth limit exceeded")
      val isEmpty = in.isNextToken('}')
      val objV = v.visitObject(if (isEmpty) 0 else -1, true, NIDX).narrow
      if (!isEmpty) {
        in.rollbackToken()
        var key = "?"
        while ({
          key = in.readKeyAsString()
          objV.visitKeyValue(objV.visitKey(NIDX).visitString(key, NIDX))
          objV.visitValue(decode(in, depthM1, objV.subVisitor.asInstanceOf), NIDX)
          in.isNextToken(',')
        }) ()
        if (!in.isCurrentToken('}')) in.objectEndOrCommaError()
      }
      objV.visitEnd(NIDX)
    } else in.readNullOrError(v.visitNull(NIDX), "expected JSON value")
  }
}
