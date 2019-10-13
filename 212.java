/**
 *  给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

    示例:

    输入: 
    words = ["oath","pea","eat","rain"] and board =
    [
    ['o','a','a','n'],
    ['e','t','a','e'],
    ['i','h','k','r'],
    ['i','f','l','v']
    ]

    输出: ["eat","oath"]
    说明:
    你可以假设所有输入都由小写字母 a-z 组成。

    提示:

    你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
    如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。


    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/word-search-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        LinkedList<String> ans = new LinkedList<>();
        if(board==null || board.length==0 || board[0].length==0) return ans;
        
        for(String s : words){
            char c = s.charAt(0);
            flag:
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j]==c){
                        boolean[][] used = new boolean[board.length][board[0].length];
                        if(dfs(s,board,used,0,i,j)){
                            ans.add(s);
                            break flag;
                        }
                    }
                }
            }
            
        }
        return ans;
    }
    
    private boolean dfs(String s, char[][] board, boolean[][] used, int index, int row, int col){
        int r = board.length;
        int c = board[0].length;
        
        if(row>=r || col>=c || row<0 || col<0) return false;
        
        if(used[row][col]) return false;
        
        if(board[row][col]==s.charAt(index)){
            used[row][col] = true;
            if(index==s.length()-1) return true;
            
            boolean flag =  dfs(s,board,used,index+1,row+1,col) ||
                   dfs(s,board,used,index+1,row-1,col) ||
                   dfs(s,board,used,index+1,row,col+1) ||
                   dfs(s,board,used,index+1,row,col-1);
            
            if(!flag) used[row][col] = false;
            return flag;
        }
        
        return false;
    }
}