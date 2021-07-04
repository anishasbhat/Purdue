import java.lang.Math;

public class PatientQueue {
    private Patient[] array;
    private int num_patients = 0;
    
    //constructor: set variables
    //capacity = initial capacity of array 
    public PatientQueue(int capacity) {
        array = new Patient[capacity];
    }

    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted

    public int insert(Patient p) {
        if (num_patients == array.length) {
            return -1;
        }

        if (num_patients == 0) {
            array[0] = p;
            num_patients++;
            return 0;
        }

        array[num_patients] = p;
        num_patients++;
        int size = num_patients - 1;
        int child_index = size;
        int parent_index = (int)Math.floor((size - 1)/2);
        while (array[child_index].compareTo(array[parent_index]) > 0) {

            Patient temp = array[child_index];
            array[child_index] = array[parent_index];
            array[parent_index] = temp;

            //array[child_index].setPosInQueue(child_index);
            //array[parent_index].setPosInQueue(parent_index);

            child_index = (int)Math.floor((child_index - 1)/2);
            parent_index = (int)Math.floor((child_index - 1)/2);
        }
        return child_index;
    }

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
        if (isEmpty()) return null;
        Patient del = array[0];

        //overwrite
        for (int i = 0; i < num_patients - 1; i++) {
            array[i] = array[i + 1];
        }
        array[num_patients - 1] = null;
        num_patients--;

        int count = 0;
        while (count < num_patients) {
            //case 1 vs 2
            Patient parent = array[count];
            Patient left_child = null;
            Patient right_child = null;
            if ((count*2 + 1) < num_patients) {
                left_child  = array[count*2 + 1];
            }
            if ((count*2 + 2) < num_patients) {
                right_child  = array[count*2 + 2];
            }

            if (left_child != null && right_child == null) {
                if (left_child.compareTo(parent) > 0) {
                    Patient temp = left_child;
                    array[count*2 + 1] = array[count];
                    array[count] = temp;

                    array[count*2 + 1].setPosInQueue(count*2 + 1);
                    array[count].setPosInQueue(count);
                }
            }
            else if (left_child != null && right_child != null) {
                if (left_child.compareTo(right_child) > 0 && left_child.compareTo(parent) > 0) {
                    Patient temp = left_child;
                    array[count*2 + 1] = array[count];
                    array[count] = temp;

                    array[count*2 + 1].setPosInQueue(count*2 + 1);
                    array[count].setPosInQueue(count);
                }

                else if (right_child.compareTo(left_child) > 0 && right_child.compareTo(parent) > 0) {
                    Patient temp = right_child;
                    array[count*2 + 2] = array[count];
                    array[count] = temp;

                    array[count*2 + 2].setPosInQueue(count*2 + 2);
                    array[count].setPosInQueue(count);
                }
            }
            count++;
        }
        return del;
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
        if(isEmpty()) return null;
        return array[0];
    }

    //return the number of patients currently in the queue
    public int size() {
        if (isEmpty()) return 0;
        return num_patients;
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
        if (array.length == 0 || num_patients == 0) {
            return true;
        }
        return false;
    }

    //used for testing underlying data structure
    public Patient[] getArray() {
	return array;
    }
}
    