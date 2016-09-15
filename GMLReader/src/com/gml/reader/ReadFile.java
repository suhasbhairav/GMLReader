package com.gml.reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *  @author suhas
 *  Date: Sep 15, 2016
 *  public static void main(String[] args) throws IOException{
			ReadFile readFile = new ReadFile(
					"netscience.gml");
			String content = readFile.read();		
			ArrayList<String> nodes = readFile.getNodes(content);
			ArrayList<String> edges = readFile.getEdges(content);
			for(String node:nodes){
				System.out.println("Node:"+node);
			}
			for(String edge:edges){
				System.out.println("Edge:"+edge);
			}
		}
 *
 */
public class ReadFile {

	//Filename along with its path
	private String filename;
	
	public ReadFile(String filename){
		this.filename = filename;
	}
	
	
	/**
	 * 
	 * @return File content as String
	 * @throws IOException
	 */
	public String read() throws IOException{
		//http://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
		byte[] encodedFile = Files.readAllBytes(Paths.get(filename));
		return new String(encodedFile, Charset.forName("ASCII"));
		
	}
	
	/**
	 * 
	 * @param content
	 * @return ArrayList containing node-id
	 */
	public ArrayList<String> getNodes(String content){
		ArrayList<String> nodeList = new ArrayList<String>();
		//String nodePattern = "node[\\s]*\\[\\s*(id)\\s*[0-9]*";
		String nodePattern = "(id)\\s*[0-9]*";
		Pattern regex = Pattern.compile(nodePattern);
		Matcher match = regex.matcher(content);
		while(match.find()){
			String line = match.group(0);
			nodeList.add(line.split(" ")[1].trim());
		}
		
		return nodeList;
	}
	
	/**
	 * 
	 * @param content
	 * @return ArrayList containing edges stored as
	 * 			comma separated value in each index
	 */	
	public ArrayList<String> getEdges(String content){
		ArrayList<String> edgeList = new ArrayList<String>();		
		String nodePattern = "(source)[\\s]*[\\d]*[\\s]*(target)[\\s]*[\\d]*";
		Pattern regex = Pattern.compile(nodePattern);
		Matcher match = regex.matcher(content);
		while(match.find()){
			String line = match.group(0);
			line = line.replaceAll("( )+"," ");			
			String[] arr = line.split(" ");			
			edgeList.add(arr[1].trim()+","+arr[3].trim());			
		}
		
		return edgeList;
	}	
	
}
