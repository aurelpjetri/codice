package builder;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Before;
import org.junit.Test;

import graph.Graph;


public class ErrorXMLParserTest {
	private XMLParser parser;
	private NetLogoGraphBuilder builder;
	
	
	@Before
	public void setUp() {
		parser = new XMLParser();
		builder = new NetLogoGraphBuilder();

	}
	
	@Test
	public void testExitRatesError(){
		

		String file = "test/builder/testExamples/exitRatiosError.xml";
		

		
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = new Document();
		try {
			jdomDocument = jdomBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Element root = jdomDocument.getRootElement();
		
		builder.buildGraph();
		
		try{
			parser.parseTree(root, builder);
		}
		catch(RuntimeException e){
			assertEquals(true, e.getMessage().startsWith("percentages missing on"));
		}
		
	}
	
	@Test
	public void testNodeTypeError(){


		String file = "test/builder/testExamples/nodeTypeError.xml";
		
		Graph graph = new Graph();

		
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = new Document();
		try {
			jdomDocument = jdomBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Element root = jdomDocument.getRootElement();
		
		builder.buildGraph();

		
		try{
			parser.parseTree(root, builder);
		}
		catch(RuntimeException e){
			assertEquals(true, e.getMessage().startsWith("not able to identify node type"));
		}
	}
	
	@Test
	public void testBehaviorTypeError(){


		String file = "test/builder/testExamples/behaviorTypeError.xml";
		
		Graph graph = new Graph();

		
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = new Document();
		try {
			jdomDocument = jdomBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Element root = jdomDocument.getRootElement();
		
		builder.buildGraph();

		
		try{
			parser.parseTree(root, builder);
		}
		catch(RuntimeException e){
			assertEquals(true, e.getMessage().startsWith("unable to identify type on behavior"));
		}
	}
	
	@Test
	public void testInterestPointsOnEvacuateError(){

		String file = "test/builder/testExamples/interestPointsError.xml";
				
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = new Document();
		try {
			jdomDocument = jdomBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Element root = jdomDocument.getRootElement();
		
		builder.buildGraph();

		
		try{
			parser.parseTree(root, builder);
		}
		catch(RuntimeException e){
			assertEquals(true, e.getMessage().startsWith("evacuate behaviors don't have optional interest points"));
		}
	}
	
	public void testRateSumValueError(){

		String file = "test/builder/testExamples/rateSumError.xml";
				
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = new Document();
		try {
			jdomDocument = jdomBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Element root = jdomDocument.getRootElement();
		
		builder.buildGraph();
		
		Element graph = parser.getElementWithAttributeValue(root.getChildren(), "id", "Graph");
		
		List<Element> nodes = graph.getChildren("node");
		
		List<Element> attributes = root.getChildren("key");
		
		Element system = parser.getElementWithAttributeValue(root.getChildren(), "id", "System");

		
		String defaultNodeType = null;
		
		for(Element at : attributes){
			if(at.getAttributeValue("id").contains("nType")){
				defaultNodeType = at.getChild("default").getText();
			}
		}
		
		for(Element n : nodes){
			
			String nType = parser.getTextAttributeOfElement(n, "nType");
			
			if(nType == null){
				nType = defaultNodeType;
			}
			
			
			
			Element nodeStatusElement = parser.getElementWithAttributeValue(system.getChildren(), "id", n.getAttributeValue("id")).getChild("graph");
			
			int x = Integer.parseInt(parser.getTextAttributeOfElement(n, "nx"));
			
			int y = Integer.parseInt(parser.getTextAttributeOfElement(n, "ny"));
			
			int nWidth = Integer.parseInt(parser.getTextAttributeOfElement(n, "nWidth"));
			
			int nHeight = Integer.parseInt(parser.getTextAttributeOfElement(n, "nHeight"));
			
			int radius = Integer.parseInt(parser.getTextAttributeOfElement(n, "nRadius"));
			
			HashMap<Integer,Integer> state = new HashMap<Integer,Integer>();
			
			HashMap<Integer,Float> rate = new HashMap<Integer,Float>();
			 
			
			//DOVREI FARE UN CONTROLLO SUL ID DEL BEHAVIOR 
			//PER VEDERE SE CORRISPONDE A UN BEHAVIOR ESISTENTEj
			if(!nType.equalsIgnoreCase("normal")){
				for(Element behaviorState : nodeStatusElement.getChildren()){
					if(parser.getTextAttributeOfElement(behaviorState, "rate") == null){
						throw new RuntimeException("exit rates missing on node: "+x+" , "+y);
					}
					else{
						int localId = Integer.parseInt(behaviorState.getAttributeValue("id"));
						int localQuantity = Integer.parseInt(parser.getTextAttributeOfElement(behaviorState, "moverQuantity"));
						float localRate = Float.parseFloat(parser.getTextAttributeOfElement(behaviorState, "rate"));
						state.put(localId, localQuantity );
						rate.put(localId, localRate);
					}
				}
			}
			else{
				for(Element behaviorState : nodeStatusElement.getChildren()){
					int localId = Integer.parseInt(behaviorState.getAttributeValue("id"));
					int localQuantity = Integer.parseInt(parser.getTextAttributeOfElement(behaviorState, "moverQuantity"));
					state.put(localId, localQuantity );
				}
			}
			
		
			
//
//			if(nType.equalsIgnoreCase("normal")){
//				builder.buildRegularNode(x, y, nWidth, nHeight, radius, state);
//			}
//			if(nType.equalsIgnoreCase("exit")){
//				builder.buildExitPoint(x, y, nWidth, nHeight, radius, state, rate);
//			}
//			if(nType.equalsIgnoreCase("entry")){
//				builder.buildEntryPoint(x, y, nWidth, nHeight, radius, state, rate);
//			}
//				
//			else if(!nType.equalsIgnoreCase("normal") && 
//					!nType.equalsIgnoreCase("exit")&&
//					!nType.equalsIgnoreCase("normal")){
//				throw new RuntimeException("not able to identify node type "+nType);
//			}
//			
//			
		}
//
//		
//		try{
//			parser.parseTree(root, builder);
//			System.out.println(graphG.getNodeFromCoordinates(0, 0).getGenerationRate().get(0));
//		}
//		catch(RuntimeException e){
//			assertEquals(true, e.getMessage().startsWith("rates sum must be equal to 1"));
//		}
	}

}
