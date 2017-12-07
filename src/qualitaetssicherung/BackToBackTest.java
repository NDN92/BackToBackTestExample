package qualitaetssicherung;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class BackToBackTest {
	
	private Softwareversion1 swv1;
	private Softwareversion2 swv2;
	
	public BackToBackTest(Softwareversion1 swv1, Softwareversion2 swv2) {
		this.swv1 = swv1;
		this.swv2 = swv2;
	}
	
	public void run() throws IOException {
		BufferedReader br_testdata;
		BufferedReader br_output1;
		BufferedReader br_output2;
		PrintWriter    pw_output1;
		PrintWriter    pw_output2;
		PrintWriter    pw_result;
		String testData = null;
		int rootValue;
		int radicand;
		ArrayList<Integer> rootValues;
		ArrayList<Integer> radicands;
		String output1 = null;
		String output2;
		String resultLine;
		
		//Testdaten generieren
		br_testdata =  new BufferedReader(new FileReader("src/qualitaetssicherung/testdata.txt"));
		generateTestDatas();
		
		
		//Softwareversionen mit Testdaten ausführen und Ergebnisse speichern.
		pw_output1 = new PrintWriter("src/qualitaetssicherung/output1.txt");
		pw_output2 = new PrintWriter("src/qualitaetssicherung/output2.txt");
		rootValues = new ArrayList<>();
		radicands  = new ArrayList<>();
		while ((testData = br_testdata.readLine()) != null && testData.length() != 0) {
			String[] parts = testData.split(" ");
			rootValue = Integer.parseInt(parts[0]);
			radicand = Integer.parseInt(parts[1]);
			pw_output1.println(swv1.isSquareRootOf(rootValue, radicand));
			pw_output2.println(swv2.isSquareRootOf(rootValue, radicand));
			rootValues.add(rootValue);
			radicands.add(radicand);
		}
		br_testdata.close();
		pw_output1.close();
		pw_output2.close();
		
		
		//Ergebnisse der Softwareversionen miteinander vergleichen
		br_output1 = new BufferedReader(new FileReader("src/qualitaetssicherung/output1.txt"));
		br_output2 = new BufferedReader(new FileReader("src/qualitaetssicherung/output2.txt"));
		pw_result  = new PrintWriter("src/qualitaetssicherung/result.txt");
		for (int i = 0; ((output1 = br_output1.readLine()) != null && output1.length() != 0) &
						((output2 = br_output2.readLine()) != null && output2.length() != 0)   ; i++) {
			resultLine = "Testcase " + (i+1) + ":";
			while(resultLine.length() < 15) { resultLine += " "; }
			
			if(output1.equals(output2)) {
				resultLine += "EQUAL";
			} else {
				resultLine += "UNEQUAL";
			}
			while(resultLine.length() < 27) { resultLine += " "; }
			
			resultLine += "(SWV1: " + output1;
			while(resultLine.length() < 40) { resultLine += " "; }
			resultLine += "| SWV2: " + output2;
			while(resultLine.length() < 54) { resultLine += " "; }
			resultLine += "| Testdata: " + rootValues.get(i) + ", " + radicands.get(i) + ")";
			pw_result.println(resultLine);
		}
		br_output1.close();
		br_output2.close();
		pw_result.close();			
	}
	
	private void generateTestDatas() throws FileNotFoundException {		
		PrintWriter pw = new PrintWriter("src/qualitaetssicherung/testdata.txt");
		
		pw.println("5 -25");
		pw.println("-5 25");
		pw.println("-5 -25");
		pw.println("-3 9");
		pw.println("-3 -9");
		pw.println("3 9");
		pw.println("-10 -99");
		pw.println("150 -25");
		pw.println("12 144");
		pw.println("12 -144");
		
		Random rand;
		
		//Korrekte Testdaten
		for(int i = 0; i < 20; i++) {
			rand = new Random();
			int minRange = 1;
			int maxRange = 1000;
			int value = rand.nextInt((maxRange - minRange) + 1) + minRange;
			pw.println(value + " " + (value * value));
		}
		
		//Falsche Testdaten (Radikand negativ)
		for(int i = 0; i < 10; i++) {
			rand = new Random();
			int minRange = 1;
			int maxRange = 1000;
			int value = rand.nextInt((maxRange - minRange) + 1) + minRange;
			pw.println(value + " " + (value * value * (-1)));
		}
		
		//Falsche Testdaten (Wurzelwert negativ)
		for(int i = 0; i < 10; i++) {
			rand = new Random();
			int minRange = 1;
			int maxRange = 1000;
			int value = rand.nextInt((maxRange - minRange) + 1) + minRange;
			pw.println( (value * (-1)) + " " + (value * value));
		}
		
		//Falsche Testdaten (Wurzelwert + Radikand negativ)
		for(int i = 0; i < 10; i++) {
			rand = new Random();
			int minRange = 1;
			int maxRange = 1000;
			int value = rand.nextInt((maxRange - minRange) + 1) + minRange;
			pw.println( (value * (-1)) + " " + (value * value * (-1)));
		}
		
		//Zufällige Testdaten
		for(int i = 0; i < 10; i++) {
			rand = new Random();
			int value1 = rand.nextInt();
			int value2 = rand.nextInt();
			pw.println(value1 + " " + value2);
		}
		
		pw.close();
	}
	
}
