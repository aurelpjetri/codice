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
		graph = parser.parseDocumentForGraph("src/standardExample.xml");
		
		visitor = new ProvaGraphVisitor();
		
	}

	@Test
	public void visitGraphTest() {
		try{
			graph.accept(visitor);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
