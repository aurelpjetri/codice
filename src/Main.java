import java.io.IOException;

import builder.XMLParser;
import graph.Graph;
import visitor.NetLogoBehaviorVisitor;
import visitor.NetLogoGraphVisitor;

public class Main {
	public static void main(String args[]){
		try{
			XMLParser parser = new XMLParser();
			NetLogoBehaviorVisitor behaviorVisitor = new NetLogoBehaviorVisitor();
			NetLogoGraphVisitor graphVisitor = new NetLogoGraphVisitor();
			Graph graph = parser.parseDocumentForGraph("data/example3.xml");
			graph.accept(graphVisitor);
			graph.accept(behaviorVisitor);
		}
		catch(IOException e1){
			e1.printStackTrace();
		}
		catch(RuntimeException e2){
			e2.printStackTrace();
		}
	}
}
