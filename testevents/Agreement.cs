
using System;

namespace REAJJ
{
    public class Agreement:IAgreement
    {
        private SalesType _salesType;
        private PurchaseType _purchaseType;

        public Agreement(SalesType st)
        {
            _salesType = st;
        }
        public Agreement(PurchaseType pt)
        {
            _purchaseType = pt;
        }

        void IAgreement.Calculate()
        {
            switch (_salesType)
            {
                case SalesType.Cash:
                    //TODO  make sure we get a vatcommitment;
                    // and a cashpayment for the full amount
                    throw new NotImplementedException();
                    break;
                default:
                    break;
            }

            switch (_purchaseType)
            {
                case PurchaseType.Cash:
                    // make sure we get a cashdisbursement for the full amount
                    throw new NotImplementedException();
                    break;
                default:
                    break;
            }
        }
        public Agreement()
        {

        }

        internal void Calculate()
        {
            Calculate();
        }
    }
}
