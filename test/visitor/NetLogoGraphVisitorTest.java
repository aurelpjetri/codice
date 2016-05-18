package visitor;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import builder.XMLParser;
import grafo.Graph;

public class NetLogoGraphVisitorTest {
	
	private Graph graph;
	private XMLParser parser;
	private Visitor visitor;
	
	@Before
	public void setUp() throws Exception {
		parser = new XMLParser();
		
		
	}

	@Test
	public void visitGraphTest() {
		try{
			graph = parser.parseDocumentForGraph("data/example3.xml");
			visitor = new NetLogoGraphVisitor();
			graph.accept(visitor);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(RuntimeException e){
			e.printStackTrace();
		}
	}

}
