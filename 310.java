/*
对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式

该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。

你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。

示例 1:

输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

输出: [1]
示例 2:

输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

输出: [3, 4]
说明:

 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
树的高度是指根节点和叶子节点之间最长向下路径上边的数量。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-height-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/
/*
基本思想：1、确定叶子节点 2、剥离叶子节点 3、重新计算叶子节点 4、重复2和3
*/
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new LinkedList<>();
        //一跳节点需要单独考虑
        if(n==1){
            ans.add(0);
            return ans;
        }

        int degree[] = new int[n];
        //改变图的表示方式
        List<List<Integer>> graph = new LinkedList<>()；
        for(int i=0;i<n;i++){
            graph.add(new LinkedList<Integer>());
        }
        //计算节点的度数，同时将图写入
        for(int[] edge : edges){
            degree[edge[0]]++;
            degree[edge[1]]++;
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        //度为1的节点，即叶子节点，存入队列
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(degree[i]==1){
                queue.offer(i);
            }
        }
        //删去所有叶子节点，再重新计算各个节点的度数，在队列中加入新的叶子节点
        //当一层一层剥离叶子节点后，剩余的的节点即为所求节点
        while(!queue.isEmpty()){
            ans = new LinkedList<>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                int cur = queue.poll();
                ans.add(cur);
                List<Integer> e = graph.get(cur);
                for(Integer in : e){
                    degree[in]--;
                    if(degree[in]==1){
                        queue.offer(in);
                    }
                }
            }
        }
        return ans;
    }
}
