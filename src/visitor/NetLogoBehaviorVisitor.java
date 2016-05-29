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


	private BufferedReader inputStream;

	private PrintWriter outputStream;
	private FileWriter myFileWriter;

	public NetLogoBehaviorVisitor() throws IOException {
		outputStream = null;
		myFileWriter = new FileWriter("data/outputs/outputBehaviors.nlogo");
	}

	public void visit(Graph g) throws IOException{
		try{
			inputStream = new BufferedReader(new FileReader("data/inputs/inputBehaviors.txt"));
			outputStream = new PrintWriter(myFileWriter);

			String l;
			while (!((l = inputStream.readLine()).equals(";;===start"))){
				outputStream.println(l);
			}
			
			outputStream.println("  set behaviors-map table:make");
			for(Behavior b : g.getBehaviors()){
				b.accept(this);
			}

			
			outputStream.println("  set initial-state []");
			outputStream.println("  populate-initial-state");
			outputStream.println(" ");
			outputStream.println("  set-world-initial-state");
			outputStream.println("end");
			outputStream.println(" ");
			
			outputStream.println("to populate-initial-state ");
			for(Node n : g.getNodes()){
				n.accept(this);
			}
			outputStream.println("end");
			
			while ((l = inputStream.readLine()) != null) {
				outputStream.println(l);
			}

		}
		finally {
			if(inputStream != null){
				inputStream.close();
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
		
		String initialState = "  set initial-state lput ["+n.getX()+" "+n.getY();

		for(int behaviorID : n.getState().keySet()){

			if(n.getState().get(behaviorID) > 0){
//					outputStream.println("  ask item 0 get-interest-beacons map [list (world-offset + item 0 ?) (world-offset + item 1 ?)] [["+n.getX()+" "+n.getY()+"]] [");
//					outputStream.println("    ask one-of patches in-radius intersection-radius [sprout-movers "+n.getState().get(behaviorID)+" [");
//
//					outputStream.println("      set mover-behavior "+behaviorID);
//					outputStream.println("      standard-mover-settings]]]");

//					outputStream.println("  set initial-state lput ["+n.getX()+" "+n.getY()+" "+behaviorID+" "+n.getState().get(behaviorID)+"] initial-state");

				initialState += " "+behaviorID+" "+n.getState().get(behaviorID);
			}
		}
		initialState += "] initial-state";
		
		if(!initialState.equals("  set initial-state lput ["+n.getX()+" "+n.getY()+"] initial-state")){
			outputStream.println(initialState);
		}

	}

	public void visit(EntryPoint n){
		
		String initialState = "  set initial-state lput ["+n.getX()+" "+n.getY();

		for(int behaviorID : n.getState().keySet()){

			if(n.getState().get(behaviorID) > 0){
//					outputStream.println("  ask item 0 get-interest-beacons map [list (world-offset + item 0 ?) (world-offset + item 1 ?)] [["+n.getX()+" "+n.getY()+"]] [");
//					outputStream.println("    ask one-of patches in-radius intersection-radius [sprout-movers "+n.getState().get(behaviorID)+" [");
//
//					outputStream.println("      set mover-behavior "+behaviorID);
//					outputStream.println("      standard-mover-settings]]]");

//					outputStream.println("  set initial-state lput ["+n.getX()+" "+n.getY()+" "+behaviorID+" "+n.getState().get(behaviorID)+"] initial-state");

				initialState += " "+behaviorID+" "+n.getState().get(behaviorID);
			}
		}
		initialState += "] initial-state";
		
		if(!initialState.equals("  set initial-state lput ["+n.getX()+" "+n.getY()+"] initial-state")){
			outputStream.println(initialState);
		}
	}

	public void visit(ExitPoint n){
		
		String initialState = "  set initial-state lput ["+n.getX()+" "+n.getY();

		for(int behaviorID : n.getState().keySet()){

			if(n.getState().get(behaviorID) > 0){
//					outputStream.println("  ask item 0 get-interest-beacons map [list (world-offset + item 0 ?) (world-offset + item 1 ?)] [["+n.getX()+" "+n.getY()+"]] [");
//					outputStream.println("    ask one-of patches in-radius intersection-radius [sprout-movers "+n.getState().get(behaviorID)+" [");
//
//					outputStream.println("      set mover-behavior "+behaviorID);
//					outputStream.println("      standard-mover-settings]]]");

//					outputStream.println("  set initial-state lput ["+n.getX()+" "+n.getY()+" "+behaviorID+" "+n.getState().get(behaviorID)+"] initial-state");

				initialState += " "+behaviorID+" "+n.getState().get(behaviorID);
			}
		}
		initialState += "] initial-state";
		
		if(!initialState.equals("  set initial-state lput ["+n.getX()+" "+n.getY()+"] initial-state")){
			outputStream.println(initialState);
		}
	}

}
