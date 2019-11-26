/*
给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

示例 1:

输入: [3,2,3]
输出: [3]
示例 2:

输入: [1,1,1,3,3,2,2,2]
输出: [1,2]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

class Solution {
    
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if(nums==null || nums.length==0) return ans;
        int a = nums[0];
        int b = nums[0];
        int count_a = 0;
        int count_b = 0;
        
        for(int num : nums){
            if(num==a){
                count_a++;
                continue;
            }
            if(num==b){
                count_b++;
                continue;
            }

            if(count_a==0){
                a=num;
                count_a++;
                continue;
            }
            if(count_b==0){
                b=num;
                count_b++;
                continue;
            }
            count_a--;
            count_b--;
        }

        count_a = 0;
        count_b = 0;
        for(int num : nums){
            if(num==a) count_a++;
            else if(num==b) count_b++;
        }

        if(count_a>nums.length/3) ans.add(a);
        if(count_b>nums.length/3) ans.add(b);
        return ans;
    }
    
    
    /* 解答版本
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        //初始化：定义两个候选人及其对应的票数
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0;
        int countB = 0;
        //遍历数组
        for (int num : nums) {
            if (num == candidateA) {
                countA++;//投A
                continue;
            }
            if (num == candidateB) {
                countB++;//投B
                continue;
            }

            //此时当前值和AB都不等，检查是否有票数减为0的情况，如果为0，则更新候选人
            if (countA == 0) {
                candidateA = num;
                countA++;
                continue;
            }
            if (countB == 0) {
                candidateB = num;
                countB++;
                continue;
            }
            //若此时两个候选人的票数都不为0，且当前元素不投AB，那么A,B对应的票数都要--;
            countA--;
            countB--;
        }

        //上一轮遍历找出了两个候选人，但是这两个候选人是否均满足票数大于N/3仍然没法确定，需要重新遍历，确定票数
        countA = 0;
        countB = 0;
        for (int num : nums) {
            if (num == candidateA)
                countA++;
            else if (num == candidateB)
                countB++;
        }
        if (countA > nums.length / 3)
            res.add(candidateA);
        if (countB > nums.length / 3)
            res.add(candidateB);
        return res;

    }
    */
}
