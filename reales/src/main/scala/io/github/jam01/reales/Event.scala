//package io.github.jam01.reales
//
//import io.github.jam01.rea
//import io.github.jam01.rea.{Agent, EventType, Stockflow}
//import org.jspecify.annotations.Nullable
//
//import java.util
//import java.util.List
//
//class Event(@Nullable eventType: EventType, provider: Agent, receiver: Agent, stockflow: util.List[_ <: Stockflow],
//            definition: EventDefinition,
//            val attributes: Map[String, Attribute[_]])
//  extends rea.Event(eventType, provider, receiver, stockflow) {
//
//  // verify invariants
//  definition.invariants.apply(this)
//
//  override def withStockflow(stockflow1: util.List[_ <: Stockflow]): rea.Event =
//    definition.withStockflow.apply(this, stockflow1)
//}
//
//class EventDefinition(val attributes: Map[String, AttributeDefinition],
//                      val invariants: Event => Unit,
//                      val withStockflow: (Event, util.List[_ <: Stockflow]) => Event) extends ObjectDefinition(false, attributes)
//
