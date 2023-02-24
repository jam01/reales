
using System;
using System.Collections.ObjectModel;

namespace REAJJ
{
    public class Purchase : IInEvent
    {
        DateTime happend;
        Decimal amount;

        Collection<Purchaseline<Purchase, SalesItem>> _inflows;

        Agreement agreement;
        public void Calculate()
        {
            agreement.Calculate();
        }
        void AddÍtem(SalesItem item)
        {
            var pl = new Purchaseline<Purchase, SalesItem>(this, item);
            Inflows.Add(pl);
        }

        void IOccurent.AddItem(IResource resource)
        {
            AddÍtem(resource as SalesItem);
        }

        public Decimal Value
        {
            get { return amount; }
            set { amount = value; }
        }
        public DateTime time { get { return happend; } set { happend = value; } }

        public Collection<Purchaseline<Purchase, SalesItem>> Inflows { get => _inflows; set => _inflows = value; }

        public Purchase()
        {
            happend = DateTime.Now;
            agreement = new Agreement(PurchaseType.Cash); // Hardcode for now
            Inflows=new Collection<Purchaseline<Purchase, SalesItem>>();
        }

    }

}
