//package io.github.jam01.reales
//
//import scala.collection.immutable.Map
//
//class AttributeDefinition(`type`: Type.Value = Type.String,
//                          isRequired: Boolean = false,
//                          isDerived: Boolean = false,
//                          derivation: Option[AttributeDerivation],
//                          isPk: Boolean) {
//}
//
//case class AttributeDerivation(cacheable: Boolean,
//                               cacheTTL: String,
//                               editable: Boolean,
//                               function1: Any => Attribute[_])
//
//object Type extends Enumeration {
//  val Number: Type.Value = Value("number")
//  val String: Type.Value = Value("string")
//  val Object: Type.Value = Value("object")
//}
//
//class ObjectDefinition(isRequired: Boolean,
//                       attributes: Map[String, AttributeDefinition]) extends AttributeDefinition(Type.Object,
//  isRequired,
//  false,
//  None,
//  false)
