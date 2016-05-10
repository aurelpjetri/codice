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
			String type = defaultNodeType;
			int x = -1;
			int y = -1;
			int w = -1;
			int h = -1;
			
			
			for(Element attr : n.getChildren("data")){
				if(attr.getAttributeValue("key").contains("nt")){
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
			
			if(x<0 || y<0 || w<0 || h<0){
				throw new RuntimeException("was not able to identify some attributes");
			}

			if(type.equalsIgnoreCase("N")){
				builder.buildRegularNode(x, y, w, h);
			}
			if(type.equalsIgnoreCase("EX")){
				builder.buildExitPoint(x, y, w, h);
			}
			if(type.equalsIgnoreCase("EN")){
				builder.buildEntryPoint(x, y, w, h);
			}
				
			else if(!type.equalsIgnoreCase("EN") && 
					!type.equalsIgnoreCase("EX")&&
					!type.equalsIgnoreCase("N")){
				throw new RuntimeException("not able to identify node type"+type);
			}
			
			
			
		}
		
		for(Element n : edges){
			
			String type = defaultEdgeType;
			
			if(getElementWithAttributeValue(n.getChildren(), "key", "et") != null){
				type = getElementWithAttributeValue(n.getChildren(), "key", "et").getText();
			}
			
			Element source = getElementWithAttributeValue(nodes, "id", n.getAttributeValue("source"));
			int srcX = Integer.parseInt(getTextAttributeofElement(source, "nx"));
			int srcY = Integer.parseInt(getTextAttributeofElement(source, "ny"));
			
			Element target = getElementWithAttributeValue(nodes, "id", n.getAttributeValue("target"));
			int trgX = Integer.parseInt(getTextAttributeofElement(target, "nx"));
			int trgY = Integer.parseInt(getTextAttributeofElement(target, "ny"));
			
			int weight = Integer.parseInt(getTextAttributeofElement(n, "ewg"));
			int width = Integer.parseInt(getTextAttributeofElement(n, "ewd"));
			
			try{
				if(type.startsWith("d")){
					builder.buildDirectedEdge(srcX, srcY, trgX, trgY, width, weight);
				}
				else{
					builder.buildUndirectedEdge(srcX, srcY, trgX, trgY, width, weight);
				}
			}
			catch(RuntimeException e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	//restituisce il nodo con una determinata cippia nome/valore per un attributo
	//usato per estrarre il nodo con id specifico nel documento
	public Element getElementWithAttributeValue(List<Element> elements, String name, String value){
		for(Element el : elements){
			if(el.getAttributeValue(name).contains(value)){
				return el;
			}
		}
		return null;
	}
	
	//restituisce il testo contenuto nel nodo-attributo, figlio dell'elemento "e" 
	//con il valore "value" associato all'attributo key
	public String getTextAttributeofElement(Element e, String value){
		for(Element child : e.getChildren()){
			if(child.getAttributeValue("key").contains(value)){
				return child.getText();
			}
		}
		return null;
	}
	
}
