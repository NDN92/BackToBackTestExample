package qualitaetssicherung;

public class Softwareversion1 {

	public boolean isSquareRootOf(int rootValue, int radicand) {
		if(rootValue < 1 || radicand < 1) return false;
		int calculatedRootValue = (int)Math.sqrt(radicand);		
		return calculatedRootValue == rootValue;
	}

}
