package builder;

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
		
		
	}
	
	@Test
	public void testParser(){
		try{
			graph = parser.parseDocumentForGraph("/home/aurel/workspace/prototipe/src/errorTestExample.xml");
		}
		catch(RuntimeException e){
			System.out.println(e.getMessage());
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
