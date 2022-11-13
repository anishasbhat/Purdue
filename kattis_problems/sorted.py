from subprocess import list2cmdline
import sys

def main():
    line = sys.stdin.readline()
    n = int(line)
    finallist = []
    while (True):
        list1 = []
        list2 = []
        for i in range(0, n):
            list1.append(int(sys.stdin.readline()))
        for i in range(0, n):
            list2.append(int(sys.stdin.readline()))
        
        #sort lists
        nextn = int(sys.stdin.readline())
        list1sorted = sorted(list1)
        list2sorted = sorted(list2)

        #print list 2
        newlist = []
        for i in range(len(list1)):
            element = list1[i]
            ind = list1sorted.index(element)
            newlist.append(list2sorted[ind])
        finallist.append(newlist)
        
        #print new line
        #start again if needed
        if (nextn == 0):
            break
        else:
            n = nextn
    
    for listcur in finallist:
        for element in listcur:
            print(element)
        if listcur != finallist[-1]:
            print()

if __name__ == "__main__":
    print(2 + (10-2) / 2)
    print(2 + ((10-2) / 2))
    main()