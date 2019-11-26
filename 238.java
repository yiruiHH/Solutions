/*
给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/product-of-array-except-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

class Solution {
    //思路：乘积 = 左边的数的乘积 * 右边的数的乘积
    public int[] productExceptSelf(int[] nums) {
        int k = 1;
        int ans[] = new int[nums.length];
        //先计算左边的乘积
        for(int i=0;i<nums.length;i++){
            ans[i] = k;
            k *= nums[i];
        }
        k = 1;
        //再计算右边的数的乘积
        for(int i=nums.length-1;i>=0;i--){
            ans[i] = k * ans[i];
            k *= nums[i];
        }
        return ans;
    }
}
