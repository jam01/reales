
namespace REAJJ
{
    public class Seller : IAgent
    {
        private string Name;

        public string Id { get { return Name; } set { Name = value; } }

        private Adress address;

        public Adress adress
        {
            get { return address; }
            set { address = value; }
        }
    }
}
