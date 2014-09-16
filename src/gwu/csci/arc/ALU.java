package gwu.csci.arc;

import gwu.csci.arc.utility.Converter;

public class ALU {
	private char[] cc = new char[4];
	
	/**
	 * do addition
	 * @param op1
	 * @param op2
	 * @param result
	 * @return 
	 */
	public int addition(char[] op1, char[] op2, int len, char result[]) {
		//char[] result = new char[IntegratedCircuit.getLenWord()];
//		int len = IntegratedCircuit.getLenAddr();
		int p1 = Converter.addrConveterS2I(op1, len);
		int p2 = Converter.addrConveterS2I(op2, len);
		Converter.addrConverterI2S(p1+p2, result);
		return 0;
	}
	
	/**
	 * do subtraction
	 * @param op1
	 * @param op2
	 * @param result
	 * @return
	 */
	public int subtraction(char[] op1, char[] op2, int len, char result[]) {
//		int len = IntegratedCircuit.getLenAddr();
		int p1 = Converter.addrConveterS2I(op1, len);
		int p2 = Converter.addrConveterS2I(op2, len);
		Converter.addrConverterI2S(p1-p2, result);
		
		return 0;
	}
}
 