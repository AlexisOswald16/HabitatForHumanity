package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelpModel {

	
	public void writeToTextFile(String text) throws IOException{
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("HelpComments.txt", true));
			out.write(text);
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("Could not be written to file.");
		}
	}
	
}
