# -*- coding: utf-8 -*-
# 个人想法：解决迪克斯特拉算法的负权边问题，如果某次遍历修改了已经处理过的节点，就将此节点再次处理一遍，
# 如果遍历到目标节点时，还有节点未遍历，需要将未遍历的节点都遍历一遍才结束。m