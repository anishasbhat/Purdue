import sys

def thanos(arr, n):
    num = 0
    i = len(arr) - 1
    while i > 0:
        #print(arr[i - 1], arr[i])
        if arr[i - 1] >= arr[i]:
            #print(arr[i - 1], arr[i])
            new_val = arr[i] - 1
            #print(new_val)
            if (new_val < 0 ): return 1
            num += arr[i - 1] - new_val
            arr[i - 1] = new_val
        i -= 1
    
    return num

def main():
    line = sys.stdin.readline().split(" ")
    n = int(line[0])
    line = sys.stdin.readline().strip('\n')
    s_arr = line.split(" ")
    arr = []
    for i in s_arr:
        arr.append(int(i))
    print(thanos(arr, n))
    

if __name__ == "__main__":
    main()