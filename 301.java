/*

删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。

说明: 输入可能包含了除 ( 和 ) 以外的字符。

示例 1:

输入: "()())()"
输出: ["()()()", "(())()"]
示例 2:

输入: "(a)())()"
输出: ["(a)()()", "(a())()"]
示例 3:

输入: ")("
输出: [""]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        int l=0,r=0;
		//对多余的左右括号计数
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(') l++;
            if(c==')'){
                if(l>0) l--;
                else r++;
            }
        }

        dfs(s,0,l,r);
        return ans;
    }
	
	//深度优先搜索
    private void dfs(String s, int index, int l, int r){
		//符合条件的String
        if(l==0 && r==0 && check(s)){
            ans.add(s);
            return;
        }
        for(int i=index;i<s.length();i++){
			//去除重复
            if(i-1>=index && s.charAt(i)==s.charAt(i-1)) continue;
			//删去多余括号再进行dfs
            if(l>0 && s.charAt(i)=='('){
                dfs(s.substring(0,i)+s.substring(i+1,s.length()),i,l-1,r);
            }
            if(r>0 && s.charAt(i)==')'){
                dfs(s.substring(0,i)+s.substring(i+1,s.length()),i,l,r-1);
            }
        }
    }
	
	//检查String合法性
    private boolean check(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(') count++;
            if(c==')'){
                count--;
                if(count<0) return false;
            }
        }

        return true;
    }
}