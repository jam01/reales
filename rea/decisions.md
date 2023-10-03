# ResourceType independent from UnitOfMeasure 
To accommodate a situation in which an enterprise might exchange (for) a resource that can be measured in more than one unit. For example selling gas in gallons or in liters.

Solutions might include subclassing ResType to include one or many UnitOfMeasures supported, possibly assigning to each one their respective price somehow.

# Associate Stockflows with Resources only
To accommodate transferring identifiable and non-identifiable resources with the same structure, making representations simpler.

The main solution is to always include ResourceType and introduce a quantity attribute in Resource subclasses. In this model we consider Resources to represent only the attributes that we care about in the context economic events.

Seen another way, not all resources linked to economic events are continuants and the model ought to deal with that.

# Reified *EntityType instead of as subclass
Allows entities and their types to form hierarchies independently. Also, to allow assigning types at runtime.

# Types references to Types
Allows creating Types hierarchies at runtime.

# Not adding quantity Value to Resource
Allows to exchange uniquely identifiable goods without unnecessary data.

# Using Result<Entity>
Allows us to synthetically mix procedure call and event connector, which gives us the ability to treat entities entirely as immutable. Also see Vernon's IDDD on Event Sourcing in Functional Languages.

# Allow to modify stockflow in Events and commitments in Contract
Probably shouldn't for events...
