/**
* 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。

  示例 1:

  输入: nums = [1,2,3,1], k = 3, t = 0
  输出: true
  示例 2:

  输入: nums = [1,0,1,1], k = 1, t = 2
  输出: true
  示例 3:

  输入: nums = [1,5,9,1,5,9], k = 2, t = 3
  输出: false

  来源：力扣（LeetCode）
  链接：https://leetcode-cn.com/problems/contains-duplicate-iii
  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

class Solution {
    /*
    public boolean containsNearbyAlmostDuplicate_1(int[] nums, int k, int t) {
        int n = nums.length;
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<Math.min(n,i+k+1);j++){
                if(j!=i && j-i<=k && Math.abs((long)nums[j]-(long)nums[i])<=(long)t){
                    System.out.println(i);
                    System.out.println(j);
                    return true;
                }
            }
        }
        return false;
    }
    */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0;i<n;i++){
            Integer s = set.ceiling(nums[i]);
            if(s!=null && s<=nums[i]+t) return true;
            
            Integer g = set.floor(nums[i]);
            if(g!=null && g+t>=nums[i]) return true;
            
            set.add(nums[i]);
            if(set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        
        return false;
    }
}
