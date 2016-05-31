import java.io.IOException;

import builder.XMLParser;
import graph.EntryPoint;
import graph.Graph;
import visitor.NetLogoBehaviorVisitor;
import visitor.NetLogoGraphVisitor;

public class Main {
	// passa percorso file xml e percorso file di output sia per behavior che per grafo
	public static void main(String args[]){
		try{
			XMLParser parser = new XMLParser();
			NetLogoBehaviorVisitor behaviorVisitor = new NetLogoBehaviorVisitor();
			NetLogoGraphVisitor graphVisitor = new NetLogoGraphVisitor();
			Graph graph = parser.parseDocumentForGraph("data/xmls/16x16grid.xml");
						
			graph.accept(graphVisitor);
			graph.accept(behaviorVisitor);
		}
		catch(IOException e1){
			e1.printStackTrace();
		}
		catch(RuntimeException e2){
			e2.printStackTrace();
		}
		
		
//		XMLParser parser = new XMLParser();
//		Graph graph = parser.parseDocumentForGraph("data/xmls/example3.xml");
//		for(Node n : graph.getNodes()){
//			for()
//		}
	}
}
