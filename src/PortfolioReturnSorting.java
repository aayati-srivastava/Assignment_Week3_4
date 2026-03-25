import java.util.Arrays;
import java.util.Random;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorting {

    // ================= MERGE SORT (ASC, STABLE) =================
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) { // stable
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // ================= QUICK SORT (DESC + volatility ASC) =================
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // ================= MEDIAN OF THREE =================
    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    // ================= SWAP =================
    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // Copy arrays
        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        Asset[] quickArr = Arrays.copyOf(assets, assets.length);

        // Merge Sort (ASC)
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Ascending): " + Arrays.toString(mergeArr));

        // Quick Sort (DESC)
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (Descending): " + Arrays.toString(quickArr));
    }
}