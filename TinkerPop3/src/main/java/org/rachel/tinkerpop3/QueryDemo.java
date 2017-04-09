package org.rachel.tinkerpop3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.function.Predicate;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

public class QueryDemo {
	
	public static void runDemo() throws Exception{
		
		// V3 version
				//http://tinkerpop.apache.org/docs/current/reference/#tinkergraph-gremlin
				//http://gremlindocs.spmallette.documentup.com/
				
				// build the graph
				DateFormat df = new SimpleDateFormat("MM dd kk:mm:ss yyyy");

				
				Graph graph = TinkerGraph.open();
				Vertex v1 = graph.addVertex("id", "id1", "name", "name1", "createdDate", df.parse("01 22 11:31:13 2011"),"decription","test111");
				Vertex v2 = graph.addVertex("id", "id2", "name", "name2", "createdDate", df.parse("02 22 11:31:13 2012"),"decription","test111");
				Vertex v3 = graph.addVertex("id", "id3", "name", "name3", "createdDate", df.parse("03 22 11:31:13 2013"),"decription","test111");
				Vertex v4 = graph.addVertex("id", "id4", "name", "name4", "createdDate", df.parse("04 22 11:31:13 2014"),"decription","test222");
				
				Vertex v5 = graph.addVertex("id", "id5", "name", "name5", "createdDate", df.parse("04 22 11:31:13 2015"),"decription","test111");
				Vertex v6 = graph.addVertex("id", "id6", "name", "name6", "createdDate", df.parse("04 22 11:31:13 2016"),"decription","test222");
				
				v1.addEdge("infoflow", v2, "id", "edgeId12", "numOfMessage", 10);
				v1.addEdge("infoflow", v3, "id", "edgeId13", "numOfMessage", 5);
				v1.addEdge("infoflow", v4, "id", "edgeId14", "numOfMessage", 30);

				v2.addEdge("infoflow", v3, "id", "edgeId23", "numOfMessage", 140);
				v2.addEdge("infoflow", v4, "id", "edgeId24", "numOfMessage", 1);

				v3.addEdge("infoflow", v4, "id", "edgeId34", "numOfMessage", 111);
				
				v4.addEdge("infoflow", v5, "id", "edgeId45", "numOfMessage", 111);
				v5.addEdge("infoflow", v6, "id", "edgeId56", "numOfMessage", 111);

				// traverse and query the graph
				GraphTraversalSource g = graph.traversal();
				
						
				System.out.println("===========demo1, query qualified Vertex and then print out the specified properties==========");
				System.out.println("==Vertex that is the outbound (infoflow) of vertex with name equals name1==");
				System.out.println(g.V().has("name","name1").out("infoflow").values("id","name").toList().toString());
				System.out.println("==same as above but handle one by one");
				GraphTraversal<Vertex, Vertex> traversal = g.V().has("name","name1").out("infoflow");	
				Vertex currentV=null;
				while (traversal.hasNext()){
					currentV=traversal.next();
					System.out.println(currentV.property("name").toString());
				}
				System.out.println("===Vertex that is the inbound (infoflow) of vertex with name equals name2==");
				System.out.println(g.V().has("name","name2").in("infoflow").values("id","name").toList().toString());
				System.out.println("===Vertex that is the link (infoflow) of vertex with name equals name2==");
				System.out.println(g.V().has("name","name2").both("infoflow").values("id","name").toList().toString());
				System.out.println("==Vertex with name equals name1==");
				System.out.println(g.V().has("name","name1").values("id","name").toList().toString());
				System.out.println("==Vertex with properties pass certain creteria ==");		
				String nameFilter = "me3";
				System.out.println("for example, name cantains " + nameFilter);
				Predicate<Traverser<Vertex>> NodeCriterion = Tr_vertex -> {			
					//can be flexible to use Regrex or check multiple fields other than only "name"
					return Tr_vertex.get().property("name").value().toString().contains(nameFilter);
				};		
				System.out.println(g.V().filter( NodeCriterion).values("id").toList().toString());
				
				
				System.out.println("===========demo1 end==========");
				
				
				System.out.println("===========demo2, query qualified Edge and then print out the specified properties==========");
				System.out.println("==Edge with description equals name1 and outbound edge named infoflow==");
				System.out.println(g.E().has("id","edgeId14").values("id","name").toList().toString());		
				int numOfMsg = 10;
				Predicate<Traverser<Edge>> EdgeCriterion = Tr_edge -> {			
					//can be flexible to use Regrex or check multiple fileds other than only "name"
					return ((int)Tr_edge.get().property("numOfMessage").value())>numOfMsg;
				};
				System.out.println("==Edge with num of outbound messages bigger than "+numOfMsg);
				System.out.println(g.E().filter(EdgeCriterion).values("id","name").toList().toString());
				System.out.println("===========demo2 end==========");
				
				
				
				
				System.out.println("===========Query sub graph by Predicate==========");
				//repeat(__.inE().subgraph('subGraph').outV()).times(3).cap('subGraph').next() //(1)
				Graph subGraph=(Graph)g.V().has("name","name1").repeat(__.bothE().subgraph("subGraph").bothV()).times(1).cap("subGraph").next();
				GraphTraversalSource sg=subGraph.traversal();
				System.out.println(sg.V().values("id").toList().toString());
				System.out.println(sg.E().values("id").toList().toString());
				subGraph.close();
				
				
				Predicate<Traverser<Edge>> subGraphEdgeCriterion = Tr_edge -> {			
					//can be flexible to use Regrex or check multiple fileds other than only "name"
					return ((int)Tr_edge.get().property("numOfMessage").value())>20;
				};
				Predicate<Traverser<Vertex>> subGraphNodeCriterion = Tr_vertex -> {			
					//can be flexible to use Regrex or check multiple fields other than only "name"
					return Tr_vertex.get().property("decription").value().toString().contains("222");
				};	
				
				subGraph=(Graph)g.V().has("name","name1").repeat(__.bothE().filter(subGraphEdgeCriterion).subgraph("subGraph").bothV()).times(3).cap("subGraph").next();
				sg=subGraph.traversal();
				System.out.println(sg.V().values("id").toList().toString());
				System.out.println(sg.E().values("id").toList().toString());				
				System.out.println("===========end1==========");		
				
				
			
				System.out.println(sg.V().filter(subGraphNodeCriterion).values("id").toList().toString());
				System.out.println(sg.E().values("id").toList().toString());				
				System.out.println("===========end2==========");
				
//				Graph subGraph1=(Graph)g.V().has("name","name1").repeat(__.bothE().filter(subGraphEdgeCriterion).subgraph("subGraph").bothV().filter(subGraphNodeCriterion)).times(3).cap("subGraph").next();
//				sg=subGraph1.traversal();
//				System.out.println(sg.V().values("id").toList().toString());
//				System.out.println(sg.E().values("id").toList().toString());
//				subGraph1.close();
				
				System.out.println("===========end==========");
				
				
			
				
				graph.close();
	}

}
