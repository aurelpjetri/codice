package visitor;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import builder.XMLParser;
import graph.Graph;

public class NetLogoBehaviorVisitorTest {

	private Graph graph;
	private XMLParser parser;
	private Visitor visitor;
	
	@Before
	public void setUp() throws Exception {
		parser = new XMLParser();		
	}

	@Test
	public void visitGraphTest() throws IOException {
		BufferedReader reader = null;
		try{
			graph = parser.parseDocumentForGraph("test/visitor/testExamples/graphVisitorTest.xml");
			visitor = new NetLogoBehaviorVisitor("data/inputs/inputBehaviors.txt", "test/visitor/testExamples/outputBehaviorTest.nlogo");
			graph.accept(visitor);
	
			reader = new BufferedReader(new FileReader("test/visitor/testExamples/outputBehaviorTest.nlogo"));

				
			String m = reader.readLine();
			
			while(m != null && !(m.equals("  set behaviors-map table:make"))){
				m = reader.readLine();
			}
			
			assertEquals("  table:put behaviors-map 0 get-interest-beacons map [ list (world-offset + item 0 ?) (world-offset + item 1 ?) ] [ [(20) (10)] ]", reader.readLine());

			while(m != null && !(m.equals("to populate-initial-state"))){
				m = reader.readLine();
			}
			
			assertEquals("  ;; Populate the initial state of movers around the world", reader.readLine());
			assertEquals("  ;; example: [x y behavior_id n_movers]", reader.readLine());
			assertEquals("  set initial-state lput [10 10 0 100] initial-state", reader.readLine());
			assertEquals("  set initial-state lput [20 10 0 100] initial-state", reader.readLine());
			assertEquals("", reader.readLine());
			assertEquals("  ;;===end", reader.readLine());
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

