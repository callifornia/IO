package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CheckFile {

	public List<String> readFile() {
		String inputPath = readKeyboard("Enter input path");
		List<String>inputList = new ArrayList<>();
		
		try (BufferedReader bf = new BufferedReader(new FileReader(inputPath))){
			String line = null; 
			while ((line = bf.readLine()) != null) {
				inputList.add(line);
				line = bf.readLine();
			}
		} catch(FileNotFoundException e){
			System.out.println("Can't find file: " + inputPath);
			this.readFile();
		} catch(IOException e){
			e.printStackTrace();
		}
		return inputList;
	}

	public void writeFile(List<String> outputList) {
		String outputPath = readKeyboard("Enter output path");
		File file = new File(outputPath);
		try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
			for (String string : outputList) {
				bf.append(string);
				bf.newLine();
			}
			bf.flush();
			System.out.println("Check your file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readKeyboard(String message) {
		System.out.print(message + ": ");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	
	
	public List<String> chekStringError(List<String> inputList){
		List<String> outputList = new ArrayList<String>();
		for(String string : inputList){
			List<Integer> indexList = new LinkedList<Integer>();
			Stack<Integer> stack = new Stack<Integer>();
			for(int i = 0; i < string.length(); i++){
				if(string.charAt(i) == '('){
					stack.push(i);
				}
				if(string.charAt(i) == ')'){
					if(stack.empty()){
						indexList.add(i);
					} else {
						stack.pop();
					}
				}
			}
			
			if(!stack.empty()){
				Iterator<Integer> iterator = stack.iterator();
				while(iterator.hasNext()){
					indexList.add(iterator.next());
				}
			}
			
			if(!indexList.isEmpty()){
				outputList.add(string);
				outputList.add(buildMarkString(indexList, string));
				outputList.add("");
			}
		}
		return outputList;
	}
	
	private  String buildMarkString(List<Integer> indexList, String string){
			char[] charMark = new char[string.length()];
			for(Integer i : indexList){
				charMark[i] = '^';
			}
			return new String(charMark);
	}
}