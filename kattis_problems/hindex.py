
import sys

def findIndex(arr):
    arr.sort()
    h_index = 0
    length = len(arr)

    for h in range(length - 1, -1, -1):
        actual_index = length - h
        if arr[h] >= actual_index:
            h_index = actual_index
    print(h_index)

def readInput():
    n = int(sys.stdin.readline())
    arr = []

    for i in range(n):
        arr.append(int(sys.stdin.readline()))
    
    return arr

# [4, 3, 2]

def main():
    arr = readInput()
    findIndex(arr)

if __name__ == "__main__":
    main()