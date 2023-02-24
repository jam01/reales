using System.Collections.ObjectModel;

namespace REAJJ
{
    public interface IInEvent : IEvent
    {
        Collection<Purchaseline<Purchase, SalesItem>> Inflows { get; set; }
    }
}
