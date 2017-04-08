package org.rachel.graph;

import org.rachel.graph.informationflow.InfoflowGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

@SpringBootApplication
public class BlueprintsPoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueprintsPoCApplication.class, args);
		
		Graph graph = new InfoflowGraph().getInitedGraph();
		GraphQuery query =graph.query();
		
		Vertex.query();
	    
	    

	    Iterable<Vertex> vertices = query.vertices();.
		
		
	}
}
