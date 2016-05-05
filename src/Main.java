import java.io.IOException;

import org.jdom2.JDOMException;

import builder.*;
import grafo.*;

public class Main {
	public static void main(String[] args){
		XMLParser parser = new XMLParser();
		try{
			Graph graph = parser.parseDocumentForGraph("/home/aurel/Scrivania/Tesi/example2");//specify the path to the document
			System.out.println(graph.toString());
			for (Node n : graph.getNodes()){
				System.out.println(n.toString());
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(JDOMException j){
			j.printStackTrace();
		}
	}
}
