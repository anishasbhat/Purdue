import sys
from collections import defaultdict
from queue import PriorityQueue

#credit: used geeks for geeks kahn's algorithm for topological sort
#link: https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/

def topSort(adj_list):
    in_deg = defaultdict()

    for key in adj_list:
        in_deg[key] = len(adj_list[key])
    queue = []
    for val in in_deg:
        if in_deg[val] == 0:
            queue.append(val)
    
    visited = 0
    res = []
    queue.sort()
    while queue:
        cur = queue.pop(0)
        res.append(cur)
        for val in adj_list:
            if cur in adj_list[val]:
                in_deg[val] -= 1
                if in_deg[val] == 0:
                    queue.append(val)
        queue.sort()
        visited += 1

    if visited != len(adj_list):
        print("cannot be ordered")
        return
    
    for r in res:
        print(r)
    return

def main():
    res = []

    n = int(sys.stdin.readline())
    while (n != 0):
        adj_list = defaultdict()
        for i in range(n):
            line = sys.stdin.readline().strip().split(" ")
            adj_list[line[0]] = line[1:]
        topSort(adj_list)
        n = int(sys.stdin.readline())
        if n != 0:
            print
if __name__ == "__main__":
    main()