namespace REAJJ
{
    public class salesline<IE, IR> : outflow<IE, IR> where IR : IResource, new() where IE : IEvent, new()
    {
        private decimal quantity;
        decimal Quantity
        {
            get { return quantity; }
            set { Total = Quantity * Price; }
        }
        private decimal price;
        decimal Price
        {
            get { return price; }
            set { Total = Quantity * Price; }
        }

        private decimal total;
        private decimal cost;
        decimal Total
        {
            get { return total; }
            set
            {
                if (quantity != 0)
                {
                    price = total / quantity;
                }
                else
                {
                    total = 1;
                    price = total;
                }
            }
        }

        public decimal Cost { get => cost; set => cost = value; }

        public salesline(IEvent ie, IResource ir) : base((IE)ie, (IR)ir) {
            Quantity = 1;
            Price = ir.Price;
            
        }

    }

    public class Purchaseline<IE, IR> : outflow<IE, IR> where IR : IResource, new() where IE : IEvent, new()
    {
        private decimal quantity;
        decimal Quantity
        {
            get { return quantity; }
            set { Total = Quantity * Price; }
        }
        private decimal price;
        decimal Price
        {
            get { return price; }
            set { Total = Quantity * Price; }
        }

        private decimal total;
        decimal Total
        {
            get { return total; }
            set
            {
                if (quantity != 0)
                {
                    price = total / quantity;
                }
                else
                {
                    total = 1;
                    price = total;
                }
            }
        }
        public Purchaseline(IEvent ie, IResource ir) : base((IE)ie, (IR)ir)
        {
            Quantity = 1;
            Price = ir.Price;

        }

    }
    public class PurchaseOrderline<IC, IR> : outflow<IC, IR> where IR : SalesItem, new() where IC : IInCommiment, new()
    {
        private SalesItem _item;
        private decimal _quantity;
        decimal Quantity
        {
            get { return _quantity; }
            set { Total = Quantity * Price; }
        }
        private decimal price;
        decimal Price
        {
            get { return price; }
            set { Total = Quantity * Price; }
        }

        private decimal total;
        decimal Total
        {
            get { return total; }
            set
            {
                if (_quantity != 0)
                {
                    price = total / _quantity;
                }
                else
                {
                    total = 1;
                    price = total;
                }
            }
        }
        public PurchaseOrderline(ICommiment ic, SalesItem ir) : base((IC)ic, (Sa)ir)
        {
            Quantity = 1;
            Price = ir.Price;
            _item = ir;

        }

    }
}
