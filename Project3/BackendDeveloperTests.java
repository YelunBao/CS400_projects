import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {
    
    FlightPlanBackend backend;

    /**
     * Test for isValidCost() and isValidDistance() in the backend.
     */
    @Test
    public void backendIndividualTest1() {

        this.backend = new FlightPlanBackend(new BDFlightLoader());

        // case 1: valid cost
        Assertions.assertTrue(backend.isValidCost("$150"));

        // case 2: invalid cost
        Assertions.assertFalse(backend.isValidCost("150"));

        // case 3: valid distance
        Assertions.assertTrue(backend.isValidDistance("150"));

        // case 4: invalid distance
        Assertions.assertFalse(backend.isValidDistance(""));

    }

    /**
     * Test for listAllAirports() in the backend.
     */
    @Test
    public void backendIndividualTest2() {

        this.backend = new FlightPlanBackend(new BDFlightLoader());

        ArrayList<String> airports = new ArrayList<String>();

        airports.add("LA");
        airports.add("SFO");
        airports.add("ORD");
        airports.add("CHI");
        airports.add("OWC");

        Assertions.assertTrue(backend.listAllAirports().equals(airports));

    }

    /**
     * Test for findMinFlightCost() and findMinFlightDistance() in the backend.
     */
    @Test
    public void backendIndividualTest3() {

        this.backend = new FlightPlanBackend(new BDFlightLoader());

        ArrayList<String> shortestPath = new ArrayList<>();

        shortestPath.add("LA");
        shortestPath.add("SFO");
        shortestPath.add("ORD");

        Assertions.assertTrue(shortestPath.equals(backend.findMinFlightCost("LA", "ORD")));
        Assertions.assertTrue(shortestPath.equals(backend.findMinFlightDistance("LA", "ORD")));

        // When no path can be found
        Assertions.assertTrue(backend.findMinFlightCost("ORD", "LA") == null);

        Assertions.assertTrue(backend.findMinFlightCost("MAD", "NYC") == null);

    }

    /**
     * Test for the addAirport() and removeAirport() methods in the backend.
     */
    @Test
    public void backendIndividualTest4() {

        this.backend = new FlightPlanBackend(new BDFlightLoader());

        // Initially, there are 5 airports.
        Assertions.assertEquals(5, backend.listAllAirports().size());
        Assertions.assertEquals(5, backend.costMap.getVertexCount());
        Assertions.assertEquals(5, backend.distanceMap.getVertexCount());

        // Add one airport
        backend.addAirport("CAM");
        Assertions.assertEquals(6, backend.listAllAirports().size());
        Assertions.assertEquals(6, backend.costMap.getVertexCount());
        Assertions.assertEquals(6, backend.distanceMap.getVertexCount());

        // Add another airport with a cost edge
        backend.addAirport("CHT", "ORD", "$180");
        Assertions.assertEquals(7, backend.listAllAirports().size());
        Assertions.assertEquals(7, backend.costMap.getVertexCount());
        Assertions.assertEquals(7, backend.distanceMap.getVertexCount());

        // Add another airport with a distane edge
        backend.addAirport("ABR", "LA", "180");
        Assertions.assertEquals(8, backend.listAllAirports().size());
        Assertions.assertEquals(8, backend.costMap.getVertexCount());
        Assertions.assertEquals(8, backend.distanceMap.getVertexCount());

        // Add an existent airport, should return false
        if (backend.addAirport("LA"))
            Assertions.fail();

        // Remove one airport
        backend.removeAirport("SFO");
        Assertions.assertEquals(7, backend.listAllAirports().size());
        Assertions.assertEquals(7, backend.costMap.getVertexCount());
        Assertions.assertEquals(7, backend.distanceMap.getVertexCount());

        // Remove a non-existent airport, should return false
         if (backend.removeAirport("MAD"))
            Assertions.fail();

    }

    /**
     * Test for addEdge() and removeEdge() in the backend.
     */
    @Test
    public void backendIndividualTest5() {

        this.backend = new FlightPlanBackend(new BDFlightLoader());

        // There should be initially 5 edges.
        Assertions.assertEquals(5, backend.costMap.getEdgeCount());
        Assertions.assertEquals(5, backend.distanceMap.getEdgeCount());

        // Add an existing edge, should return false
        if (backend.addEdge("LA", "SFO", "$150"))
            Assertions.fail();

        // Add a cost edge
        Assertions.assertTrue(backend.addEdge("SFO", "LA", "$180"));
        Assertions.assertEquals(6, backend.costMap.getEdgeCount());
        Assertions.assertEquals(5, backend.distanceMap.getEdgeCount());

        // Add a distance edge
        Assertions.assertTrue(backend.addEdge("ORD", "SFO", "100"));
        Assertions.assertEquals(6, backend.costMap.getEdgeCount());
        Assertions.assertEquals(6, backend.distanceMap.getEdgeCount());

        // Delete the edges above
        Assertions.assertTrue(backend.removeEdge("SFO", "LA"));
        Assertions.assertTrue(backend.removeEdge("ORD", "SFO"));
        Assertions.assertEquals(5, backend.costMap.getEdgeCount());
        Assertions.assertEquals(5, backend.distanceMap.getEdgeCount());
    }

    /**
	 * Test if backend's addAirport() works with frontend's display all airports method.
	 */
	@Test
	public void backendIntegrationTest1() {
		
		FlightPlanBackend backend = new FlightPlanBackend(new BDFlightLoader());
		Scanner scnr = new Scanner(System.in);
		FlightPlanFrontend frontend = new FlightPlanFrontend(scnr, backend);
		
		backend.addAirport("AIS");
		backend.addAirport("MKE");
		backend.addAirport("DCB");
		
		PrintStream displayedTexts = System.out;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteStream);
		
		System.setOut(printStream);
		
		frontend.displayAirports(backend.listAllAirports());
		
		String output = "LA SFO ORD CHI OWC AIS MKE DCB \n";
		
		System.out.flush();
		System.setOut(displayedTexts);
		
		
		Assertions.assertEquals(output, byteStream.toString());
		
	}

    /**
	 * Test for removeAirport() of the backend along with frontend's displayAirports().
	 */
	@Test
	public void backendIntegrationTest2() {
		
		FlightPlanBackend backend = new FlightPlanBackend(new BDFlightLoader());
		Scanner scnr = new Scanner(System.in);
		FlightPlanFrontend frontend = new FlightPlanFrontend(scnr, backend);
		
		backend.removeAirport("LA");
		backend.removeAirport("SFO");
		backend.removeAirport("ORD");
		
		PrintStream displayedTexts = System.out;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteStream);
		
		System.setOut(printStream);
		
		frontend.displayAirports(backend.listAllAirports());
		
		String output = "CHI OWC \n";
		
		System.out.flush();
		System.setOut(displayedTexts);
		
		System.out.println(byteStream.toString());
		
		Assertions.assertEquals(output, byteStream.toString());
		
	}

    /**
	 * Test for the output if an invalid input has been passed
	 */
	@Test
	public void backendCodeReviewOfFrontendDeveloperTest1() {

		InputOutput textUITester = new InputOutput("7\n Exit\n");

		String output = textUITester.checkOutput();
		
		Assertions.assertEquals(true, output.contains("Please enter a valid choice")); 
		
		Assertions.assertEquals(true, output.contains("Goodbye!"));
	}

    /**
	 * Test for the output of finding shortest distance
	 */
	@Test
	public void backendCodeReviewOfFrontendDeveloperTest2() {
		
		InputOutput textUITester = new InputOutput("4\n   ORD\n    BOS\n   6\n");
		
		String testerOutput = textUITester.checkOutput();
		String expectedPath = "ORD LGA BOS";
		
		Assertions.assertEquals(true, testerOutput.contains(expectedPath));
		Assertions.assertEquals(true, testerOutput.contains("Enter point A:"));
		Assertions.assertEquals(true, testerOutput.contains("Enter point B:"));
		
	}

}
