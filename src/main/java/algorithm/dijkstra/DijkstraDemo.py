# -*- coding: utf-8 -*-

# 1.构造加权有向无环图
graph = {}
graph["start"] = {"a": 6, "b": 2}
graph["a"] = {"fin": 1}
graph["b"] = {"a": 3, "fin": 5}
graph["fin"] = {}

# 2.创建开销表
infinity = float("inf")
costs = {"a": 6, "b": 2, "fin": infinity}

# 3.创建存储父节点的表
parents = {"a": "start", "b": "start", "fin": None}

# 4.创建存储已经处理过的节点的数组
processed = []


# 5.算法过程
# 找出权重最小的节点，可以节省一些节点的遍历
def find_lowest_cost_node(costs):
    lowest_cost = infinity
    lowest_cost_node = None
    for n in costs:
        cost = costs[n]
        if cost < lowest_cost and n not in processed:
            lowest_cost = cost
            lowest_cost_node = n
    return lowest_cost_node


node = find_lowest_cost_node(costs)
while node is not None:
    cost = costs[node]
    neighbors = graph[node]
    for n in neighbors.keys():
        new_cost = cost + neighbors[n]
        if new_cost < costs[n]:
            costs[n] = new_cost
            parents[n] = node
    processed.append(node)
    node = find_lowest_cost_node(costs)

print parents

