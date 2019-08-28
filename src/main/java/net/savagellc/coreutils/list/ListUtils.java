package net.savagellc.coreutils.list;

import net.savagellc.coreutils.iface.IComparePredicate;
import net.savagellc.coreutils.iface.IPredicate;
import net.savagellc.coreutils.iface.ITypeCall;
import net.savagellc.coreutils.iface.IVoidCall;

import java.util.*;

public class ListUtils {


    /**
     * Creates a new collection based on a execute on each element of the entry collection
     * @param target the target collection to perform the map on
     * @param predicate the callback function executing the compare
     * @param <T> the type of the targeted collection
     * @param <T2> the type of the resulting collection
     * @return the resulting collection
     */
    public static <T, T2> Collection<T2> map(Collection<T> target, ITypeCall<T, T2> predicate) {
        Collection<T2> result = new ArrayList<>();
        for (T element : target) {
            result.add(predicate.apply(element));
        }
        return result;
    }

    /**
     * Performs a selection sort on the giving collection with the callback
     * @param target the target collection to perform the sorting on
     * @param predicate the callback function to perform th sort, should return an integer to perform the sort
     * @param <T> the collection type
     * @return the resulting collection
     */
    public static <T> Collection<T> sort(Collection<T> target, IComparePredicate<T> predicate) {
        T[] arr = (T[]) target.toArray();
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (predicate.apply(arr[j], arr[index]) < 0)
                    index = j;

            T smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        Collection<T> result = new ArrayList<>();
        for(T e : arr) result.add(e);
        return result;
    }

    /**
     * Concatenates two collections together
     * @param first the first collection to perform the concat
     * @param second the second
     * @param <T> the type of the collections
     * @return the resulting collection
     */
    public static <T> Collection<T> concat(Collection<T> first, Collection<T> second) {
        Collection<T> out = new ArrayList<>();
        for (T entry : first) out.add(entry);
        for (T entry : second) out.add(entry);
        return out;
    }

    /**
     * Executes a filter on a collection
     * @param target the target collection to perform the filtering on
     * @param predicate the callback function to perform the filtering on, should returns @{@link Boolean}
     * @param <T> The Type of the collection the filtering is executed on
     * @return a new collection based on the results of the filtering
     */
    public static <T> Collection<T> filter(Collection<T> target, IPredicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        for (T element : target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Returns a @{@link Map} from a List with an index as key
     * @param target the target list to apply on
     * @param <T> the type of the list
     * @return @{@link Map} created from the indexing.
     */
    public static <T> Map<Integer, T> withIndex(List<T> target) {
        Map<Integer, T> out = new HashMap<>();
        for (int i = 0; i < target.size(); i++) {
            out.put(i, target.get(i));
        }
        return out;
    }

    /**
     * Performs a simple action on all elements of the collection
     * @param target the target collection for the iteration
     * @param predicate the callback function
     * @param <T> the type of the collection
     */
    public static <T> void forEach(Collection<T> target, IVoidCall<T> predicate) {
        for (T entry : target) {
            predicate.apply(entry);
        }
    }
}
