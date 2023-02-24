using System;

namespace REAJJ
{
    public interface ICommiment : IOccurent
    {
        DateTime PromisedWhen { get; set; }
        decimal Value { get; set; }
    }
}


