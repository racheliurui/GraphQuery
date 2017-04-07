package org.rachel.graph.informationflow;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public class InfoflowGraph {
	
	private Graph graph ;
	
	public Graph getInitedGraph(){
		
		return graph;
	}
	
	public InfoflowGraph (){		
		// in-memory
		graph = new TinkerGraph();
		// on graph.shutdown() the graph is serialized to disk with Java serialization
		//Graph graphFromFile = new TinkerGraph("/tmp/tinkergraph");
		this.addInfoEdge(this.getEdgeById("edge1"));
		this.addInfoEdge(this.getEdgeById("edge2"));
		this.addInfoEdge(this.getEdgeById("edge3"));
		this.addInfoEdge(this.getEdgeById("edge4"));
		this.addInfoEdge(this.getEdgeById("edge5"));
		this.addInfoEdge(this.getEdgeById("edge6"));
		this.addInfoEdge(this.getEdgeById("edge7"));
		
		
		
	}	
	
	public void addInfoVertex(InfoflowNode node){
		Vertex a = graph.addVertex(null);
		a.setProperty("id", node.getId());
		a.setProperty("name", node.getName());
		a.setProperty("createDate", node.getCreateDate());
		a.setProperty("numOfEdge", node.getNumberOfEdges());
	}
	
	public void addInfoEdge(InfoflowEdge edge){
	
		InfoflowNode nodeA = this.getNodeById(edge.fromId);
		InfoflowNode nodeB = this.getNodeById(edge.toId);
		Vertex a = graph.addVertex(null);
		a.setProperty("id", nodeA.getId());
		a.setProperty("name", nodeA.getName());
		a.setProperty("createDate", nodeA.getCreateDate());
		a.setProperty("numOfEdge", nodeA.getNumberOfEdges());
		
		Vertex b = graph.addVertex(null);
		a.setProperty("id", nodeB.getId());
		a.setProperty("name", nodeB.getName());
		a.setProperty("createDate", nodeB.getCreateDate());
		a.setProperty("numOfEdge", nodeB.getNumberOfEdges());
		
		
		Edge e = graph.addEdge(null, a, b, edge.description);	
		e.setProperty("id", edge.id);
		e.setProperty("createdDate", edge.createdDate);
		e.setProperty("numOfMessagePerDay", edge.numberOfMessagePerDay);
	}
	
	
	private InfoflowNode getNodeById(String id){
		
		DateFormat df = new SimpleDateFormat("MM dd kk:mm:ss yyyy");
		try{
			//InfoflowNode(String id,String name,Date createdDate,int numberOfEdges,String createdBy)
			if(id.equals("nodeId1"))
				return new InfoflowNode("nodeId1","SAP",df.parse("01 02 14:00:33 2011"),10,"rachel");		
			else if(id.equals("nodeId2"))
				return new InfoflowNode("nodeId2","DW",df.parse("02 02 14:00:33 2013"),20,"tom");
			else if(id.equals("nodeId3"))
				return new InfoflowNode("nodeId3","MQ",df.parse("01 02 14:00:33 2015"),3,"jerry");
			else if(id.equals("nodeId4"))
				return new InfoflowNode("nodeId4","IIB",df.parse("05 02 14:00:33 2011"),7,"monkey");
			else if(id.equals("nodeId5"))
				return new InfoflowNode("nodeId5","Test",df.parse("01 02 14:00:33 2011"),80,"rachel");
			else if(id.equals("nodeId6"))
				return new InfoflowNode("nodeId6","RH",df.parse("11 02 14:00:33 2016"),9,"cat");
			else
				return new InfoflowNode("nodeId7","BH",df.parse("01 02 14:00:33 2018"),4,"jerry");
			
			
		}catch(Exception e){
			
			
			
		}
		
		return null;
		
		
	}
	
   private InfoflowEdge getEdgeById(String id){
		
		DateFormat df = new SimpleDateFormat("MM dd kk:mm:ss yyyy");
		try{
			//String id, String fromId,String toId,String description,     int numberOfMessagePerDay,Date createdDate)
			if(id.equals("edge1"))
				return new InfoflowEdge("edge1","nodeId1","nodeId2",id, 100,df.parse("01 02 14:00:33 2011"));		
			else if(id.equals("edge2"))
				return new InfoflowEdge("edge2","nodeId1","nodeId2", id,200,df.parse("02 02 14:00:33 2013"));
			else if(id.equals("edge3"))
				return new InfoflowEdge("edge3","nodeId1","nodeId2",id,23,df.parse("01 02 14:00:33 2015"));
			else if(id.equals("edge4"))
				return new InfoflowEdge("edge4","nodeId1","nodeId2",id,4,df.parse("05 02 14:00:33 2011"));
			else if(id.equals("edge5"))
				return new InfoflowEdge("edge5","nodeId1","nodeId2",id,33,df.parse("01 02 14:00:33 2011"));
			else if(id.equals("edge6"))
				return new InfoflowEdge("edge6","nodeId1","nodeId2",id,23,df.parse("11 02 14:00:33 2016"));
			else
				return new InfoflowEdge("edge7","nodeId1","nodeId2",id,1,df.parse("01 02 14:00:33 2018"));
			
			
		}catch(Exception e){
			
			
			
		}
		
		return null;
		
		
	}
	

}
