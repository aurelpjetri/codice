package visitor;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import builder.XMLParser;
import grafo.Graph;

public class NetLogoBehaviorVisitorTest {

	private Graph graph;
	private XMLParser parser;
	private Visitor visitor;
	
	@Before
	public void setUp() throws Exception {
		parser = new XMLParser();
		graph = parser.parseDocumentForGraph("data/example.xml");
		
	}

	@Test
	public void visitGraphTest() {
		try{
			visitor = new NetLogoBehaviorVisitor();
			graph.accept(visitor);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
