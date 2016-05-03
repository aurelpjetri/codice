package builder;

import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import grafo.*;

public class XMLParser {

	private ConcreteBuilder builder;
	
	public XMLParser (){
		builder = new ConcreteBuilder();
	}
	
	public Graph parseDocumentForGraph(String path) throws JDOMException, IOException{
		
		
		String file = path;
		
		SAXBuilder jdomBuilder = new SAXBuilder(); 
		Document jdomDocument = jdomBuilder.build(file);
		
		Element root = jdomDocument.getRootElement();
		
		builder.buildGraph();
		
		parseTree(root, builder);
		return builder.getProduct();
		
	}
	
	public void parseTree(Element node, ConcreteBuilder builder){
		
		
		List<Element> nodes = node.getChild("nodes").getChildren("node");
		
		List<Element> edges = node.getChild("edges").getChildren("edge");
				
		
		for(Element n : nodes){
			String type = n.getChild("type").getText();
			
			int x = Integer.parseInt(n.getChild("x").getText());
			int y = Integer.parseInt(n.getChild("y").getText());
			if(type.equalsIgnoreCase("N")){
				builder.buildRegularNode(x, y);
			}
			if(type.equalsIgnoreCase("EX")){
				builder.buildExitPoint(x, y);
			}
			if(type.equalsIgnoreCase("EN")){
				builder.buildEntryPoint(x, y);
			}
				
//			else {
//				throw new RuntimeException("not able to identify node type");
//			}
			
		}
		
		for(Element n : edges){
			String type = n.getChild("type").getText();
			int srcX = Integer.parseInt(n.getChild("src").getChild("x").getText());
			int srcY = Integer.parseInt(n.getChild("src").getChild("y").getText());
			int dstX = Integer.parseInt(n.getChild("dst").getChild("x").getText());
			int dstY = Integer.parseInt(n.getChild("dst").getChild("y").getText());
			
			if(type.startsWith("d")){
				builder.buildDirectedEdge(srcX, srcY, dstX, dstY);
			}
			else{
				builder.buildUndirectedEdge(srcX, srcY, dstX, dstY);
			}
		}
		
	}
	
}
