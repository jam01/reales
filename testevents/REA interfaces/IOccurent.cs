
using System;

namespace REAJJ
{
    public interface IOccurent
    {
        DateTime time { get; set; }

        void AddItem(IResource resource);
    }
}
