package visitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import grafo.DirectedEdge;
import grafo.Edge;
import grafo.EntryPoint;
import grafo.ExitPoint;
import grafo.Graph;
import grafo.Node;
import grafo.RegularNode;
import grafo.UndirectedEdge;

public class NetLogoGraphVisitor implements Visitor{
	
	private BufferedReader inputStream;
	private BufferedReader inputStream2;
	private PrintWriter outputStream;
	private FileWriter myFileWriter;
	
	public NetLogoGraphVisitor() throws IOException{
		inputStream = null;
		inputStream2 = null;
		outputStream = null;
		myFileWriter = new FileWriter("data/output.nlogo");
	}
	
	public void visit(Graph g) throws IOException{
		
        try {
            inputStream = new BufferedReader(new FileReader("data/input.txt"));
            inputStream2 = new BufferedReader(new FileReader("data/input2.txt"));
            outputStream = new PrintWriter(myFileWriter);

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
            
            int maxX = g.getMaxEastCoordinate();
            int maxY = g.getMaxNorthCoordinate();
            
            outputStream.println("to generate-map");
            outputStream.println("  let new-world-width 2 * world-offset + "+maxX);
            outputStream.println("  let new-world-height 2 * world-offset + "+maxY);
            outputStream.println("  resize-world 0 new-world-width 0 new-world-height \n");
            outputStream.println("  generate-beacons \n"
			            		+ "  connect-beacons \n"
			            		+ "  draw-squares \n"
			            		+ "  draw-streets \n"
			            		+ "  make-patches-non-wall \n");
            outputStream.println("end \n");
            
            outputStream.println("to generate-beacons");
            
            
            for(Node n : g.getNodes()){
            	
        		n.accept(this);
            	
            }
            
            
            
            outputStream.println("end");
            
            outputStream.println("to connect-beacons");
            
            for (Edge e: g.getEdges()){
            	e.accept(this);
            }
            
            outputStream.println("end");
            while ((l = inputStream2.readLine()) != null) {
                outputStream.println(l);
            }
           
        } 
		catch(RuntimeException e){
		        	e.printStackTrace();
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
	
	public void visit(RegularNode n) throws IOException{
		if(outputStream != null){
            outputStream.println("  ask patch (world-offset + "+n.getX()+") (world-offset + "+n.getY()+") [sprout-beacons 1 [");
            outputStream.println("    make-beacon-normal");
            outputStream.println("    set intersection-width "+n.getWidth());
            outputStream.println("    set intersection-height "+n.getHeight());
            outputStream.println("    set intersection-radius "+n.getRadius());
            outputStream.println("  ]]");
		}else if (outputStream == null){
			throw new RuntimeException("unable to perform streaming");
		}
                        

	}
	
	public void visit(EntryPoint n) throws IOException{
		if(outputStream != null){
			outputStream.println("  ask patch (world-offset + "+n.getX()+") (world-offset + "+n.getY()+") [sprout-beacons 1 [");
	        outputStream.println("    make-beacon-normal");
	        outputStream.println("    set intersection-width "+n.getWidth());
	        outputStream.println("    set intersection-height "+n.getHeight());
	        outputStream.println("    set intersection-radius "+n.getRadius());
	        outputStream.println("  ]]");
		}else if (outputStream == null){
			throw  new RuntimeException("unable to perform streaming");
		}
        
                    
	}
	
	public void visit(ExitPoint n) throws IOException{
		if(outputStream != null){
	        outputStream.println("  ask patch (world-offset + "+n.getX()+") (world-offset + "+n.getY()+") [sprout-beacons 1 [");
	        outputStream.println("    make-beacon-normal");
	        outputStream.println("    set intersection-width "+n.getWidth());
	        outputStream.println("    set intersection-height "+n.getHeight());
	        outputStream.println("    set intersection-radius "+n.getRadius());
	        outputStream.println("  ]]");
		}
		else if(outputStream == null){
			throw new RuntimeException("unable to perform streaming");
		}

	}	
	
	public void visit(UndirectedEdge e){
		if(outputStream != null){
	        outputStream.println("  ask get-beacon-at (world-offset + "+e.getSource().getX()+") (world-offset + "+e.getSource().getY()+") [");
	        outputStream.println("    create-street-with get-beacon-at (world-offset + "+e.getTarget().getX()+") (world-offset + "+e.getTarget().getY()+") [");
	        outputStream.println("      set weight "+e.getWeight());
	        outputStream.println("      set street-width "+e.getWidth()+"]]");
		}
		else if(outputStream == null){
			throw new RuntimeException("unable to perform streaming");
		}
	}
	
	public void visit(DirectedEdge e){
		if(outputStream != null){
	        outputStream.println("  ask get-beacon-at (world-offset + "+e.getSource().getX()+") (world-offset + "+e.getSource().getY()+") [");
	        outputStream.println("    create-street-with get-beacon-at (world-offset + "+e.getTarget().getX()+") (world-offset + "+e.getTarget().getY()+") [");
	        outputStream.println("      set weight "+e.getWeight());
	        outputStream.println("      set street-width "+e.getWidth()+"]]");

		}
		else if(outputStream == null){
			throw new RuntimeException("unable to perform streaming");
		}
	}
	
	
}
