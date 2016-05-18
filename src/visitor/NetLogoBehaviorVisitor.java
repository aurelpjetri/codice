package visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import behaviors.Behavior;
import grafo.DirectedEdge;
import grafo.EntryPoint;
import grafo.ExitPoint;
import grafo.Graph;
import grafo.Node;
import grafo.RegularNode;
import grafo.UndirectedEdge;

public class NetLogoBehaviorVisitor implements Visitor {

	private BufferedReader inputStream;
	private BufferedReader inputStream2;
	private PrintWriter outputStream;
	private FileWriter myFileWriter;
	
	public NetLogoBehaviorVisitor() throws IOException {
		inputStream = null;
		inputStream = null;
		inputStream2 = null;
		outputStream = null;
		myFileWriter = new FileWriter("data/outputB.nlogo");
	}
	
	public void visit(Graph g) throws IOException{
		try{
			inputStream = new BufferedReader(new FileReader("data/inputBPre.txt"));
            inputStream2 = new BufferedReader(new FileReader("data/inputBPost.txt"));
            outputStream = new PrintWriter(myFileWriter);

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
            
//            ;; Set some defaults and globals for this model
//            to default-configuration
//              set-default-shape beacons "box"
//              set-default-shape movers "circle"
//              set global-crowd-max-at-patch 5
//
//              ;; List of possible destination ordering strategies
//              set destination-ordering table:make
//            	
//              table:put destination-ordering "orderedList" "set-destination-ordered-list"
//            end

            outputStream.println("to default-configuration");
            outputStream.println("  set-default-shape beacons \"box\"");
            outputStream.println("  set-default-shape movers \"circle\"");
            outputStream.println("  set global-crowd-max-at-patch 5");
            outputStream.println(" ");
            outputStream.println("  set behaviors-map table:make");
            for(Behavior b : g.getBehaviors()){
            	b.accept(this);
            }
            outputStream.println("end");
            
            while ((l = inputStream2.readLine()) != null) {
                outputStream.println(l);
            }
            
		}
      finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if(inputStream2 != null){
            	inputStream2.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
	}
	
	public void visit(Behavior behavior){
		String beacons = "map [ list (world-offset + item 0 ?) (world-offset + item 1 ?) ] [";
		for(Node n : behavior.getCoreInterestPoints()){
			beacons = beacons.concat(" [("+n.getX()+") ("+n.getY()+")] ");
		}
		
		outputStream.println("  table:put behaviors-map "+behavior.getId()+" get-interest-beacons "+beacons+"]");
	}

	public void visit(DirectedEdge e){
		
	}
	
	public void visit(UndirectedEdge e){
		
	}
	
	public void visit(RegularNode n){
		
	}
	
	public void visit(EntryPoint n){
		
	}
	
	public void visit(ExitPoint n){
		
	}
	
}
