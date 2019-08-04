package algorithm.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 广度优先搜索
 * O(V+E)
 * 不能求出最短路径是什么？
 * @author heshuaijun
 * @date 2019/5/19 21:02
 */
public class BreadthFirstSearch {

    public static void main (String[] args) {

        Map<String, List> graph = new HashMap<>();
        graph.put("北京", Arrays.asList("天津", "张家口", "廊坊"));
        graph.put("天津", Arrays.asList("张家口", "济南", "德州"));
        graph.put("张家口", Arrays.asList("承德", "天津"));
        graph.put("廊坊", Arrays.asList("德州", "石家庄"));
        graph.put("济南", new ArrayList());
        graph.put("德州", new ArrayList());
        graph.put("承德", new ArrayList());
        graph.put("石家庄", Arrays.asList("邯郸"));
        graph.put("邯郸", new ArrayList());

        String start = "北京";
        LinkedList<String> list = new LinkedList(graph.get(start));
        Set<String> searched = new HashSet<>();
        searched.add(start);
        String target = "邯郸";
        Set<String> path = new HashSet<>();
        while (!list.isEmpty()){
            String first = list.pop();
            if(searched.contains(first)){
                continue;
            }
            if(target.equals(first)){
                System.out.println("到达：" + first);
                System.out.println(path);
                return;
            }else{
                list.addAll(graph.get(first));
                path.add(first);
                searched.add(first);
            }
        }
        System.out.println(path.size());
    }
}
