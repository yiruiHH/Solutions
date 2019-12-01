/*
将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。

示例 1:

输入: 123
输出: "One Hundred Twenty Three"
示例 2:

输入: 12345
输出: "Twelve Thousand Three Hundred Forty Five"
示例 3:

输入: 1234567
输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
示例 4:

输入: 1234567891
输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-to-english-words
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

class Solution {
    String words[] = {"Zero", "One", "Two", "Three", "Four", 
                        "Five", "Six", "Seven", "Eight", "Nine",
                        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                        "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String tens[] = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};

    public String numberToWords(int num) {
        if(num<20) return words[num];

        String ans = "";
        if(num/1000000000!=0){
            ans += words[num/1000000000] + " Billion ";
            num = num%1000000000;
        }
        if(num/1000000!=0){
            ans += underThousand(num/1000000) + " Million ";
            num = num%1000000;
        }
        if(num/1000!=0){
            ans += underThousand(num/1000) + " Thousand ";
            num = num%1000;
        }
        ans += underThousand(num);
        return ans.trim();
    }

    private String underThousand(int num){
        String ans = "";
        if(num/100!=0){
            ans += words[num/100] + " Hundred ";
            num = num%100;
        }
        if(num<20 && num!=0){
            ans += words[num];
            return ans;
        }
        if(num/10!=0){
            ans += tens[num/10];
            num = num%10;
        }
        if(num!=0){
            ans += " " + words[num];
        }
        return ans.trim();
    }
}
