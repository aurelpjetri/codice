package builder;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import grafo.Graph;

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
	 
	public void parseTree(Element root, ConcreteBuilder builder){
		
//		Element graph = node.getChild("graph");
		
		Element graph = getElementWithAttributeValue(root.getChildren(), "id", "Graph");
		
		
		List<Element> behaviors = getElementWithAttributeValue(root.getChildren(), "id", "Behaviors").getChildren("node");
		
		Element system = getElementWithAttributeValue(root.getChildren(), "id", "System");
		
		List<Element> nodes = graph.getChildren("node");
		
		List<Element> edges = graph.getChildren("edge");
				
		List<Element> attributes = root.getChildren("key");
		
		String defaultNodeType = null;
		
		for(Element at : attributes){
			if(at.getAttributeValue("id").contains("nType")){
				defaultNodeType = at.getChild("default").getText();
			}
		}
		
		String defaultEdgeType = graph.getAttributeValue("edgedefault");
		
		for(Element n : nodes){
			
			String type = getTextAttributeOfElement(n, "nType");
			
			if(type == null){
				type = defaultNodeType;
			}
			
			
			Element nodeStatusElement = getElementWithAttributeValue(system.getChildren(), "id", n.getAttributeValue("id"));
			
			int x = Integer.parseInt(getTextAttributeOfElement(n, "nx"));
			
			int y = Integer.parseInt(getTextAttributeOfElement(n, "ny"));
			
			int w = Integer.parseInt(getTextAttributeOfElement(n, "nWidth"));
			
			int h = Integer.parseInt(getTextAttributeOfElement(n, "nHeight"));
			
			int r = Integer.parseInt(getTextAttributeOfElement(n, "nRadius"));
			
			int s = 0;
			
			if(nodeStatusElement != null){
				s = Integer.parseInt(getTextAttributeOfElement(nodeStatusElement, "nStatus"));
			}

			if(type.equalsIgnoreCase("normal")){
				builder.buildRegularNode(x, y, w, h, r, s);
			}
			if(type.equalsIgnoreCase("exit")){
				builder.buildExitPoint(x, y, w, h, r, s);
			}
			if(type.equalsIgnoreCase("entry")){
				builder.buildEntryPoint(x, y, w, h, r, s);
			}
				
			else if(!type.equalsIgnoreCase("normal") && 
					!type.equalsIgnoreCase("exit")&&
					!type.equalsIgnoreCase("normal")){
				throw new RuntimeException("not able to identify node type "+type);
			}
			
			
		}
		
		for(Element e : edges){
			
			String type = getTextAttributeOfElement(e, "eType");
			
			if(type == null){
				type = defaultEdgeType;
			}
			
			Element source = getElementWithAttributeValue(nodes, "id", e.getAttributeValue("source"));
			int srcX = Integer.parseInt(getTextAttributeOfElement(source, "nx"));
			int srcY = Integer.parseInt(getTextAttributeOfElement(source, "ny"));
			
			Element target = getElementWithAttributeValue(nodes, "id", e.getAttributeValue("target"));
			int trgX = Integer.parseInt(getTextAttributeOfElement(target, "nx"));
			int trgY = Integer.parseInt(getTextAttributeOfElement(target, "ny"));
			
			int weight = Integer.parseInt(getTextAttributeOfElement(e, "weight"));
			int width = Integer.parseInt(getTextAttributeOfElement(e, "eWidth"));
			
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
		
		
		for (Element b : behaviors){
			Element behavior = b.getChild("graph");
			
			String type = b.getChild("data").getText();
			
			int id = Integer.parseInt(behavior.getAttributeValue("id"));
			
			
			if(type.equalsIgnoreCase("evacuate")){
				builder.buildEvacuateBehavior(id);
			}
			if(type.equalsIgnoreCase("visit")){
				builder.buildVisitBehavior(id);
			}
			else if (!type.equalsIgnoreCase("evacuate") && !type.equalsIgnoreCase("visit")){
				throw new RuntimeException("unable to identify type on behavior: "+id);
			}
			
			for(Element i : behavior.getChildren()){
				String id1 = i.getAttributeValue("id");
				
				Element poi = getElementWithAttributeValue(nodes, "id", id1);
				int poiX = Integer.parseInt(getTextAttributeOfElement(poi, "nx"));
				int poiY = Integer.parseInt(getTextAttributeOfElement(poi, "ny"));
				
				if(type.equalsIgnoreCase("evacuate") && !i.getChildren().isEmpty()){
					throw new RuntimeException("evacuate behaviors don't have optional interest points");
				}
				
				builder.buildInterestPointOnBehavior(poiX, poiY, id, !i.getChildren().isEmpty() );
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
