/**
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:

输入: "3+2*2"
输出: 7
示例 2:

输入: " 3/2 "
输出: 1
示例 3:

输入: " 3+5 / 2 "
输出: 5
说明：

你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/basic-calculator-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

class Solution {
    public int calculate(String s) {
        s = s + '+';
        Stack<Integer> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int val = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c==' ') continue;
            else if(c>='0' && c<='9'){
                val = val * 10 + (c-'0');
            }
            else{
                vals.push(val);
                val = 0;
                if(!ops.empty() && (ops.peek()=='*' || ops.peek()=='/')){
                    int v1 = vals.pop();
                    int v2 = vals.pop();
                    int v = ops.pop()=='*' ? (v1*v2) : (v2/v1);
                    vals.push(v);
                }
                ops.push(c);
            }
        }
        
        vals.push(val);
        int res = 0;
        while(!ops.empty()){
            int sign = ops.pop()=='-' ? -1 : 1;
            int v = vals.pop();
            res += sign * v;
        }
        
        if(!vals.empty()) res += vals.pop();
        return res;
    }
}
