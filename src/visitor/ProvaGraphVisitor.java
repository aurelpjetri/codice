package visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import grafo.DirectedEdge;
import grafo.EntryPoint;
import grafo.ExitPoint;
import grafo.Graph;
import grafo.Node;
import grafo.RegularNode;
import grafo.UndirectedEdge;

public class ProvaGraphVisitor implements Visitor{
	
	private BufferedReader inputStream;
	private PrintWriter outputStream;
	private FileWriter myFileWriter;
	
	public ProvaGraphVisitor() throws IOException{
		try{
			inputStream = new BufferedReader(new FileReader("data/input.txt"));
			outputStream = new PrintWriter(myFileWriter);
			myFileWriter = new FileWriter("data/output.txt");
		}
		finally{
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
		}
        
	
		
	}
	
	public void visit(Graph g) throws IOException{
		

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
      
	}
	
	public void visit(RegularNode n) throws IOException{
	          
            
            outputStream.println("  ask patch "+n.getX()+" "+n.getY()+" [sprout-beacon 1 [");
            outputStream.println("    make-beacon-normal");
            outputStream.println("    set intersection-width "+n.getWidth());
            outputStream.println("    set intersection-height "+n.getHeight());
            outputStream.println("    set intersection-radius "+n.getRadius());
            outputStream.println("  ]]");

	}
	
	public void visit(EntryPoint n) throws IOException{
	
			
            
            
            outputStream.println("  ask patch "+n.getX()+" "+n.getY()+" [sprout-beacon 1 [");
            outputStream.println("    make-beacon-entry");
            outputStream.println("    set intersection-width "+n.getWidth());
            outputStream.println("    set intersection-height "+n.getHeight());
            outputStream.println("    set intersection-radius "+n.getRadius());
            outputStream.println("  ]]");
	            
			


	}
	
	public void visit(ExitPoint n) throws IOException{
	
			
            
            
            outputStream.println("  ask patch "+n.getX()+" "+n.getY()+" [sprout-beacon 1 [");
            outputStream.println("    make-beacon-exit");
            outputStream.println("    set intersection-width "+n.getWidth());
            outputStream.println("    set intersection-height "+n.getHeight());
            outputStream.println("    set intersection-radius "+n.getRadius());
            outputStream.println("  ]]");
            
		

	}	
	
	public void visit(DirectedEdge e){
		
	}
	
	public void visit(UndirectedEdge e){
		
	}
}
