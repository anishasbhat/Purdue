public class SortMerge
{

    public static void sort(int[] arr)
    {
        Queue<int[]> q = new Queue<int[]>();
        Queue<Integer> s = new Queue<Integer>();

        //find indices and store in queue
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) s.enqueue(i);
            else if (arr[i] < arr[i - 1]) {
                s.enqueue(i - 1);
                s.enqueue(i);
                if (i == arr.length - 1 && s.size() % 2 == 1) {
                    s.enqueue(i);
                }
            }
            else if (i == arr.length - 1 && s.size() % 2 == 1) {
                s.enqueue(i);
            }
        }

        //create queue of subarrays
        while (!s.isEmpty()) {
            int beg = s.dequeue();
            int end = s.dequeue();

            int index = 0;
            int[] array = new int[end - beg + 1];
            for(int i = beg; i <= end; i++) {
                array[index] = arr[i];
                index++;
            }
            q.enqueue(array);
        }

        //sort arrays
        while (q.size() != 1) {
            q.enqueue(merge(q.dequeue(), q.dequeue()));
        }

        int[] copy = q.dequeue();
        for (int i = 0; i < copy.length; i++) {
            arr[i] = copy[i];
        }
    }

    public static int[] merge(int[] a, int[] b) {
        int[] arr = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                arr[k] =  a[i];
                k++;
                i++;
            } else {
                arr[k] =  b[j];
                k++;
                j++;
            }
        }
        while (i < a.length) {
            arr[k] = a[i];
            k++;
            i++;
        }

        while (j < b.length) {
            arr[k] = b[j];
            k++;
            j++;
        }
        return arr;
    }

}
	