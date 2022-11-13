import sys
def findIndex(word):
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    track = []

    for i in range(len(word)):
        track.append(1)
    
    num_same = inOrder(word, track)
    return len(alphabet) - num_same #subtract alphabet length from the number in order

def inOrder(word, track): #find max num in order
    for i in range(len(word)):
        if i != 0:
            for j in range(i + 1):
                next_val = track[j] + 1
                if word[i] > word[j] and track[i] < next_val:
                    track[i] = next_val
    return track[-1]

def readInput():
    line = sys.stdin.readline()  
    line = line.strip() 
    return line

def main():
    word = readInput()
    num_changes = findIndex(word)
    print(num_changes)

if __name__ == "__main__":
    main()