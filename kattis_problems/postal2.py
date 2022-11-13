from functools import total_ordering
import sys

def taketrip(coords, k): 
    total = 0
    while (len(coords) > 0):
        cur_dist = coords[len(coords) - 1][0]
        total += cur_dist * 2
        trip(coords, k)
    return total

def trip(coords, letters_left):
    i = len(coords) - 1
    while i > -1:
        to_deliver = coords[i][1]
        if letters_left < to_deliver:
            coords[i][1] -= letters_left
            return
        elif letters_left >= to_deliver:
            letters_left -= to_deliver
            coords.pop(i)
        i -= 1

def main():
    line = sys.stdin.readline().split(" ")
    n = int(line[0])
    k = int(line[1])
    coords = []
    neg = []
    #add to list
    for i in range(n):
        line = sys.stdin.readline().split(" ")
        line[0] = (int(line[0]))
        line[1] = int(line[1])
        if (line[0] < 1):
            line[0] = abs(int(line[0]))
            neg.append(line)
        else:
            coords.append(line)

    neg.reverse()
    print(taketrip(neg, k) + taketrip(coords, k))

if __name__ == "__main__":
    main()