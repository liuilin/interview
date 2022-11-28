package array;

import java.util.Arrays;

/**
 * @author Daniel Liu
 * @date 2022-10-12
 */
public class BubbleSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BubbleSort().bubbleSort(new int[]{3, 7, 5, 4})));
    }

    public int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
