package builder;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import grafo.Graph;

public class ConcreteBuilderTest {
	
	private ConcreteBuilder builder;

	@Before
	public void setUp() throws Exception {
		
		builder = new ConcreteBuilder();
		builder.buildGraph();
//		builder.buildRegularNode(0, 0, 10, 10, 10, 5);
//		builder.buildRegularNode(0, 10, 10, 10, 10, 5);
//		builder.buildRegularNode(10, 10, 10, 10, 10, 5);
//		builder.buildRegularNode(20, 0, 10, 10, 10, 5);
//		
		builder.buildUndirectedEdge(0, 0, 0, 10, 5, 5);
		builder.buildUndirectedEdge(0, 10, 10, 10, 10, 10);
		builder.buildUndirectedEdge(10, 10, 20, 0, 10, 10);
		builder.buildUndirectedEdge(0, 0, 20, 0, 10, 10);
		
		Graph grafo = builder.getProduct();
		System.out.println(grafo.getNodes().get(0).getId());
	}

	@Test
	public void testBuildUndirectedEdgeError() {
		try{
			builder.buildUndirectedEdge(0, 0, 0, 10, 10, 10);
		}
		catch(RuntimeException e){
			String message = " the edge between "+0+" and "+1+" already exists";
			assertEquals(message, e.getMessage());
		}
	}

	@Test
	public void testBuildUndirectedEdgeNodeError(){
		try{
			builder.buildUndirectedEdge(0, 0, 20, 20, 9, 9);
			
		}
		catch(RuntimeException e){
			System.out.println(e.getMessage());
			assertNotNull(e);
		}
	}

}
