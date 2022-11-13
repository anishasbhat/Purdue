import sys
from collections import defaultdict

def dfs(graph, node, visited):
    if node in graph and node not in visited:
        visited.append(node)
        for val in graph[node]:
            dfs(graph, val, visited)
    return

def judge(m, graph, edge_list, counter):
    def calculate(graph, m):
        visited = []
        visited_rev = []
        dfs(graph, 0, visited)
        return visited

    def calculate_rev(graph, m):
        rev_graph = defaultdict()
        visited = []
        for node in graph:
            for val in graph[node]:
                if val in rev_graph:
                    rev_graph[val].append(node)
                else:
                     rev_graph[val] = [node]

        dfs(rev_graph, 0, visited)
        return visited

    visited = calculate(graph, m)
    visited_rev = calculate_rev(graph, m)
    if len(visited) == m and len(visited_rev) == m or m == 1:
        ret = "Case " + str(counter) + ": valid"
        print(ret)
        return #VALID
    else:
        new_graph = graph.copy()
        for edge in edge_list:
            new_graph[edge[0]].remove(edge[1])
            if edge[1] in new_graph:
                new_graph[edge[1]].append(edge[0])
            else:
                new_graph[edge[1]] = [edge[0]]
            visited = calculate(new_graph, m)
            visited_rev = calculate_rev(new_graph, m)

            if len(visited) == m and len(visited_rev) == m:
                ret = "Case " + str(counter) + ": " + str(edge[0]) + " " + str(edge[1])
                print(ret)
                return
            new_graph[edge[1]].remove(edge[0])
            new_graph[edge[0]].append(edge[1])
    ret = "Case " + str(counter) + ": invalid"
    print(ret)
    return

def main():
    res = []
    counter = 1
    
    line = sys.stdin.readline()
    while (line):
        line = line.split(" ")
        m = int(line[0])
        n = int(line[1])
        graph = defaultdict()
        edge_list = []
        
        for i in range(n):
            line = sys.stdin.readline()
            line = line.split(" ")
            a = int(line[0])
            b = int(line[1])
            edge_list.append((a, b))
            if a in graph:
                graph[a].append(b)
            else:
                graph[a] = [b]
        res.append(judge(m, graph, edge_list, counter))
        counter += 1
        line = sys.stdin.readline()
        
if __name__ == "__main__":
    main()