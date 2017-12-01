package qualitaetssicherung;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BackToBackTest {
	
	private Softwareversion1 swv1;
	private Softwareversion2 swv2;
	
	public BackToBackTest(Softwareversion1 swv1, Softwareversion2 swv2) {
		this.swv1 = swv1;
		this.swv2 = swv2;
	}
	
	public void run() throws IOException {
		BufferedReader br =  new BufferedReader(new FileReader("src/qualitaetssicherung/testdata.txt"));
		PrintWriter    pw1 = new PrintWriter("src/qualitaetssicherung/output1.txt");
		PrintWriter    pw2 = new PrintWriter("src/qualitaetssicherung/output2.txt");
		String testData;
		int firstNumber;
		int secondNumber;
		
		generateTestDatas();
		
		//Softwareversionen mit Testdaten ausführen und Ergebnisse speichern.
		while ((testData = br.readLine()) != null && testData.length() != 0) {
			String[] parts = testData.split(" ");
			firstNumber = Integer.parseInt(parts[0]);
			secondNumber = Integer.parseInt(parts[1]);
			pw1.println(swv1.isSquareRootOf(firstNumber, secondNumber));
			pw2.println(swv2.isSquareRootOf(firstNumber, secondNumber));
		}
		
		
		//Ergebnisse der Softwareversionen miteinander vergleichen
		
		
		br.close();
		pw1.close();
		pw2.close();
	}
	
	private void generateTestDatas() throws FileNotFoundException {		
		PrintWriter pw = new PrintWriter("src/qualitaetssicherung/testdata.txt");
		
		pw.println("5 -25");
		pw.println("-5 25");
		pw.println("-5 -25");
		
		//Korrekte Testdaten
		for(int i = 0; i < 20; i++) {
			Random rand = new Random();
			int minRange = 1;
			int maxRange = 1000;
			int value = rand.nextInt((maxRange - minRange) + 1) + minRange;
			pw.println(value + " " + (value * value));
		}
		
		//Zufällige Testdaten
		for(int i = 0; i < 20; i++) {
			Random rand = new Random();
			int value1 = rand.nextInt();
			int value2 = rand.nextInt();
			pw.println(value1 + " " + value2);
		}
		
		pw.close();
	}
	
}
