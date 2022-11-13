
import sys

def printSimon(n):
    for i in range(0, n):
        line = sys.stdin.readline()
        if line[0:11] == "Simon says ":
            print(line[11:len(line)])

def main():
    n = int(sys.stdin.readline())
    printSimon(n)

if __name__ == "__main__":
    main()

