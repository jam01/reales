namespace REAJJ
{
    public class CashCashDisbursment : inflow<CashDisbursment, Cash>
    {
        public CashCashDisbursment(CashDisbursment ie, Cash ir) : base(ie, ir) { }
    }
}
