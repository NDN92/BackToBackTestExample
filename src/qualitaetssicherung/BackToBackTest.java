package qualitaetssicherung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
		
		pw1.println(br.readLine());
		pw2.println("Testprint2");
		
		//Softwareversionen mit Testdaten ausführen und Ergebnisse speichern.
		
		
		//Ergebnisse der Softwareversionen miteinander vergleichen
		
		
		br.close();
		pw1.close();
		pw2.close();
	}
	
}
