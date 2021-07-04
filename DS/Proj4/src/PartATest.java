import java.io.BufferedReader;
import java.io.FileReader;

public class PartATest 
{
    public static void main(String[] args) 
    {
	    int testNum = 1;
	    int graphNum = 1;

	    //Test 1
	    int budget = 20;
	    runTest(testNum, graphNum, budget);
	    
        //Test 2
	    budget = 75;
	    testNum++;
	    runTest(testNum, graphNum, budget);
	    
        //Test 3
	    budget = 100;
	    testNum++;
	    graphNum++;
	    runTest(testNum, graphNum, budget);
	    
        //Test 4
	    budget = 900;
	    testNum++;
	    runTest(testNum, graphNum, budget);
    }

    private static void runTest(int testNum, int graphNum, int budget) 
    {
	    System.out.println("*****BEGIN TEST " + testNum + "*****");
	    ///Users/anishasbhat/Downloads/CS251/Proj4/src/test/testFiles/graph2.txt
	    Graph G = new Graph("/Users/anishasbhat/Downloads/CS251/Proj4/src/test/testFiles/graph" + graphNum + ".txt");
	    Graph R = RegNet.run(G, budget);
	    ///Users/anishasbhat/Downloads/CS251/Proj4/src/PartATest.java
		///Users/anishasbhat/Downloads/CS251/Proj4/src/test/testFiles/A_test_1.txt
	    Graph E = new Graph("/Users/anishasbhat/Downloads/CS251/Proj4/src/test/testFiles/A_test_" + testNum + ".txt");
	    boolean passed = R.equals(E);
	    printMsg(testNum, passed, E.toString(), R.toString());
    }

    private static void printMsg(int test, boolean passed, String exp, String act) 
    {
	    if(passed) 
	        System.out.println("Test " + test + " passed!");
	    else 
        {
	        System.out.println("Test " + test + " failed!\n");
	        System.out.println("Expected:\n" + exp + "\n");
	        System.out.println("Actual:\n" + act + "\n");
	    }
    }
}
	