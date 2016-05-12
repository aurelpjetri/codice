package builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNot;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.ranges.RangeException;

import grafo.Graph;
import grafo.Node;

public class XMLParser {

	private ConcreteBuilder builder;
	
	public XMLParser (){
		builder = new ConcreteBuilder();
	}
	
	public Graph parseDocumentForGraph(String path){
		
		
		String file = path;
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
			parseTree(root, builder);
		}
		catch(RuntimeException e){
			e.printStackTrace();
		}
		
		try{
			graph = builder.getProduct();
		}
		catch(RuntimeException e){
			e.printStackTrace();
		}
		
		return graph;
		
	}
	
	public void parseTree(Element node, ConcreteBuilder builder){
		
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
			
			String type = getTextAttributeOfElement(n, "nt");
			
			if(type == null){
				type = defaultNodeType;
			}
			
			int x = Integer.parseInt(getTextAttributeOfElement(n, "nx"));
			
			int y = Integer.parseInt(getTextAttributeOfElement(n, "ny"));
			
			int w = Integer.parseInt(getTextAttributeOfElement(n, "nw"));
			
			int h = Integer.parseInt(getTextAttributeOfElement(n, "nh"));
			
			int r = Integer.parseInt(getTextAttributeOfElement(n, "nr"));

			if(type.equalsIgnoreCase("N")){
				builder.buildRegularNode(x, y, w, h, r);
			}
			if(type.equalsIgnoreCase("EX")){
				builder.buildExitPoint(x, y, w, h, r);
			}
			if(type.equalsIgnoreCase("EN")){
				builder.buildEntryPoint(x, y, w, h, r);
			}
				
			else if(!type.equalsIgnoreCase("EN") && 
					!type.equalsIgnoreCase("EX")&&
					!type.equalsIgnoreCase("N")){
				throw new RuntimeException("not able to identify node type "+type);
			}
			
			
			
		}
		
		for(Element e : edges){
			
			String type = getTextAttributeOfElement(e, "et");
			
			if(type == null){
				type = defaultEdgeType;
			}
			
			Element source = getElementWithAttributeValue(nodes, "id", e.getAttributeValue("source"));
			int srcX = Integer.parseInt(getTextAttributeOfElement(source, "nx"));
			int srcY = Integer.parseInt(getTextAttributeOfElement(source, "ny"));
			
			Element target = getElementWithAttributeValue(nodes, "id", e.getAttributeValue("target"));
			int trgX = Integer.parseInt(getTextAttributeOfElement(target, "nx"));
			int trgY = Integer.parseInt(getTextAttributeOfElement(target, "ny"));
			
			int weight = Integer.parseInt(getTextAttributeOfElement(e, "ewg"));
			int width = Integer.parseInt(getTextAttributeOfElement(e, "ewd"));
			
			try{
				if(type.startsWith("d")){
					builder.buildDirectedEdge(srcX, srcY, trgX, trgY, width, weight);
				}
				else{
					builder.buildUndirectedEdge(srcX, srcY, trgX, trgY, width, weight);
				}
			}
			catch(RuntimeException exep){
				exep.printStackTrace();
			}
			
		}
		
	}
	
	//cerca tra gli "elements" quello con attributo "name" = "value" e lo restituisce
	//usato per cercare il nodo con un determinato 'id'
	public Element getElementWithAttributeValue(List<Element> elements, String name, String value){
		for(Element el : elements){
			if(el.getAttributeValue(name).contains(value)){
				return el;
			}
		}
		return null;
	}
	
	
	//itera tra i figli di 'e', si ferma in quello con attributo 'key'= "value",
	//restituisce il 'text' sottostante
	public String getTextAttributeOfElement(Element e, String value){
		for(Element child : e.getChildren()){
			if(child.getAttributeValue("key").contains(value)){
				return child.getText();
			}
		}
		return null;
	}
	
}
