package visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import builder.XMLParser;
import graph.Graph;

import static org.junit.Assert.assertEquals;;

public class NetLogoGraphVisitorTest {

	private Graph graph;
	private XMLParser parser;
	private Visitor visitor;

	@Before
	public void setUp() throws Exception {
		parser = new XMLParser();


	}

	@Test
	public void visitGraphTest() throws IOException{
		BufferedReader reader = null;
		try{
			graph = parser.parseDocumentForGraph("test/visitor/testExamples/graphVisitorTest.xml");
			visitor = new NetLogoGraphVisitor("data/inputs/inputGraph.txt", "test/visitor/testExamples/outputGraphTest.nlogo");
			graph.accept(visitor);
	
			reader = new BufferedReader(new FileReader("test/visitor/testExamples/outputGraphTest.nlogo"));

				
			String m = reader.readLine();
			
			while(m != null && !(m.equals("to generate-beacons"))){
				m = reader.readLine();
			}
			
			assertEquals("  ask patch (world-offset + 10) (world-offset + 10) [sprout-beacons 1 [", reader.readLine());
			assertEquals("    make-beacon-normal", reader.readLine());
			assertEquals("    set intersection-width 5", reader.readLine());
			assertEquals("    set intersection-height 5", reader.readLine());
			assertEquals("    set intersection-radius 3", reader.readLine());
			assertEquals("  ]]", reader.readLine());
			
			assertEquals("  ask patch (world-offset + 20) (world-offset + 10) [sprout-beacons 1 [", reader.readLine());
			assertEquals("    make-beacon-normal", reader.readLine());
			assertEquals("    set intersection-width 5", reader.readLine());
			assertEquals("    set intersection-height 5", reader.readLine());
			assertEquals("    set intersection-radius 3", reader.readLine());
			assertEquals("  ]]", reader.readLine());
			assertEquals("end", reader.readLine());

			while(m != null && !(m.equals("to connect-beacons"))){
				m = reader.readLine();
			}
			
			assertEquals("  ask beacons-on patch (world-offset + 10) (world-offset + 10) [", reader.readLine());
			assertEquals("    create-streets-with beacons-on patch (world-offset + 20) (world-offset + 10) [", reader.readLine());
			assertEquals("      set weight 3", reader.readLine());
			assertEquals("      set street-width 3]]", reader.readLine());
			assertEquals("end", reader.readLine());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			if (reader != null) {
				reader.close();
			}
		}

	}

}
