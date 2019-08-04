package algorithm.breadthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author heshuaijun
 * @date 2019/8/4 21:27
 */
public class BroadFristSearchAlgorithm{

    public static void main (String[] args) {

        Graph g = new Graph(new BroadFristSearchAlgorithm());
        //添加顶点
        g.addVertex("北京");
        g.addVertex("天津");
        g.addVertex("张家口");
        g.addVertex("廊坊");
        g.addVertex("济南");
        g.addVertex("德州");
        g.addVertex("承德");
        g.addVertex("石家庄");
        g.addVertex("邯郸");
        //添加边
        g.addEdge("北京", "天津");
        g.addEdge("北京", "张家口");
        g.addEdge("北京", "廊坊");
        g.addEdge("天津", "张家口");
        g.addEdge("天津", "济南");
        g.addEdge("天津", "德州");
        g.addEdge("张家口", "承德");
        g.addEdge("廊坊", "德州");
        g.addEdge("廊坊", "石家庄");
        g.addEdge("石家庄", "邯郸");


        g.done();
        g.setFirstVertex("北京");
        Stack<String> result = g.findPathTo("邯郸");
        System.out.println("BFS: From [北京] to [邯郸]:");
        while (!result.isEmpty()) {
            System.out.println(result.pop());
        }

    }
    // 保存已经访问过的地点
    private List<String> visitedVertex;
    // 保存最短路径
    private Map<String, String> path;

    public void perform(Graph g, String firstVertex) {
        if (null == visitedVertex) {
            visitedVertex = new ArrayList<>();
        }
        if (null == path) {
            path = new HashMap<>();
        }
        BFS(g, firstVertex);
    }

    public Map<String, String> getPath() {
        return path;
    }

    private void BFS(Graph g, String firstVertex){
        Queue<String> queue = new LinkedList<>();
        //标记起点
        visitedVertex.add(firstVertex);
        // 起点入列
        queue.add(firstVertex);
        while(!queue.isEmpty()){
            String fromVertex = queue.poll();  //返回队列头部，或null，如果队列为空
            List<String> toBeVisitedVertex = g.getAdj().get(fromVertex);
            for (String toVertex : toBeVisitedVertex) {
                if (!visitedVertex.contains(toVertex)) {
                    visitedVertex.add(toVertex);
                    path.put(toVertex, fromVertex);
                    queue.add(toVertex);
                }
            }
        }
    }


    static class Graph {
        // 图的起点
        private String firstVertex;
        // 邻接表
        private Map<String, List<String>> adj = new HashMap<>();
        // 遍历算法
        private BroadFristSearchAlgorithm algorithm;

        public Graph(BroadFristSearchAlgorithm algorithm) { //策略模式，将Graph与算法分离
            this.algorithm = algorithm;
        }

        public void setFirstVertex(String firstVertex) {
            this.firstVertex = firstVertex;
        }

        /**
         * 执行算法
         */
        public void done() {
            algorithm.perform(this, firstVertex);
        }
        /**
         * 得到从起点到{@code vertex}点的最短路径
         * @param vertex
         * @return
         */
        public Stack<String> findPathTo(String vertex) {
            Stack<String> stack = new Stack<>();
            stack.add(vertex);
            Map<String, String> path = algorithm.getPath();
            for (String location = path.get(vertex) ; !location.equals(firstVertex) ; location = path.get(location)) {
                stack.push(location);
            }
            stack.push(firstVertex);
            return stack;
        }
        /**
         * 添加一条边
         */
        public void addEdge(String fromVertex, String toVertex) {
            if (firstVertex == null) {
                firstVertex = fromVertex;
            }
            adj.get(fromVertex).add(toVertex);
            // 无向图
            adj.get(toVertex).add(fromVertex);
        }
        /**
         * 添加一个顶点
         */
        public void addVertex(String vertex) {
            adj.put(vertex, new ArrayList<String>());
        }

        public Map<String, List<String>> getAdj() {
            return adj;
        }
    }
}

