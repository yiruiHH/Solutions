/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

/*
动态规划：
 定义：dp[i]  数组中从0到i中最长上升子序列的长度
 转移方程：
 对于j属于[0,i),分为以下两种情况
 1、nums[i]>nums[j],这种情况下，nums[i]可以接在之前的最长子序列之后，则dp[i] = Math.max(dp[i],dp[j]+1);
 2、nums[i]<=nums[j],直接跳过
初始状态：dp[i]所有元素置 11，含义是每个元素都至少可以单独成为子序列，此时长度都为 11。
返回值：返回 dp[] 列表最大值，即可得到全局最长上升子序列长度。

*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp,1);
        int ans = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]) dp[i] = Math.max(dp[i],dp[j]+1);
            }
            ans = Math.max(ans,dp[i]);
        }

        return ans;
    }

}