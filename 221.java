/**
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximal-square
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class Solution{
    public static void main(String args[]){
        int[][] s = {{0,1,1,0},
                     {0,1,1,0},
                     {0,0,1,0},
                     {0,1,0,0}};
        System.out.println(maximalSquare(s));
    }

    public static int maximalSquare(int[][] matrix) {
        if(matrix==null || matrix.length==0) return 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int maxSquare = 0;
        int[][] dp = new int[r+1][c+1];
        for(int i=1;i<=r;i++){
            for(int j=1;j<=c;j++){
                if(matrix[i-1][j-1]==1){
                    dp[i][j] = Math.min(Math.min(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1])+1;
                    maxSquare = Math.max(maxSquare,dp[i][j]);
                }
                
            }
        }

        return maxSquare * maxSquare;
    }
}
