package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author Daniel Liu
 * @date 2022-10-12
 */
public class TowSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TowSum().twoSum(new int[]{2, 3, 6, 8}, 9)));
    }

    /**
     * 以空间换时间的方式达到只用遍历一次数组，效率为 O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> hashMap = new HashMap<>(len - 1);
        // 初始化并放置第一个元素，用于后面的比较
        hashMap.put(nums[0], 0);
        // 第一个已放置，所以从索引 1 位置开始遍历处理
        for (int i = 1; i < len; i++) {
            int another = target - nums[i];
            if (hashMap.containsKey(another)) {
                // 返回两个值对应的下标
                return new int[]{hashMap.get(another), i};
            }
            // key 是值，value 为该值的索引下标
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
