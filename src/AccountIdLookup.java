import java.util.Arrays;

public class AccountIdLookup {

    // ================= LINEAR SEARCH =================
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First Occurrence: " + first);
        System.out.println("Last Occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
    }

    // ================= BINARY SEARCH =================
    public static void binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;
        int index = -1;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                index = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Count duplicates
        int count = 0;
        if (index != -1) {
            int i = index;
            while (i >= 0 && arr[i].equals(target)) {
                count++;
                i--;
            }
            i = index + 1;
            while (i < arr.length && arr[i].equals(target)) {
                count++;
                i++;
            }
        }

        System.out.println("\nBinary Search:");
        System.out.println("Found at index: " + index);
        System.out.println("Count: " + count);
        System.out.println("Comparisons: " + comparisons);
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Sort for binary search
        Arrays.sort(logs);

        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        linearSearch(logs, "accB");
        binarySearch(logs, "accB");
    }
}