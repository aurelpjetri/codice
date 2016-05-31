import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import builder.XMLParser;
import graph.EntryPoint;
import graph.Graph;
import visitor.NetLogoBehaviorVisitor;
import visitor.NetLogoGraphVisitor;

public class Main {
	private static final String INPUT_GRAPH_XML_PARAM = "--inputGraphXML";
	private static final String OUTPUT_BEHAVIOUR_PARAM = "--outputBehavior";
	private static final String OUTPUT_GRAPH_PARAM = "--outputGraph";
	private static final String MODEL_BEHAVIOUR_PARAM = "--modelBehavior";
	private static final String MODEL_GRAPH_PARAM = "--modelGraph";

	public static void main(String args[]){
		Map<String,String> parameters;
		try{
			//From CLI
			parameters = getParametersAsMap(args);
			//Default parameter (uncomment this and comment the previous)
			//parameters = getDefaultTestParameters();
		}catch(IllegalArgumentException e){
			System.out.println("ERROR:" + e.getMessage() + "\n\n" + help());
			System.exit(1);
			return;
		}

		try{
			XMLParser parser = new XMLParser();
			//NetLogoBehaviorVisitor behaviorVisitor = new NetLogoBehaviorVisitor("data/inputs/inputBehaviors.txt", "data/outputs/outputBehavior.nlogo");
			//NetLogoGraphVisitor graphVisitor = new NetLogoGraphVisitor("data/inputs/inputGraph.txt", "data/outputs/outputGraph.nlogo");
			//Graph graph = parser.parseDocumentForGraph("data/xmls/16x16grid.xml");

			NetLogoBehaviorVisitor behaviorVisitor = new NetLogoBehaviorVisitor(parameters.get(MODEL_BEHAVIOUR_PARAM), parameters.get(OUTPUT_BEHAVIOUR_PARAM));
			NetLogoGraphVisitor graphVisitor = new NetLogoGraphVisitor(parameters.get(MODEL_GRAPH_PARAM), parameters.get(OUTPUT_GRAPH_PARAM));
			Graph graph = parser.parseDocumentForGraph(parameters.get(INPUT_GRAPH_XML_PARAM));
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

	private static Map<String,String> getDefaultTestParameters(){
		Map<String,String> p = new HashMap<>();
	    p.put(INPUT_GRAPH_XML_PARAM, "data/xmls/example2.xml");
	    p.put(OUTPUT_BEHAVIOUR_PARAM, "data/outputs/outputBehaviors.nlogo");
	    p.put(OUTPUT_GRAPH_PARAM, "data/outputs/outputGraph.nlogo");
	    p.put(MODEL_BEHAVIOUR_PARAM, "data/inputs/inputBehaviors.txt");
	    p.put(MODEL_GRAPH_PARAM, "data/inputs/inputGraph.txt");

		return p;
	}

	private static Map<String,String> getParametersAsMap(String args[]){
		Map<String,String> parameters = new HashMap<>();
		for (String arg : args) {
			String[] elements = arg.split("=");
			if(elements.length != 2 || !elements[0].startsWith("--")){
				throw new IllegalArgumentException("All parameter should be in the form '--key=value'");
			}
			parameters.put(elements[0], elements[1]);
		}
		checkParameters(parameters);
		return parameters;
	}

	private static void checkParameters(Map<String,String> parameters){
		if(parameters.get(INPUT_GRAPH_XML_PARAM) == null)
			throw new IllegalArgumentException(INPUT_GRAPH_XML_PARAM + " is mandatory");
		if(parameters.get(OUTPUT_BEHAVIOUR_PARAM) == null)
			throw new IllegalArgumentException(OUTPUT_BEHAVIOUR_PARAM + " is mandatory");
		if(parameters.get(OUTPUT_GRAPH_PARAM) == null)
			throw new IllegalArgumentException(OUTPUT_GRAPH_PARAM + " is mandatory");
		if(parameters.get(MODEL_BEHAVIOUR_PARAM) == null)
			throw new IllegalArgumentException(MODEL_BEHAVIOUR_PARAM + " is mandatory");
		if(parameters.get(MODEL_GRAPH_PARAM) == null)
			throw new IllegalArgumentException(MODEL_GRAPH_PARAM + " is mandatory");
	}

	private static String help(){
		StringBuilder sb = new StringBuilder();
		sb.append("USAGE java -jar netlogo-model-generator.jar --inputGraphXML=INPUTGRAPHXML --outputBehavior=OUTPUTBEHAVIOR --outputGraph=OUTPUTGRAPH --modelBehavior=MODELBEHAVIOR --modelGraph=MODELGRAPH\n");
		sb.append("All arguments are mandatory:\n");
		sb.append("--inputGraphXML		input file name\n");
		sb.append("--outputBehavior		name of the output file that will contain the behavior\n");
		sb.append("--outputGraph		name of the output file that will contain the graph\n");
		sb.append("--modelBehavior		model for the behavior\n");
		sb.append("--modelGraph			model for the graph\n");

		return sb.toString();
	}
}
