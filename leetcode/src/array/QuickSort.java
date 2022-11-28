package array;

import java.util.Arrays;

/**
 * @author Daniel Liu
 * @date 2022-10-12
 */
public class QuickSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BubbleSort().bubbleSort(new int[]{3, 7, 5, 4})));
    }

    public int[] quickSort(int[] nums) {

        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
