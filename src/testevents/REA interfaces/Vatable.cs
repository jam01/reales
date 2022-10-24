using System;

namespace REAJJ
{
    public class Vatable : IVatable
    {
        private Boolean _vatdue;
        bool IVatable.Vatdue { get => _vatdue; set { _vatdue = value; } }


        public Vatable(Boolean vatdue)
        {
            _vatdue = vatdue;
        }
    }
}
