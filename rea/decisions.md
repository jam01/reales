# Removed UnitOfMeasure from ResourceType
To accommodate a situation in which an enterprise might exchange (for) a resource that can be measured in more than one unit. For example selling gas in gallons or in liters.

Solutions might include subclassing ResType to include one or many UnitOfMeasures supported, possibly assigning to each one their respective price somehow.

# Associate Stockflows with Resources only
To accommodate transferring identifiable and non-identifiable resources with the same structure, making representations simpler.

The main solution is to always include ResourceType and introduce a quantity attribute in Resource subclasses. In this model we consider Resources to represent only the attributes that we care about in the context EconomicEvents.

# Reified ResourceType instead of Resources as subclass
Allows ResourceType and Resource hierarchies independently. Also, to allow assigning ResourceTypes to Resources at runtime.

# Considering: ResourceType reference to itself
Allows creating ResourceType hierarchies at runtime.

# Not adding quantity Value to Resource
Allows to exchange uniquely identifiable goods without unnecessary data.