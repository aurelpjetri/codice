package visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import behaviors.Behavior;
import graph.DirectedEdge;
import graph.EntryPoint;
import graph.ExitPoint;
import graph.Graph;
import graph.Node;
import graph.RegularNode;
import graph.UndirectedEdge;

public class NetLogoBehaviorVisitor implements Visitor {

//	private BufferedReader inputStreamPre;
//	private BufferedReader inputStreamPost;
	private BufferedReader inputTest;
	
	private PrintWriter outputStream;
	private FileWriter myFileWriter;
	
	public NetLogoBehaviorVisitor() throws IOException {
//		inputStreamPre = null;
//		inputStreamPost = null;
		outputStream = null;
		myFileWriter = new FileWriter("data/outputs/outputBTest.nlogo");
	}
	
	public void visit(Graph g) throws IOException{
		try{
			inputTest = new BufferedReader(new FileReader("data/inputs/inputTest.txt"));
//			inputStreamPre = new BufferedReader(new FileReader("data/inputs/inputBPre.txt"));
//          inputStreamPost = new BufferedReader(new FileReader("data/inputs/inputBPost.txt"));
            outputStream = new PrintWriter(myFileWriter);

            String l;
            while (!inputTest.readLine().equals(";;===start")){
        		l = inputTest.readLine();
                outputStream.println(l);
            }
            

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
            
            while (inputTest.readLine() != null) {
            	l = inputTest.readLine();
                outputStream.println(l);
            }
            
		}
      finally {
	    	  if(inputTest != null){
	    		  inputTest.close();
	    	  }
//            if (inputStreamPre != null) {
//                inputStreamPre.close();
//            }
//            if(inputStreamPost != null){
//            	inputStreamPost.close();
//            }
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
