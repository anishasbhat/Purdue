import sys

def compute(arr, n, k):
    counter = 0
    for i in range (2, n + 1):
        arr.append(i)
    
    #fine
    for i in range(0, len(arr)):
        val = arr[i]
        if (val != -1): #new
            counter += 1
            if (counter == k): return val
            val2 = arr[i + 1]
            for val2 in range(len(arr)):
                val2 = arr[j]
                if (val2 % val == 0 and val != -1 and val2 != -1):
                    counter += 1
                    if (counter == k):
                        return val2
                    arr[j] = -1
            arr[i] = -1
    return 0

def main():
    n, k = map(int,sys.stdin.readline().split())
    arr = []
    val = compute(arr,  n , k)
    print(val)
    

if __name__ == "__main__":
    main()