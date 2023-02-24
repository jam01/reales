namespace REAJJ
{
    public interface IVatGroup:IGroup, IContinuant
    {
        decimal Vatpercentage { get; set; }

    }
}
