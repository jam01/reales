using System.Collections.ObjectModel;

namespace REAJJ
{
    public interface IOutEvent : IEvent
    {
        Collection<salesline<Sale, SalesItem>> OutFlows { get; set; }
    }
}
