package main;
import java.util.List;

public class App1 {

	public static void main(String[] args) {
		 CheckFile check = new CheckFile();
		 List<String> input = check.readFile();
		 List<String> output = check.chekStringError(input);
		 check.writeFile(output);
	}
}