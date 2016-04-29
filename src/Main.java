import java.io.IOException;

import org.jdom2.JDOMException;

import builder.*;
import grafo.*;
public class Main {
	public static void main(String[] args){
		XMLParser parser = new XMLParser();
		try{
			Graph graph = parser.parseDocumentForGraph("/home/aurel/Scrivania/example2.txt");
			graph.printGraph();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(JDOMException j){
			j.printStackTrace();
		}
	}
}
