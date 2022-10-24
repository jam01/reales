
using System;
using System.Collections.ObjectModel;

namespace REAJJ
{
    public class Sale : IOutEvent
    {
        DateTime happend;
        Decimal amount;

        Agreement agreement;

        Collection<salesline<Sale, SalesItem>> _outflows;
        public void Calculate()
        {
           agreement.Calculate();
        }

        void IOccurent.AddItem(IResource resource)
        {
            var sl = new salesline<Sale, SalesItem>(this, resource);
            _outflows.Add(sl);
        }

        public Collection<salesline<Sale, SalesItem>> OutFlows { get => _outflows; set => _outflows = value; }
        public Decimal Value
        {
            get { return amount; }
            set { amount = value; }
        }
        public DateTime time { get { return happend; } set { happend = value; } }
        public Sale()
        {
            happend = DateTime.Now;
            agreement = new Agreement(SalesType.Cash); // Hardcode for now

        }

    }

}
