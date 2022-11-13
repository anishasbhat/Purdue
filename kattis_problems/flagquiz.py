
import numbers
import sys

def numchanges(str1, str2):
    num = 0
    for i  in range(len(str1)):
        if str1[i] != str2[i]:
            num += 1
    return num


def flagquiz(n):
    lines = []
    strings = []
    #creating array of words
    for i in range(n):
        line = sys.stdin.readline()
        line = line.rstrip()
        strings.append(line)
        arr = line.split(",")
        lines.append(arr)
    #print(strings)

    #double for loop to check ceach pair
    minNums = len(lines[0])
    ptrs = []

    #print("hi")
    for i in range(len(lines)):
        maxNums = 0
        for j in range(len(lines)):
            num = numchanges(lines[i], lines[j])
            if (num >= maxNums):
                maxNums = num
    
        if (maxNums <= minNums):
            if (maxNums < minNums):
                ptrs = []
                minNums = maxNums
            ptrs.append(i)


    #print("printing out final result")
    for i in range(len(ptrs)):
        print(strings[ptrs[i]])

def main():
    line = sys.stdin.readline()
    n = int(sys.stdin.readline())
    flagquiz(n)

if __name__ == "__main__":
    main()

# go through list
# split by , add all to a list 
# so all the first words are in a list, second words, etc.
# find the most commmon word in each list, piece that together, return
