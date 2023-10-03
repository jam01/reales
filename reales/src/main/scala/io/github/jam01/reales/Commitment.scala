//package io.github.jam01.reales
//
//import io.github.jam01.rea
//import io.github.jam01.rea.{Agent, CommitmentType, Event, EventType, Reservation}
//import org.jspecify.annotations.Nullable
//
//import java.util
//import java.util.List
//
//class Commitment(@Nullable commitmentType: CommitmentType,
//                 provider: Agent, receiver: Agent,
//                 reservations: util.List[_ <: Reservation],
//                 @Nullable eventType: EventType,
//                 executedBy: util.List[Event],
//                 isFulfilled: Boolean,
//                 definition: CommitmentDefinition,
//                 val attributes: Map[String, Attribute[_]]) extends rea.Commitment(commitmentType, provider, receiver, reservations, eventType, executedBy, isFulfilled) {
//
//  // verify invariatns
//  definition.invariants.apply(this)
//
//  override protected def fulfill(isFulfilled: Boolean): rea.Commitment =
//    definition.fulfill.apply(this, isFulfilled)
//
//  override def execute(events: util.List[Event]): rea.Commitment =
//    definition.executedBy.apply(this, events)
//}
//
//class CommitmentDefinition(val attributes: Map[String, AttributeDefinition],
//                           val invariants: Commitment => Unit,
//                           val fulfill: (Commitment, Boolean) => Commitment,
//                           val executedBy: (Commitment, util.List[Event]) => Commitment) extends ObjectDefinition(false, attributes)