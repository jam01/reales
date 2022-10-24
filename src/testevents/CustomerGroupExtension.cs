
using System;

namespace REAJJ
{
    static public class CustomerGroupExtension
    {
        static public Boolean IsInGroup(this Customer c, IGroup group)
        {
            return c.adress.countryregion.Equals(group);
        }
    }
}
