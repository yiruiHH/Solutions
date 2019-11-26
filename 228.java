/**
给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

示例 1:

输入: [0,1,2,4,5,7]
输出: ["0->2","4->5","7"]
解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
示例 2:

输入: [0,2,3,4,6,8,9]
输出: ["0","2->4","6","8->9"]
解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/summary-ranges
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        LinkedList ans = new LinkedList<>();
        if(nums==null || nums.length==0) return ans;
        boolean over = true;
        int lastnum=0,nownum=0,leftnum=0;
        String s = "";
        for(int i=0;i<nums.length;i++){
            nownum = nums[i];
            if(over){
                if(i==nums.length-1){
                    ans.add(String.valueOf(nownum));
                    break;
                }
                s = String.valueOf(nownum);
                over = false;
                lastnum = nownum;
                leftnum = nownum;
                continue;
            }
            else{
                if(nownum-1==lastnum){
                    lastnum = nownum;
                    if(i==nums.length-1){
                        s += "->" + String.valueOf(lastnum);
                        ans.add(s);
                    }
                    continue;
                }
                else{
                    s += "->" + String.valueOf(lastnum);
                    if(lastnum==leftnum){
                        s = String.valueOf(lastnum);
                    }
                    ans.add(s);
                    over = true;
                    i--;
                }
            }
        }
        return ans;
    }
}
