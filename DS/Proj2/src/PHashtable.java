import java.util.ArrayList;
public class PHashtable {
    private ArrayList[] table;
    private int num_patients = 0;
    
    //set the table size to the first 
    //prime number p >= capacity
    public PHashtable (int capacity) {
        table = new ArrayList[getNextPrime(capacity)];
    }

    //return the Patient with the given name 
    //or null if the Patient is not in the table
    public Patient get(String name) {
        Patient a = null;
        int index = name.hashCode();
        index = (index % table.length);

        if (index < 0) {
            index = index + table.length;
        }

        if (table[index] != null) {
            for (int i = 0; i < table[index].size(); i++) {
                a = (Patient)table[index].get(i);
                if (a.name().equals(name)) return a;
            }
        }
        return a;
    }

    //put Patient p into the table
    public void put(Patient p) {
        int index = p.name().hashCode() % table.length;

        if (index < 0) {
            index = index + table.length;
        }

        if (table[index] != null && table[index].contains(p)) {
            return;
        }

        if(table[index] == null) {
            table[index] = new ArrayList();
        }

        table[index].add(p);
        num_patients++;
    }

    //remove and return the Patient with the given name
    //from the table
    //return null if Patient doesn't exist
    public Patient remove(String name) {
        Patient a = null;
        int index = name.hashCode() % table.length;

        if (index < 0) {
            index = index + table.length;
        }

        if (table[index] != null) {
            for (int i = 0; i < table[index].size(); i++) {
                a = (Patient)table[index].get(i);
                if (a.name().equals(name)) {
                    num_patients--;
                    table[index].remove(i);
                    return a;
                }
            }
        }
        return a;
    }	    

    //return the number of Patients in the table
    public int size() {
        return num_patients;
    }

    //returns the underlying structure for testing
    public ArrayList<Patient>[] getArray() {
	return table;
    }
    
    //get the next prime number p >= num
    private int getNextPrime(int num) {
    if (num == 2 || num == 3)
        return num;
    int rem = num % 6;
    switch (rem) {
        case 0:
        case 4:
            num++;
            break;
        case 2:
            num += 3;
            break;
        case 3:
            num += 2;
            break;
    }
    while (!isPrime(num)) {
        if (num % 6 == 5) {
            num += 2;
        } else {
            num += 4;
           }
        }
        return num;
    }


    //determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if(num % 2 == 0){
            return false;
        }
        
	int x = 3;
	for(int i = x; i < num; i+=2){
	    if(num % i == 0){
		    return false;
        }
    }
	return true;
    }
}
      

