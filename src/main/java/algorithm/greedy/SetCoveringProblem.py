# -*- coding: utf-8 -*-
# 集合覆盖问题

# 贪婪算法 每一步都选择一个电台，它覆盖了最多的未覆盖州

states_uncovered = set(["mt", "wa", "or", "id", "nv", "ut", "ca", "az"])
broadcasts = {}
broadcasts["kone"] = set(["id", "nv", "ut"])
broadcasts["ktwo"] = set(["wa", "id", "mt"])
broadcasts["kthree"] = set(["or", "nv", "ca"])
broadcasts["kfour"] = set(["nv", "ut"])
broadcasts["kfive"] = set(["ca", "az"])
final_broadcasts = set()

while states_uncovered:  # 等价于len(states_uncovered) > 0
    best_station = None
    states_covered = set()
    for station, states_for_station in broadcasts.items():
        intersection = states_for_station & states_uncovered
        if len(intersection) > len(states_covered):
            best_station = station
            states_covered = intersection
    final_broadcasts.add(best_station)
    states_uncovered -= states_covered

print final_broadcasts






