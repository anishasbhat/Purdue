import sys


def dfss(graph, node, visited):
    stack = []
    stack.append(node)

    if node in graph:
        visited[node] = True
        for val in graph[node]:
            if val not in visited:
                visited = dfs(graph, val, visited)
    return

def dfs(graph, node, visited):
    stack = []
    stack.append(node)

    while (len(stack) > 0):
        node = stack.pop()
        visited[node] = True
        if node in graph:
            for val in graph[node]:
                if val not in visited:
                    stack.append(val)
    return


def judge(graph, num_tiles):
    min_dominos = num_tiles
    visited = [False] * (num_tiles + 1)

    nodes = graph.keys()
    for node in nodes:
        if visited[node] == False:
            #run dfs
            dfs(graph, node, visited)
            visited[node] = True

    #print(visited)
    return len(visited) - sum(visited) #-1
    #xsprint(min_dominos)
    
def read_data(graph, m):
    for j in range(m):
            line = sys.stdin.readline()
            line = line.rstrip()
            line = line.split(" ")
            x = int(line[0])
            y = int(line[1])

            if x in graph:
                graph[x].append(y)
            else:
                graph[x] = [y]
    return graph

def main():
    n = int(sys.stdin.readline())
    res = []
    for i in range(n):
        line = sys.stdin.readline()
        line = line.rstrip()
        line = line.split(" ")
        num_tiles = int(line[0])
        m = int(line[1])

        #visited array:
        #visited = [(i + 1) for i in range(num_tiles)]
        graph = {}
        graph = read_data(graph, m)
        val = judge(graph, num_tiles)
        res.append(val)
    
    
    for r in res:
        print(r)

if __name__ == "__main__":
    main()