
using System;

namespace REAJJ
{
    public class VAT : IResource
    {
        string name;
        public string Id { get { return name; } set { name = value; } }
        Currency currency = new Currency();
        private Decimal value;

        public Decimal Value
        {
            get { return this.value; }
            set { this.value = value; }
        }

        decimal IResource.Price { get => throw new NotImplementedException(); set => throw new NotImplementedException(); }
        decimal IResource.Onstock { get => throw new NotImplementedException(); set => throw new NotImplementedException(); } // This is VATdue

        void IResource.CalculateCost()
        {
            // TODO: I am not sure that there is a cost for cash?
            throw new NotImplementedException();
        }
    }
}
