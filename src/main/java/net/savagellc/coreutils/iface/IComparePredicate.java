package net.savagellc.coreutils.iface;

public interface IComparePredicate<T> {
    int apply(T a, T b);
}
