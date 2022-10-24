namespace REAJJ
{
    public interface IResource : IContinuant
    {
        decimal Value { get; set; }
        decimal Price { get; set; }
        decimal Onstock { get; set; }

        void CalculateCost();

    }
}
