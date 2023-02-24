namespace REAJJ
{
    public class inflow<IEvent , IResource> : assocication<IEvent, IResource>
    {
        public inflow(IEvent ie,IResource ir) : base(ie, ir) { }

    }
}
