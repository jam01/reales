
using System;

namespace REAJJ
{
    public class CountryRegion : IGroup
    {
        String Name;
        public CountryRegion(string name)
        {
            Name = name;    
        }
        public CountryRegion()
        {
            Name = "Denmark";
        }
    }
}
