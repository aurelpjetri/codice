package builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import graph.Graph;

public class XMLParser implements Parser{

	private NetLogoGraphBuilder builder;
	
	public XMLParser (){
		builder = new NetLogoGraphBuilder();
	}
	
	public Graph parseDocumentForGraph(String path) throws RuntimeException{
		
		
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
	 
	public void parseTree(Element root, NetLogoGraphBuilder builder){
		
		
		Element graph = getElementWithAttributeValue(root.getChildren(), "id", "Graph");
		
		parseNodes(root, graph, builder);
		
		parseEdges(graph, builder);
		
		parseBehaviors(root, graph, builder);
		
		
	} 
	
	//parses both nodes and initial state ('System') of the graph
	public void parseNodes(Element root, Element graph, NetLogoGraphBuilder buider){

		  List<Element> nodes = graph.getChildren("node");

		  Element system = getElementWithAttributeValue(root.getChildren(), "id", "System");

		  String defaultNodeType = null;

		  for(Element at : root.getChildren("key")){
		    if(at.getAttributeValue("id").contains("nType")){
		      defaultNodeType = at.getChild("default").getText();
		    }
		  }

		  for(Element n : nodes){
		    
		    String nType = getTextAttributeOfElement(n, "nType");
		    
		    if(nType == null){
		      nType = defaultNodeType;
		    }
		    
		    
		    Element nodeSystemElement = getElementWithAttributeValue(system.getChildren(), "id", n.getAttributeValue("id"));
		    
		    Element nodeStatusElement = getElementWithAttributeValue(nodeSystemElement.getChildren(), "id", "state");
		    
		    Element nodeParametersElement = getElementWithAttributeValue(nodeSystemElement.getChildren(), "id", "parameters");

		          
		    int x = Integer.parseInt(getTextAttributeOfElement(n, "nx"));
		    
		    int y = Integer.parseInt(getTextAttributeOfElement(n, "ny"));
		    
		    int nWidth = Integer.parseInt(getTextAttributeOfElement(n, "nWidth"));
		    
		    int nHeight = Integer.parseInt(getTextAttributeOfElement(n, "nHeight"));
		    
		    int radius = Integer.parseInt(getTextAttributeOfElement(n, "nRadius"));
		    
		    HashMap<Integer,Integer> state = new HashMap<Integer,Integer>();
		    
		    HashMap<Integer,Float> percentage = new HashMap<Integer,Float>();
		    
		    float rate = 0;
		    
		    
		    //DOVREI FARE UN CONTROLLO SUL ID DEL BEHAVIOR 
		    //PER VEDERE SE CORRISPONDE A UN BEHAVIOR ESISTENTE
		    if(!nType.equalsIgnoreCase("normal")){
		      
		      if(nodeParametersElement.getChild("data") == null){
		        throw new RuntimeException("rate missing on node: "+x+" , "+y);
		      }
		      
		      rate = Float.parseFloat(nodeParametersElement.getChild("data").getText());
		      
		      
		      for(Element behaviorRates : nodeParametersElement.getChildren("behavior")){
		        
		        if(getTextAttributeOfElement(behaviorRates, "percentage") == null){
		          throw new RuntimeException("percentages missing on node: "+x+" , "+y);
		        }
		        
		        else{
		          int localId = Integer.parseInt(behaviorRates.getAttributeValue("id"));
		          float localRate = Float.parseFloat(getTextAttributeOfElement(behaviorRates, "percentage"));
		          percentage.put(localId, localRate);
		        }
		      }
		    }
		    
		    for(Element behaviorState : nodeStatusElement.getChildren()){
		      int localId = Integer.parseInt(behaviorState.getAttributeValue("id"));
		      int localQuantity = Integer.parseInt(getTextAttributeOfElement(behaviorState, "moverQuantity"));
		      state.put(localId, localQuantity );
		    }
		          

		    if(nType.equalsIgnoreCase("normal")){
		      builder.buildRegularNode(x, y, nWidth, nHeight, radius, state);
		    }
		    if(nType.equalsIgnoreCase("exit")){
		      builder.buildExitPoint(x, y, nWidth, nHeight, radius, rate, state, percentage);
		    }
		    if(nType.equalsIgnoreCase("entry")){
		      builder.buildEntryPoint(x, y, nWidth, nHeight, radius, rate, state, percentage);
		    }
		      
		    else if(!nType.equalsIgnoreCase("normal") && 
		        !nType.equalsIgnoreCase("exit")&&
		        !nType.equalsIgnoreCase("normal")){
		      throw new RuntimeException("not able to identify node type "+nType);
		    }
		    
		    
		  }

		  
		}
	
	public void parseEdges(Element graph, NetLogoGraphBuilder builder){

		  List<Element> edges = graph.getChildren("edge");
		  
		  List<Element> nodes = graph.getChildren("node");

		  String defaultEdgeType = graph.getAttributeValue("edgedefault");

		  for(Element e : edges){
		    
		    String eType = getTextAttributeOfElement(e, "eType");
		    
		    if(eType == null){
		      eType = defaultEdgeType;
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
		      if(eType.startsWith("d")){
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

	public void parseBehaviors(Element root, Element graph, NetLogoGraphBuilder builder){

		  List<Element> behaviors = getElementWithAttributeValue(root.getChildren(), "id", "Behaviors").getChildren("node");

		  List<Element> nodes = graph.getChildren("node");

		  for (Element b : behaviors){
		    Element behavior = b.getChild("graph");
		    
		    String bType = b.getChild("data").getText();
		    
		    int id = Integer.parseInt(behavior.getAttributeValue("id"));
		    
		    
		    if(bType.equalsIgnoreCase("evacuate")){
		      builder.buildEvacuateBehavior(id);
		    }
		    if(bType.equalsIgnoreCase("visit")){
		      builder.buildVisitBehavior(id);
		    }
		    else if (!bType.equalsIgnoreCase("evacuate") && !bType.equalsIgnoreCase("visit")){
		      throw new RuntimeException("unable to identify type on behavior: "+id);
		    }
		    
		    for(Element i : behavior.getChildren()){
		      String id1 = i.getAttributeValue("id");
		      
		      Element poi = getElementWithAttributeValue(nodes, "id", id1);
		      int poiX = Integer.parseInt(getTextAttributeOfElement(poi, "nx"));
		      int poiY = Integer.parseInt(getTextAttributeOfElement(poi, "ny"));
		      
		      if(bType.equalsIgnoreCase("evacuate") && !i.getChildren().isEmpty()){
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
			if(el.getAttributeValue(name).equalsIgnoreCase(value)){
				return el;
			}
		}
		return null;
	}
	
	
	//itera tra i figli di 'e', si ferma in quello con attributo 'key'= "value",
	//restituisce il 'text' sottostante
	public String getTextAttributeOfElement(Element e, String value){
		for(Element child : e.getChildren()){
			if(child.getAttributeValue("key").equalsIgnoreCase(value)){
				return child.getText();
			}
		}
		return null;
	}
	
}
