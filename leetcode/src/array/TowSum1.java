package array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Daniel Liu
 * @date 2022-11-10
 */
public class TowSum1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TowSum1().toSum(new int[]{2, 4, 8}, 12)));
    }

    public int[] toSum(int[] nums, int target) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        int num = nums[0];
        hashMap.put(num, 0);
        for (int i = 1; i < nums.length; i++) {
            int value = target - nums[1];
            if (hashMap.containsKey(value)) {
                return new int[]{(int) hashMap.get(value),i};
            }
            // 值不匹配加入到hashmap
            hashMap.put(value, i);
        }
//        return nums;
        throw new RuntimeException("");
    }
}
