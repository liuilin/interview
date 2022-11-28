package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 用暴力递归需要重复循环好多遍
 * 改用空间换时间的方式，采用 HashSet
 */
class FindRepeatNumber {
    public static void main(String[] args) {
        int repeatNumber = new FindRepeatNumber().findRepeatNumber(new int[]{2, 3, 1, 0, 4, 5, 5});
        System.out.println("repeatNumber = " + repeatNumber);
    }
    public int findRepeatNumber(int[] nums) {

        // HashSet 的特点是不会存储重复元素
        // 所以可以利用 HashSet 来查找出重复的元素
        Set<Integer> dic = new HashSet<>();

        // 遍历数组，设置此时遍历的元素为 num
        for(int num : nums) {
            
            // 如果发现 dic 中已经存储了 num
            // 那么说明找到了重复的那个元素
            if(dic.contains(num)) {
                // 把 num 这个结果进行返回
                return num;

            // 否则的话，说明 dic 中还没有存储 num
            }else{
                // 把 num 添加到 dic 中
                dic.add(num);
            }
           
        }

        // 由于 nums 中所有的数字都在 0 ～ n-1 的范围内
        // 所以负数，比如 -1 必然不在 nums 这个范围内
        // 如果没有找到重复的数字，那么返回 -1
        return -1;
    }
}