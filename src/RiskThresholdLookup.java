import java.util.Arrays;

public class RiskThresholdLookup {

    // ================= LINEAR SEARCH =================
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comparisons);
    }

    // ================= BINARY FLOOR & CEILING =================
    public static void binarySearchFloorCeil(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1, ceil = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                floor = ceil = arr[mid];
                break;
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("\nBinary Search:");
        System.out.println("Floor: " + floor);
        System.out.println("Ceiling: " + ceil);
        System.out.println("Comparisons: " + comparisons);
    }

    // ================= INSERTION POINT =================
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        // Linear search (unsorted scenario)
        linearSearch(risks, 30);

        // Binary search (sorted)
        binarySearchFloorCeil(risks, 30);

        // Insertion point
        int index = insertionPoint(risks, 30);
        System.out.println("Insertion Index for 30: " + index);
    }
}