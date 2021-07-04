import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part1Test {
    public static ArrayList<Patient> patients;
    public static int index;
    public static int time = 0;
    public static int testNum = 0;
    
    public static void main(String[] args) {
	patients = new ArrayList<Patient>();
	//getPatients(args[0]);
		getPatients("/Users/anishasbhat/Downloads/CS251/Proj2/src/patient_file_1.txt");
	//"patient_file_1.txt"
	ArrayList<Patient> list = new ArrayList<Patient>();
	double score = 0;

	//Test 1
        testNum++;
	score += test1_2(32, 25, list);


	//Test 2
        testNum++;
        //System.out.println(list.size());
        //list.clear();
	score += test1_2(7, patients.size() - 1, list);

	//Test 3
	testNum++;
	score += test3(30, 17);

	score = Math.max(0.0, score);

	System.out.println("\nTotal score for Part 1: " + score);
    }	

    private static double test1_2(int i, int cap, ArrayList<Patient> list) {
	System.out.println("\n*****Begin Test " + testNum + "*****");
	index = i;
	PatientQueue pq = new PatientQueue(cap);
	double score = 0;
	//check operations on empty PQ
	System.out.println("\nPQ is empty...");
	if(pq.size() == 0) {
	    printMsg(true, "size");
	    score += 0.5;//0.5
	} else 
	    printMsg(false, "size");
	if(pq.isEmpty()) {
	    score += 0.5;//1.0
	    printMsg(true, "isEmpty");
	} else
	    printMsg(false, "isEmpty");
	if(pq.getMax() == null) {
	    score += 0.5;//1.5
	    printMsg(true, "getMax");
	} else
	    printMsg(false, "getMax");
	if(pq.delMax() == null) {
	    score += 0.5;//2.0
	    printMsg(true, "delMax");
	} else 
	    printMsg(false, "delMax");

	//insert some Patients
	System.out.println("\nAdding some patients...");
	insertPatients(list, pq, (int)(cap/2));
	/*
	for (int d = 0; d < list.size(); d++) {
		System.out.println(list.get(d).name() + ":" + list.get(d).urgency() + " 	" + pq.getArray()[d].name() + ": " +  pq.getArray()[d].urgency());
	}
	 */

	/*
		System.out.println(list.get(0).name() + ":" + list.get(0).urgency() + " 	" + pq.getArray()[0].name() + ": " +  pq.getArray()[0].urgency());
		System.out.println(list.get(1).name() + ":" + list.get(1).urgency() + " 	" + pq.getArray()[1].name() + ": " +  pq.getArray()[1].urgency());
		System.out.println(list.get(2).name() + ":" + list.get(2).urgency() + " 	" + pq.getArray()[2].name() + ": " +  pq.getArray()[2].urgency());
		System.out.println(list.get(3).name() + ":" + list.get(3).urgency() + " 	" + pq.getArray()[3].name() + ": " +  pq.getArray()[3].urgency());
		System.out.println(list.get(4).name() + ":" + list.get(4).urgency() + " 	" + pq.getArray()[4].name() + ": " +  pq.getArray()[4].urgency());
		System.out.println(list.get(5).name() + ":" + list.get(5).urgency() + " 	" + pq.getArray()[5].name() + ": " +  pq.getArray()[5].urgency());
		System.out.println(list.get(6).name() + ":" + list.get(6).urgency() + " 	" + pq.getArray()[6].name() + ": " +  pq.getArray()[6].urgency());
		System.out.println(list.get(7).name() + ":" + list.get(7).urgency() + " 	" + pq.getArray()[7].name() + ": " +  pq.getArray()[7].urgency());
		System.out.println(list.get(8).name() + ":" + list.get(8).urgency() + " 	" + pq.getArray()[8].name() + ": " +  pq.getArray()[8].urgency());
	*/
	//System.out.println("after: " + list.size() + " " + pq.size());

	if(pq.size() == list.size()) {
	    score += 0.5;//2.5
	    printMsg(true, "size");
	} else
	    printMsg(false, "size");
		//System.out.println("edit: " + list.size() + ", " + pq.size());
	if(!pq.isEmpty()) {
	    score += 0.5;//3.0
	    printMsg(true, "isEmpty");
	} else
	    printMsg(false, "isEmpty");
	if(pq.getMax() == list.get(0)) {
	    score += 1.0;//4.0
	    printMsg(true, "getMax");
	} else {
	    printMsg(false, "getMax");
	}
		//System.out.println(list.size() + " " + pq.size());
	if(pq.delMax() == list.remove(0) && pq.size() == list.size()) {
	    score += 1.0;//5.0
	    printMsg(true, "delMax");
	} else
	    printMsg(false, "delMax");
	
	//fill up the queue
	System.out.println("\nFilling up the PQ...");
	insertPatients(list, pq, cap-list.size());

	if(pq.size() == list.size()) {
	    score += 0.5;//5.5
	    printMsg(true, "size");
	} else
	    printMsg(false, "size");
	if(pq.insert(patients.get(index)) == -1 && pq.size() == list.size()) {
	    score += 1.0;//6.5
	    printMsg(true, "insert");
	} else
	    printMsg(false, "insert");
	
	//remove all remaining patients
	boolean pass = true;
/*
		for (int d = 0; d < list.size(); d++) {
			System.out.println(list.get(d).name() + ":" + list.get(d).urgency() + " 	" + pq.getArray()[d].name() + ": " +  pq.getArray()[d].urgency());
		}
*/
		System.out.println(list.size() + " " + pq.size());
	while(!list.isEmpty()) {
	    Patient lp = list.remove(0);
		//System.out.println("removing 1 element");
	    Patient qp = pq.delMax();
		//System.out.println(list.size() + "xx " + pq.size());
		//System.out.println(list.get(1).name() + "second " + pq.getArray()[1].name());
		//System.out.println(list.get(2).name() + "third " + pq.getArray()[2].name());
		//System.out.println(list.size() + "xx " + pq.size());

		//System.out.println(lp.name() + "VS " + qp.name());
	    if(lp != qp) {
		pass = false;
		/*
			for (int d = 0; d < list.size(); d++) {
				System.out.println(list.get(d).name() + ":" + list.get(d).urgency() + " 	" + pq.getArray()[d].name() + ": " +  pq.getArray()[d].urgency());
			}
		 */
			System.out.println(list.size() + " " + pq.size());
		System.out.println("Max patients not equal");
		printExpAct(lp.toString(), qp.toString());
			//System.out.println(list.size() + "xx " + pq.size());
		break;
	    }
	}
	if(!pq.isEmpty())
	    pass = false;
	if(pass) {
	    score += 2.5;//9.0
	    printMsg(true, "delMax until empty");
	}
	else
	    printMsg(false, "delMax until empty");
	System.out.println("\nTotal score for Test " + testNum + ": " + score);
	return score;
    }

    private static double test3(int size, int start_index) {
	System.out.println("\n*****Begin Test 3*****");
	String oFile = "/Users/anishasbhat/Downloads/CS251/Proj2/src/p1_test3_output_" + size + "_" + start_index + ".txt";
	PatientQueue pq = new PatientQueue(size);
	index = start_index;
	for(int i = 0; i < size; i++) {
	    pq.insert(patients.get(index));
	    incIndex();
	}
	String exp = getExp(oFile);
        String act = getAct(pq);
	if(exp.equals(act)) {
	    System.out.println("Test 3 passed!");
	    return 7.0;
	} else {
	    System.out.println("Test 3 failed: underlying array not correct");
	    printExpAct(exp, act);
	}
	return -18.0;
    }

    private static String getExp(String fn) {
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    return line;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    private static String getAct(PatientQueue pq) {
	Patient[] array = pq.getArray();
	String ret = "";
	for(int i = 0; i < array.length; i++) 
	    if(array[i] != null)
		ret += "|" + array[i].toStringForTesting() + "|";
	return ret;
    }
	    
    private static void printMsg(boolean passed, String method) {
	if(passed) 
	    System.out.println(method + " passed");
	else
	    System.out.println(method + " failed");
    }
    
    private static void printExpAct(String exp, String act) {
	System.out.println("Expected: " + exp);
	System.out.println("Actual: " + act);
    }

    private static void printArray(PatientQueue pq) {
	Patient[] array = pq.getArray();
	for(int i = 0; i < array.length; i++)
	    System.out.print(array[i] + " | ");
    }
	    
    private static void insertPatients(ArrayList<Patient> list, PatientQueue pq, int num) {
	int count = 0;
	while(count < num) {
	    Patient p = patients.get(index);
	    pq.insert(p);
	    insertToList(list, p); 
	    incIndex();
	    count++;
	}
    }

    private static void insertToList(ArrayList<Patient> list, Patient p) {
	for(int i = 0; i < list.size(); i++) {
	    if(p.compareTo(list.get(i)) > 0) {
		list.add(i, p);
		return;
	    }
	}
	list.add(p);
    }

    private static void getPatients(String fn) {
	BufferedReader reader;
	try {
	    reader = new BufferedReader(new FileReader(fn));
	    String line = reader.readLine();
	    while(line != null) {
		String[] split = line.split(",");
		if(split.length >= 2) {
		    Patient p = new Patient(split[0], Integer.parseInt(split[1]), time++);
		    patients.add(p);
		}
		line = reader.readLine();
	    }
	}catch (Exception e) {
	    e.printStackTrace();
	}  
    }

    private static void incIndex() {
	 index = (index + 1) % patients.size();
    }

    private static void printList(ArrayList<Patient> list) {
	System.out.println("\nLIST: ");
	for(Patient p : list)
	    System.out.print(p.toString() + " | ");
    }

    private static void printQueue(PatientQueue pq) {
	System.out.println("\nQUEUE: ");
	Patient[] array = pq.getArray();
	for(int i = 1; i < pq.size(); i++)
	    System.out.print(array[i].toString() + " | ");
    }
}

    
	