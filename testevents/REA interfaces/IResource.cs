namespace REAJJ
{
    public interface IResource : IContinuant
    {
        decimal Value { get; set; } // onstock * price (sometimes) established by resource
        decimal Price { get; set; } // sale price
        decimal Onstock { get; set; } // how many in stock

        void CalculateCost();


        // non-identifiable resources are group or res type
    }
}
