package qualitaetssicherung;

public class Softwareversion1 {

	public boolean isSquareRootOf(int number1, int number2) {
		if(number1 < 1 || number2 < 1) return false;
		int square = (int)Math.sqrt(number2);		
		return square == number1;
	}

}
