package builder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import grafo.Graph;

public class XMLParserTest2 {

	private XMLParser parser;
	private Graph graph;
	
	
	@Before
	public void setUp() throws JDOMException, IOException  {
		parser = new XMLParser();
		graph = parser.parseDocumentForGraph("data/standardExample2.xml");
		
	}
	
	
	
	@Test
	public void testBehaviors(){
		assertEquals(1, graph.getBehaviors().size());
		assertEquals(1, graph.getBehaviorFromId(0).getCoreInterestPoints().size());
		assertEquals(graph.getNodeFromCoordinates(0, 10), graph.getBehaviorFromId(0).getCoreInterestPoints().get(0) );
	}

}
