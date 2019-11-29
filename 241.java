/*
给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。

示例 1:

输入: "2-1-1"
输出: [0, 2]
解释: 
((2-1)-1) = 0 
(2-(1-1)) = 2
示例 2:

输入: "2*3-4*5"
输出: [-34, -14, -10, -10, 10]
解释: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/different-ways-to-add-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/


//1、设计一个hashmap，存储遍历过的情况
//2、遍历表达式，每当遇到运算符，就进入迭代
//3、将子表达式的运算结果返回，得到最终的答案

import javafx.util.Pair;
class Solution {

    private HashMap<Pair<Integer,Integer>,List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        return dfs(input,0,input.length());
    }

    private List<Integer> dfs(String in, int left, int right){
        Pair<Integer,Integer> p = new Pair<>(left,right);
        if(map.containsKey(p)){
            return map.get(p);
        }
        List<Integer> list = new LinkedList<>();
        for(int i=left;i<right;i++){
            char c = in.charAt(i);
            if(c=='+'||c=='-'||c=='*'){
                List<Integer> l = dfs(in,left,i);
                List<Integer> r = dfs(in,i+1,right);
                for(int a : l){
                    for(int b : r){
                        list.add(ops(c,a,b));
                    }
                }
            }
        }
        if(list.size()==0){
            list.add(Integer.parseInt(in.substring(left,right)));
        }
        map.put(p,list);
        return list;
    }

    private int ops(char op, int a, int b){
        switch(op){
            case'+': return a+b;
            case'-': return a-b;
            case'*': return a*b;
        }
        return 0;
    }
}