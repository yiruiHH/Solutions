/***
 *  一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
    我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。

    骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

    有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
    其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。

    为了尽快到达公主，骑士决定每次只向右或向下移动一步。

    编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。

    例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。

    -2 (K)	-3	  3
    -5	    -10	  1
    10	    30	  -5 (P)
     

    说明:

    骑士的健康点数没有上限。

    任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/dungeon-game
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution {
    
    //记忆化回溯
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon==null || dungeon.length==0 || dungeon[0].length==0){
            return 0;
        }
        int memory[][] = new int[dungeon.length][dungeon[0].length];
        for(int i=0;i<dungeon.length;i++){
            for(int j=0;j<dungeon[0].length;j++){
                memory[i][j] = Integer.MAX_VALUE;
            }
        }
        return dfs(dungeon,memory,0,0)+1;
    }
    
    private int dfs(int[][] dungeon, int[][] memory, int row, int col){
        int r = dungeon.length;
        int c = dungeon[0].length;
        
        if(row>=r || col>=c){
            return Integer.MAX_VALUE;
        }
        
        if(memory[row][col]!=Integer.MAX_VALUE){
            return memory[row][col];
        }
        
        if(row==r-1 && col==c-1){
            if(dungeon[row][col]>=0){
                memory[row][col] = 0;
                return 0;
            }
            else{
                memory[row][col] = -dungeon[row][col];
                return -dungeon[row][col];
            }
        }
        
        int rightmin = dfs(dungeon,memory,row,col+1);
        int downmin = dfs(dungeon,memory,row+1,col);
        
        int needmin = Math.min(rightmin,downmin) - dungeon[row][col];
        
        int res;
        if(needmin>0){
            res = needmin;
        }
        else{
            res = 0;
        }
        memory[row][col] = res;
        return res;
    }
    
    //动态规划
    public int calculateMinimumHP2(int[][] dungeon) {
        if(dungeon==null || dungeon.length==0 || dungeon[0].length==0){
            return 0;
        }
        
        int r = dungeon.length;
        int c = dungeon[0].length;
        
        int dp[][] = new int [r][c];
        
        dp[r-1][c-1] = dungeon[r-1][c-1]>0 ? 0 : -dungeon[r-1][c-1];
        
        for(int i=r-2;i>=0;i--){
            int temp = dp[i+1][c-1] -dungeon[i][c-1];
            dp[i][c-1] = temp>0 ? temp : 0;
        }
        
        for(int i=c-2;i>=0;i--){
            int temp = dp[r-1][i+1] -dungeon[r-1][i];
            dp[r-1][i] = temp>0 ? temp : 0;
        }
        
        for(int i=r-2;i>=0;i--){
            for(int j=c-2;j>=0;j--){
                int temp = Math.min(dp[i+1][j],dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = temp>0 ? temp : 0;
            }
        }
        
        return dp[0][0]+1;
    }
}