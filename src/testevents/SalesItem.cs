
using System;

namespace REAJJ
{
    public class SalesItem : IResource
    {
        string name;
        public string Id { get { return name; } set { name = value; } }
        Currency currency;
        private Decimal value;


        public Decimal Value
        {
            get { return this.value; }
            set { this.value = value; }
        }

        decimal Price { get;set; }
        decimal IResource.Onstock { get; set; }
        decimal IResource.Price { get => Price; set => Price=value; }

        void IResource.CalculateCost()
        {
            // TODO: go throug all outstockflows and calculate the cost of those
            throw new NotImplementedException();
        }
    }
}
