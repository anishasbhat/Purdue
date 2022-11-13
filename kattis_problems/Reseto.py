import sys

def compute2(arr, n, k):
    barr = []
    for i in range (2, n + 1):
        barr(i)
    return k;

def compute(arr, n, k):
    counter = 0
    for i in range (2, n + 1):
        arr.append(i)

    for i in range(0, len(arr)):
        val = arr[i]
        if (val != -1):
            counter += 1
            if (counter == k): return val

            for j in range(i, len(arr), val):
                val2 = arr[j]
                if (val2 != val and val != -1 and val2 != -1):
                    counter += 1
                    if (counter == k):
                        return val2
                    arr[j] = -1
                j += val
            arr[i] = -1
    return 0

def main():
    n, k = map(int,sys.stdin.readline().split())
    arr = []
    val = compute(arr,  n , k)
    print(val)
    

if __name__ == "__main__":
    main()