# GMLReader

GMLReader is a simple program for reading .gml files in Java. Some of the .gml files can be downloaded from here http://www-personal.umich.edu/~mejn/netdata/ .
Sample Code to execute the file is :
  ```
  ReadFile readFile = new ReadFile("netscience.gml");
	String content = readFile.read();		
	ArrayList<String> nodes = readFile.getNodes(content);
	ArrayList<String> edges = readFile.getEdges(content);
	for(String node:nodes){
		System.out.println("Node:"+node);
	}
	for(String edge:edges){
		System.out.println("Edge:"+edge);
	}
	
	
	
	
