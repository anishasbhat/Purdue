import sys

def find_pivots(arr):
    length = len(arr)
    count = 0

    maxes = [[0] for a in arr]
    mins = [[0] for a in arr]

    currMax = float("-inf")
    for i in range(len(maxes)):
        maxes[i] = max(currMax, arr[i])
        currMax = maxes[i]

    currMin = float("inf")
    for i in range(len(mins) - 1, -1, -1):
        mins[i] = min(currMin, arr[i])
        currMin = mins[i]
    
    for i in range(len(arr)):
        if mins[i] == arr[i] and arr[i] == maxes[i]:
            count += 1
    return count

def readInput():
    n = int(sys.stdin.readline())
    line = sys.stdin.readline()
    line = line.rstrip()
    arr = line.split(" ")

    for i in range(len(arr)):
        arr[i] = int(arr[i])
    return arr

def main():
    arr = readInput()
    print(find_pivots(arr))

if __name__ == "__main__":
    main()