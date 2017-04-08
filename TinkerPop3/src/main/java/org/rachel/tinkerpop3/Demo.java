package org.rachel.tinkerpop3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

import org.apache.tinkerpop.gremlin.process.computer.traversal.strategy.optimization.GraphFilterStrategy;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.strategy.decoration.SubgraphStrategy;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;


public class Demo {

	public static void main(String[] args) throws ParseException {
		// V3 version
		//http://tinkerpop.apache.org/docs/current/reference/#tinkergraph-gremlin
		//http://gremlindocs.spmallette.documentup.com/
		
		// build the graph
		DateFormat df = new SimpleDateFormat("MM dd kk:mm:ss yyyy");
		Graph graph = TinkerGraph.open();
		Vertex v1 = graph.addVertex("id", "id1", "name", "name1", "createdDate", df.parse("01 22 11:31:13 2011"));
		Vertex v2 = graph.addVertex("id", "id2", "name", "name2", "createdDate", df.parse("02 22 11:31:13 2011"));
		Vertex v3 = graph.addVertex("id", "id3", "name", "name3", "createdDate", df.parse("03 22 11:31:13 2011"));
		Vertex v4 = graph.addVertex("id", "id4", "name", "name4", "createdDate", df.parse("04 22 11:31:13 2011"));

		v1.addEdge("infoflow", v2, "id", "edgeId12", "numOfMessage", 10);
		v1.addEdge("infoflow", v3, "id", "edgeId13", "numOfMessage", 5);
		v1.addEdge("infoflow", v4, "id", "edgeId14", "numOfMessage", 30);

		v2.addEdge("infoflow", v3, "id", "edgeId23", "numOfMessage", 140);
		v2.addEdge("infoflow", v4, "id", "edgeId24", "numOfMessage", 1);

		v3.addEdge("infoflow", v4, "id", "edgeId34", "numOfMessage", 111);

		// traverse and query the graph
		GraphTraversalSource g = graph.traversal();
		
				
		
		System.out.println("===========S1 Query by vertex name & then linked vertex with out bound edge properties==========");
		System.out.println(g.V().has("name","name2").out("infoflow").values("id").toList().toString());
		System.out.println("===========end==========");
		
		System.out.println("===========S2 Query by vertex name & then linked vertex with in/out bound edge properties==========");
		System.out.println(g.V().has("name","name2").both("infoflow").values("id").toList().toString());
		System.out.println("===========end==========");
		
		System.out.println("===========S3 Query by vertex name==========");
		String nameFilter = "me3";
		Predicate<Traverser<Vertex>> NodeNameCriterion = Tr_vertex -> {
			
			//can be flexible to use Regrex
			return Tr_vertex.get().property("name").value().toString().contains(nameFilter);
		};
		
		System.out.println(g.V().filter( NodeNameCriterion).values("id").toList().toString());
		System.out.println("===========end==========");
		
		
		System.out.println("===========Query sub graph by Predicate==========");
//        Predicate<Traversal<Vertex, ?>> demoVertexCreteria = Tr_vertex -> {
//			
//        	Date createDate =(Date)Tr_vertex.get().property("createdDate").value();
//			return createDate.before(new Date());
//		};
//		GraphFilterStrategy strategy = SubgraphStrategy.build().vertices(demoVertexCreteria).vertexPredicate(vertexCriterion).edgePredicate(edgeCriterion).create();
// 
//		GraphTraversalSource sg = g.withStrategies(strategy);
//
//		// all vertices are here
//		System.out.println("" + sg.V().count().next() + " of " + g.V().count().next() + " vertices");
//
//		// only the given edges are included
//		System.out.println("" + sg.E().count().next() + " of " + g.E().count().next() + " edges");
		System.out.println("===========end==========");
	}

}
