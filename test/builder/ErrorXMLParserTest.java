package builder;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Before;
import org.junit.Test;


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
	
	@Test
	public void testBehaviorsMssing(){
		
		String file = "test/builder/testExamples/behaviorsMissingError.xml";
		
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
			assertEquals(true, e.getMessage().startsWith("behaviors missing on node"));
		}
		
	}
	
	@Test
	public void testIdNotCorresponding(){
		
		String file = "test/builder/testExamples/idNotCorrespondingError.xml";
		
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
			assertEquals(true, e.getMessage().endsWith("not corresponding to any behavior"));
		}
		
	}
	
	@Test
	public void testErrorsCallOrder(){
		
		//voglio testare l'ordine in cui vengono chiamati gli errori
		
		//in questo caso ci sono due errori: id behavior sbagliato e percentuale mancante
		//deve acorcersi prima che l'id è sbagliato
		
		String file = "test/builder/testExamples/errorsCallOrder.xml";
		
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
			assertEquals(true, e.getMessage().endsWith("not corresponding to any behavior"));
		}
		
	}
	
	@Test
	public void testPercentSumError(){
		String file = "test/builder/testExamples/percentSumError.xml";
		
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
			assertEquals(true, e.getMessage().startsWith("percentage sum must be equal to 1"));
		}
	}
	
	@Test
	public void testMissingState(){
		String file = "test/builder/testExamples/stateMissingError.xml";
		
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
			assertEquals(true, e.getMessage().startsWith("Initial state in System section missing for the node"));
		}
	}
	
	@Test
	public void testMissingParameters(){
		String file = "test/builder/testExamples/parametersMissingError.xml";
		
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
			assertEquals(true, e.getMessage().startsWith("Parameters specification in System section missing for the node"));
		}
	}
	
	@Test
	public void testMissingStateParam(){
		String file = "test/builder/testExamples/stateParamMissingError.xml";
		
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
			assertEquals(true, e.getMessage().startsWith("State specification in System section missing for the node"));
		}
	}

}
