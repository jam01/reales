using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Schema;

namespace REAJJ
{

    class Program
    {
        static void Main(string[] args)
        {
            Customer MyC = new Customer();
            MyC.IsInGroup(new CountryRegion());
        }
    }
}
