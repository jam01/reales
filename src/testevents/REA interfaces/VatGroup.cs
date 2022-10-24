using System;

namespace REAJJ
{
    public class VatGroup:IVatGroup
    {
        private Decimal _vatpercentage;
        private string _name;
        public VatGroup(string Name)
        {
            _vatpercentage = 0;
        }
        public VatGroup(string Name,decimal vatpercentage)
        {
            _vatpercentage = vatpercentage;
        }

        public decimal Vatpercentage { get => _vatpercentage; set => _vatpercentage = value; }
        string IContinuant.Id { get => _name; set => _name=value; }
    }
}
