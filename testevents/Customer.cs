namespace REAJJ
{
    public class Customer : IAgent
    {
        private string Name;
        Vatable _vatable = new Vatable(true);
        public string Id { get { return Name; } set { Name = value; } }

        private Adress address;

        public Adress adress
        {
            get { return address; }
            set { address = value; }
        }

        public Vatable Vatable { get => _vatable; set => _vatable = value; }
    }
}
