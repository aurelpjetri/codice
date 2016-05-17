package builder;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import grafo.Graph;

public class ErrorXMLParserTest {
	private XMLParser parser;

	
	
	@Before
	public void setUp() {
		parser = new XMLParser();
		
		
		
	}
	
	@Ignore
	@Test
	public void testParser(){
		try{
			Graph graph = parser.parseDocumentForGraph("data/errorTestExample.xml");
			graph.toString();
		}
		catch (RuntimeException e){
			e.printStackTrace();
		}
	}
	

}
