package array;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniel Liu
 * @date 2022-10-12
 */
public class HeapSort {
    public static void main(String[] args) {
        ReentrantLock rlock = new ReentrantLock();
        rlock.lock();
        rlock.unlock();
        System.out.println(Arrays.toString(new HeapSort().heapSort(new int[]{2, 5, 8, 4, 6})));
    }

    public int[] heapSort(int[] arr) {
        // 建立大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        // 和末尾交换
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr,0,size);
            swap(arr, 0, --size);
        }
        return arr;
    }

    private void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int lagest = arr[left]>arr[left+1]?arr[left]:arr[left+1];
//            arr[index] > lagest ? arr[index] : lagest;
        }
    }

    private void heapInsert(int[] arr, int i) {
        int parent = (i - 1) / 2;
        while (arr[i] > arr[parent]) {
            swap(arr, i, parent);
            i = parent;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
