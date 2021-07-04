import java.util.ArrayList;

public class NewPatientQueue {
    private Patient[] array;
    private PHashtable table;
    private int num_patients = 0;

    /*TO BE COMPLETED IN PART 1*/

    //constructor: set variables
    //capacity = initial capacity of array
    public NewPatientQueue(int capacity) {
        array = new Patient[capacity];
        table = new PHashtable(capacity);
    }

    public void  swim(int child_index, int parent_index, int insert) {
        while (array[child_index] != null && array[parent_index] != null
                && child_index > 0 && array[child_index].compareTo(array[parent_index]) > 0) {
            swap(child_index, parent_index);

            child_index = parent_index;
            parent_index = (child_index - 1) / 2;
        }
        if (insert == 1){
            table.put(array[child_index]);
        }
    }

    public void sink(Patient p) {
        int index = p.posInQueue();
        Patient l = null;
        Patient r = null;
        Patient replace = p;

        if (2*index + 1 < num_patients && array[2*index + 1] != null) {
            l = array[2*index + 1];
            if (l.compareTo(replace) > 0) replace = l;
        }
        if (2*index + 2 < num_patients && array[2*index + 2] != null) {
            r = array[2*index + 2];
            if (r.compareTo(replace) > 0) replace = r;
        }
        if (replace != p) {
            //swap(replace.posInQueue(), p.posInQueue());

            if (replace != null && p != null) {
                int child_ind = replace.posInQueue();
                int other_index = p.posInQueue();
                Patient temp = replace;
                array[child_ind] = p;
                array[other_index] = temp;

                replace.setPosInQueue(other_index);
                p.setPosInQueue(child_ind);

            }
            sink(replace);
        }

    }

    public void  swap(int child_ind, int other_index) {
        if (array[child_ind] != null && array[other_index] != null) {
            Patient temp = array[child_ind];
            array[child_ind] = array[other_index];
            array[other_index] = temp;

            array[child_ind].setPosInQueue(child_ind);
            array[other_index].setPosInQueue(other_index);
        }
    }

    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
        if (num_patients == array.length) {
            return -1;
        }
        table.put(p);

        p.setPosInQueue(num_patients);
        array[num_patients] = p;

        //array[num_patients].setPosInQueue(num_patients);
        int size = num_patients;
        num_patients++;

        int child_index = size;
        int parent_index = (size - 1)/2;
        // swim(child_index, parent_index, 0);
        while (array[child_index] != null && array[parent_index] != null
                && child_index > 0 && array[child_index].compareTo(array[parent_index]) > 0) {
            swap(child_index, parent_index);

            child_index = parent_index;
            parent_index = (child_index - 1) / 2;
        }
        table.put(array[child_index]);
        return child_index;

    }

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
        if (isEmpty()) return null;
        array[0].setPosInQueue(-1);
        Patient del = array[0];
        table.remove(array[0].name());

        array[0] = array[num_patients - 1];
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

    /*TO BE COMPLETED IN PART 2*/

    //remove and return the Patient with
    //name s from the queue
    //return null if the Patient isn't in the queue
    public Patient removee(String s) {
        Patient p = null;
        if (table.get(s) == null) return p;

        int index = table.get(s).posInQueue();
        p = table.get(s);
        p.setPosInQueue(-1);
        table.remove(s);
        return null;
    }

    public Patient remove(String s) {
        if (isEmpty()) { //added
            return null;
        }
        Patient p = table.get(s);
        if (p == null) return p;

        int index = p.posInQueue();
        if (size() == 1) { //added
            array[0] = null;
            num_patients--;
            p.setPosInQueue(-1);
            return table.remove(s);
        }
        else {
            if (array[num_patients - 1] == null) {
                array[0] = null;
                p.setPosInQueue(-1);
                return null;
            }
            swap(index, num_patients - 1);
        }
        array[num_patients - 1] = null;
        num_patients--;

        //table.remove(array[index].name());

        if (array[index].posInQueue() != 0 && array[index].compareTo(array[(index - 1)/2]) > 0) {
            swim(index, (index - 1)/2, 0);
        }
        else {
            sink(array[index]);
        }

        p.setPosInQueue(-1);

        return table.remove(s);
    }

    //update the emergency level of the Patient
    //with name s to urgency
    public void update(String s, int urgency) {
        Patient p = (Patient)table.get(s);

        if (p == null) {
            return;
        }

        int original = p.urgency();
        p.setUrgency(urgency);
        table.remove(s);

        int size = num_patients;
        int child_index = p.posInQueue();
        array[child_index].setUrgency(urgency);
        int parent_index = (int)Math.floor((p.posInQueue() - 1)/2);

        if (urgency - original > 0) {
            while (array[child_index].compareTo(array[parent_index]) > 0) {
                Patient temp = array[child_index];
                array[child_index] = array[parent_index];
                array[parent_index] = temp;

                array[child_index].setPosInQueue(child_index);
                array[parent_index].setPosInQueue(parent_index);

                child_index = (int)Math.floor((child_index - 1)/2);
                parent_index = (int)Math.floor((child_index - 1)/2);
            }

        } else {
            int grandchild_index = child_index*2 + 1;
            while (grandchild_index < num_patients && array[child_index].compareTo(array[grandchild_index]) < 0) {
                Patient temp = array[child_index];
                array[child_index] = array[grandchild_index];
                array[grandchild_index] = temp;

                array[child_index].setPosInQueue(child_index);
                array[grandchild_index].setPosInQueue(grandchild_index);

                child_index = child_index*2 + 1;
                grandchild_index = child_index*2 + 1;
            }
        }
        table.put(p);
    }

    public boolean maxHeap() {
        int left, right;

        for (int i = 0; i < array.length; i++) {
            left = (2*i)+1;

            if (left >= num_patients) {
                break;
            }

            right = (2*i)+2;

            if (array[left].compareTo(array[i]) > 0) {
                return false;
            }

            if (right < num_patients && array[right].compareTo(array[i]) > 0) {
                return false;
            }
        }
        return true;
    }

}
//UPDATES 25 MISMATCH CODE