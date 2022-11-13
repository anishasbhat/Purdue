import sys

def find_num(arr, tune):
    switch = 0
    cur_instrument = arr[tune[0]]

    for note in tune:
        next_note = arr[note]
        valid = set(next_note).intersection(cur_instrument)
        if len(valid) == 0:
            switch += 1
            cur_instrument = next_note
        else:
            cur_instrument = set(next_note).intersection(cur_instrument)
    return switch

def readInput(n):
    instruments = {}

    for i in range(n):
        line = sys.stdin.readline()
        line = line.rstrip()
        arr = [int(n) for n in line.split()]

        arr = arr[1:]
        for val in arr:
            if val not in instruments:
                instruments[val] = [i + 1]
            else:
                instruments[val].append(i + 1)
    return instruments

def main():
    line = sys.stdin.readline()
    line = line.rstrip()
    arr = line.split(" ")
    n = int(arr[0])
    m = int(arr[1])

    instruments = readInput(n)
    tune = sys.stdin.readline()
    tune = tune.rstrip()
    tune = [int(n) for n in tune.split()]
    print(find_num(instruments, tune))

if __name__ == "__main__":
    main()