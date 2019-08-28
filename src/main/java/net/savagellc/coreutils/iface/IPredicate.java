package net.savagellc.coreutils.iface;

public interface IPredicate<T> {
    boolean apply(T value);
}
