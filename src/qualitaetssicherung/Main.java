package qualitaetssicherung;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Softwareversion1 swv1 = new Softwareversion1();
		Softwareversion2 swv2 = new Softwareversion2();
		
		BackToBackTest b2bt = new BackToBackTest(swv1, swv2);
		
		b2bt.run();
	}

}
