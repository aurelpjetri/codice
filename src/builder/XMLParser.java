package builder;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import grafo.*;

public class XMLParser {

	private ConcreteBuilder builder;
	
	public XMLParser (){
		builder = new ConcreteBuilder();
	}
	
	public Graph parseDocumentForGraph(String path) throws JDOMException, IOException{
		
		
		String file = path;
		Graph graph = new Graph();
		
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = jdomBuilder.build(file);
		
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
		
		
		List<Element> nodes = node.getChild("nodes").getChildren("node");
		
		List<Element> edges = node.getChild("edges").getChildren("edge");
				
		
		for(Element n : nodes){
			String type = n.getChild("type").getText();
			
			int x = Integer.parseInt(n.getChild("x").getText());
			int y = Integer.parseInt(n.getChild("y").getText());
			int w = Integer.parseInt(n.getChild("width").getText());
			int h = Integer.parseInt(n.getChild("height").getText());
			
			
			if(type.equalsIgnoreCase("N")){
				builder.buildRegularNode(x, y, w, h);
			}
			if(type.equalsIgnoreCase("EX")){
				builder.buildExitPoint(x, y, w, h);
			}
			if(type.equalsIgnoreCase("EN")){
				builder.buildEntryPoint(x, y, w, h);
			}
				
			else {
				throw new RuntimeException("not able to identify node type");
			}
			
		}
		
		for(Element n : edges){
			String type = n.getChild("type").getText();
			int srcX = Integer.parseInt(n.getChild("src").getChild("x").getText());
			int srcY = Integer.parseInt(n.getChild("src").getChild("y").getText());
			int dstX = Integer.parseInt(n.getChild("dst").getChild("x").getText());
			int dstY = Integer.parseInt(n.getChild("dst").getChild("y").getText());
			int width = Integer.parseInt(n.getChild("width").getText());
			int weight = Integer.parseInt(n.getChild("weight").getText());
			
			try{
				if(type.startsWith("d")){
					builder.buildDirectedEdge(srcX, srcY, dstX, dstY, width, weight);
				}
				else{
					builder.buildUndirectedEdge(srcX, srcY, dstX, dstY, width, weight);
				}
			}
			catch(RuntimeException e){
				e.printStackTrace();
			}
			
		}
		
	}
	
}
