import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap
    // and false otherwise.
    private static boolean maxOrderedHeap(Comparable[] a) {
        int N = a.length;

        // For each node 1 <= i <= N / 2, if i is less than
        // either of its children, return false, meaning a[]
        // does not represent a maximum-ordered heap.
        // Otherwise, return true.
        if (N == 0)
        {
            return false;
        }
        
        for (int i = 1; i <= N/2; i++)
        {
            if (2*i < N && less(a[i], a[2*i]))
            {
                return false;
            }
            if (2*i+1 < N && less(a[i], a[2*i+1]))
            {
                return false;
            }
        }
        
        return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        String[] a = scanner.nextLine().split(" ");
        System.out.println(maxOrderedHeap(a));
        scanner.close();
    }
}
