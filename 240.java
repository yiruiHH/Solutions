/*
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/
/*
算法：
首先，我们初始化一个指向矩阵左下角的 (row，col)指针。
然后，直到找到目标并返回 true（或者指针指向矩阵维度之
外的(row，col) 为止，我们执行以下操作：如果当前指向的
值大于目标值，则可以 “向上” 移动一行。 否则，如果当前
指向的值小于目标值，则可以移动一列。不难理解为什么这样
做永远不会删减正确的答案；因为行是从左到右排序的，所
以我们知道当前值右侧的每个值都较大。 因此，如果当前值
已经大于目标值，我们知道它右边的每个值会比较大。也可以
对列进行非常类似的论证，因此这种搜索方式将始终在矩阵中
找到目标（如果存在）。
*/


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) return false;
        int row = matrix.length-1;
        int col = 0;

        while(row>=0 && col<matrix[0].length){
            if(matrix[row][col]==target){
                return true;
            }
            else if(matrix[row][col]>target){
                row--;
            }
            else{
                col++;
            }
        }

        return false;
    }
}
