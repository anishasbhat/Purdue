import sys

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
    #read input
    line = sys.stdin.readline().split(" ")
    n = int(line[0])
    k = int(line[1])
    coords = []
    for i in range(n):
        line = sys.stdin.readline().split(" ")
        line[0] = abs(int(line[0]))
        line[1] = int(line[1])
        coords.append(line)

    #make trips for each list type, calculate total dist
    total = 0
    length = len(coords)
    print(coords)
    while (length > 0):
        cur_dist = coords[length - 1][0]
        total += cur_dist * 2
        trip(coords, k)
        length = len(coords)
    
    print(total)
    #print(strings)

if __name__ == "__main__":
    main()