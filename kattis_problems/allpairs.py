import sys

#credit: used floyd-warshall algorith, from geeks for geeks
#links:
# https://www.geeksforgeeks.org/detecting-negative-cycle-using-floyd-warshall/?ref=rp
# https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/

def floyd_warshall(graph, flag):
    for i in range(len(graph)):
        for j in range(len(graph)):
            for k in range(len(graph)):
                if flag:
                    graph[j][k] = min(graph[j][k], graph[i][k] + graph[j][i])
                else:
                    #if there is STILL a shortest path to be found, than
                    # cycle indication. set to -inf so it can be found later
                    if (graph[j][i] + graph[i][k] < graph[j][k]):
                        graph[j][k] = float("-inf")
                    
    return

def calc_dist(graph):
    vals = sys.stdin.readline().strip().split(" ")
    vals = [int(s) for s in vals]

    u = vals[0]
    v = vals[1]

    if graph[u][v] == float("inf"):
        print("Impossible")
    elif graph[u][v] == float("-inf"): #arbitrary short paths
        print("-Infinity")
    else:
        print(graph[u][v])
    return

def initialize(n, m, q):
    graph = [ [float("inf")]* n for i in range(n)]

    for i in range(n):
        graph[i][i] = 0
    
    for i in range(m):
        vals = sys.stdin.readline().strip().split(" ")
        vals = [int(s) for s in vals]

        u = vals[0]
        v = vals[1]
        w = vals[2]
        
        #handle edge cases, ex: same node
        graph[u][v] = min(graph[u][v], w)

    return graph

def main():
    vals = sys.stdin.readline().strip().split(" ")
    vals = [int(s) for s in vals]

    while (max(vals) > 0):
        n = vals[0]
        m = vals[1]
        q = vals[2]

        graph = initialize(n, m, q)

        floyd_warshall(graph, True)
        floyd_warshall(graph, False) #check for neg cycles

        for i in range(q):
            calc_dist(graph)

        #next test case
        vals = sys.stdin.readline().strip().split(" ")
        vals = [int(s) for s in vals]
        if max(vals) > 0:
            print

if __name__ == "__main__":
    main()