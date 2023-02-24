
using System;

namespace REAJJ
{
    public class PurchaseOrder : IInCommiment
    {
        DateTime happend;
        Decimal amount;

        Agreement agreement;
        public void Calculate()
        {
            agreement.Calculate();
        }

        public Decimal Value
        {
            get { return amount; }
            set { amount = value; }
        }
        public DateTime time { get { return happend; } set { happend = value; } }

        DateTime ICommiment.PromisedWhen { get => happend; set { happend = value; } }

        public PurchaseOrder()
        {
            agreement = new Agreement(PurchaseType.Order); // Hardcode for now

        }

        public void addItem(SalesItem item) 
        {
            //TODO: if no such PurchaseorderLine with this item then add a salesline otherwise increase quantity
            throw new NotImplementedException();
        }

        void IOccurent.AddItem(IResource resource)
        {
            addItem(resource as SalesItem);
        }
    }

}
