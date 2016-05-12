package builder;


import org.junit.Before;
import org.junit.Test;

import grafo.Graph;

public class ErrorXMLParserTest {
	private XMLParser parser;
	private Graph graph;
	
	
	@Before
	public void setUp() {
		parser = new XMLParser();
		graph = new Graph();
		
		
	}
	
	@Test
	public void testParser(){
		graph = parser.parseDocumentForGraph("/home/aurel/workspace/prototipe/src/errorTestExample.xml");
	}
	

}
