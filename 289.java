/*

根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

示例:

输入: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/game-of-life
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

class Solution {
    public void gameOfLife(int[][] board) {
        int r = board.length;
        int c = board[0].length;
        int temp[][] = new int[r][c];

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                temp[i][j] = check(i,j,board);
            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                board[i][j] = temp[i][j];
            }
        }
    }

    private int check(int r, int c, int[][] board){
        int liveCell = 0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(i==0 && j==0) continue;
                if(r+i<0 || r+i>=board.length || c+j<0 || c+j>=board[0].length) continue;
                liveCell += board[r+i][c+j];
            }
        }
        //System.out.println("liveCell="+liveCell);
        if(board[r][c]==1){
            if(liveCell<2 || liveCell>3) return 0;
            else return 1;
        }
        else{
            return liveCell==3 ? 1 : 0;
        }
    }
}

