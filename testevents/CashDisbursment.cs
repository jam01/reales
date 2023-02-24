
using System;

namespace REAJJ
{
    public class CashDisbursment : IEvent
    {
        DateTime happend;
        Decimal amount;

        public Decimal Value
        {
            get { return amount; }
            set { amount = value; }
        }
        public DateTime time { get { return happend; } set { happend = value; } }
    }
}
