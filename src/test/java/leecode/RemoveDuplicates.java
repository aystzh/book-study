package leecode;

import org.junit.Test;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 1，1，1，2
 * Created by zhanghuan on 2020/5/25.
 */
public class RemoveDuplicates {

    public int solution(int [] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //慢指针
        int i = 0;
        //快指针
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 2, 2, 3, 4, 5, 5, 6, 7, 7, 8, 8};
        System.out.println(String.format("移除重复数据前的大小为：%d", nums.length));
        int i = solution(nums);
        System.out.println(String.format("移除重复数据后的大小为：%d", i));
    }
}
