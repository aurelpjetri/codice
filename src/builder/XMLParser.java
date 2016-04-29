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
		
		
		List<Element> children = node.getChildren();
		
		if(!children.get(0).getChildren().isEmpty()){
			for(Element son: children){
				parseTree(son, builder);
			}
		}
		else{
			if(node.getName().contentEquals("node")){
				List<Element> figli = node.getChildren();
//				if(figli.get(0).getText().startsWith("N")){
//					builder.buildRegularNode( Integer.parseInt(figli.get(1).getText()));
//				}
				builder.buildRegularNode( Integer.parseInt(figli.get(1).getText()));
			}
			else if(node.getName().contentEquals("edge")){
				List<Element> figli = node.getChildren();
				builder.buildEdge(Integer.parseInt(figli.get(0).getText()), Integer.parseInt(figli.get(1).getText()));
			}
		}
		
	}
	
}
