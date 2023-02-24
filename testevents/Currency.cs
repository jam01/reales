namespace REAJJ
{
    public class Currency : IGroup
    {
        string name;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        string shortname;

        public string Shortname
        {
            get { return shortname; }
            set { shortname = value; }
        }
    }
}
