package org.rachel.tinkerpop3;



public class Demo {

	public static void main(String[] args) throws Exception {
		
		long startTime = System.nanoTime();
		
		QueryDemo.runDemo();
		
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("execution time is   "+duration/1000000 + "  milliseconds");
		
		
	}

}
