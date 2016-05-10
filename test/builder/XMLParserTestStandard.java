package builder;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Before;
import org.junit.Test;

import grafo.Graph;

public class XMLParserTestStandard {

	private ConcreteBuilder builder;
	private Graph graph;
	private Element node;
	private Document jdomDocument;
	private String file;
	
	@Before
	public void setUp() throws Exception {
		builder = new ConcreteBuilder();
		file = "/home/aurel/workspace/prototipe/src/standardExample.xml";
		
		builder.buildGraph();
		
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		jdomDocument = new Document();
		try {
			jdomDocument = jdomBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		node = jdomDocument.getRootElement();
		
	}

	@Test
	public void testParseTree() {
		Element graph = node.getChild("graph");
		
		List<Element> nodes = graph.getChildren("node");
		
		List<Element> edges = graph.getChildren("edge");
				
		List<Element> attributes = node.getChildren("key");
		
		String defaultNodeType = null;
		
		for(Element at : attributes){
			if(at.getAttributeValue("id").contains("nt")){
				defaultNodeType = at.getChild("default").getText();
			}
		}
		
		String defaultEdgeType = graph.getAttributeValue("edgedefault");
		
		for(Element n : nodes){
			String type = defaultNodeType;
			System.out.println(type);
			int x = -1;
			int y = -1;
			int w = -1;
			int h = -1;
			
			try{
				for(Element attr : n.getChildren("data")){
					if(attr.getAttributeValue("key").contains("nt")){
						System.out.println(attr.getText());
						type = attr.getText();
					}
					if(attr.getAttributeValue("key").contains("nx")){
						x = Integer.parseInt(attr.getText());
					}
					if(attr.getAttributeValue("key").contains("ny")){
						y = Integer.parseInt(attr.getText());
					}
					if(attr.getAttributeValue("key").contains("nw")){
						w = Integer.parseInt(attr.getText());
					}
					if(attr.getAttributeValue("key").contains("nh")){
						h = Integer.parseInt(attr.getText());
					}
				}
				
				System.out.println(type);
				
				if(x<0 || y<0 || w<0 || h<0){
					throw new RuntimeException("was not able to identify some attributes");
				}
				
				
				if(type.equalsIgnoreCase("N")){
					builder.buildRegularNode(x, y, w, h);
					System.out.println("costruito  "+type);
				}
				if(type.equalsIgnoreCase("EX")){
					builder.buildExitPoint(x, y, w, h);
					System.out.println("costruito  "+type);
				}
				if(type.equalsIgnoreCase("EN")){
					builder.buildEntryPoint(x, y, w, h);
					System.out.println("costruito  "+type);
				}
					
				else if(!type.equalsIgnoreCase("EN") && 
						!type.equalsIgnoreCase("EX")&&
						!type.equalsIgnoreCase("N")){
					throw new RuntimeException("not able to identify node type"+type);
				}
			}
			catch(RuntimeException e){
				e.printStackTrace();
			}
			
			
		}
			
		
	}

}
