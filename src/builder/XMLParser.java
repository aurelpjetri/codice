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
		
		
		
		
		return new Graph();
		
	}
	
}
