/*

给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

示例 1:

输入: "bcabc"
输出: "abc"
示例 2:

输入: "cbacdcbc"
输出: "acdb"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-duplicate-letters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

class Solution {
    public String removeDuplicateLetters(String s) {
        if(s.length()<2) return s;
        Stack<Character> stack = new Stack<>();

        boolean appear[] = new boolean[26];
        int lastAppearIndex[] = new int[26];

        for(int i=0;i<s.length();i++){
            lastAppearIndex[s.charAt(i)-'a'] = i;
        }

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(!appear[c-'a']){
                while(!stack.empty() && c-stack.peek()<0 && lastAppearIndex[stack.peek()-'a']>i){
                    appear[stack.peek()-'a'] = false;
                    stack.pop();
                }
                stack.push(c);
                appear[c-'a'] = true;
            }
        }

        String ans = "";
        while(!stack.empty()){
            ans += stack.pop();
        }

        return new StringBuffer(ans).reverse().toString();
    }
}
