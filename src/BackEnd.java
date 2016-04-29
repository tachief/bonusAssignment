import java.util.Random;

public class BackEnd
{
	//'E' => light grey
	//'K' => pink
	final static char colors[] = {'R', 'O', 'Y', 'G', 'B', 'P', 'E', 'K'};
	
	public static char[] code;
	
	/**
	 * Generates random code based off inputs
	 * @param codeSize how big the code is
	 * @param colorSize highest amount of colors code will have
	 */
	public static void generateCode(int codeSize, int colorSize)
	{
		
		char[] code = new char[codeSize];
		for(int i = 0; i < codeSize; i++)
		{
			Random rand = new Random();
			int randomInt = rand.nextInt(colorSize);
			code[i] = colors[randomInt];
		}
		BackEnd.code = code;
	}
	
	/**
	 * Algorithm to calculate how many black and white pegs result from guess
	 * @param guessArray the guess the user has made
	 * @return char[] with 'B'(s) and 'W'(s)
	 */
	public static char[] calculateResult(char guessArray[])
	{
		//TODO
		int codeSize = code.length;
		char[] result = new char[code.length];
		char[] codeArray = new char[code.length];
		for(int i = 0; i < code.length; i++)
		{
			codeArray[i] = code[i];
		}
		
		int index = 0;
		
		for(int i = 0;  i < code.length; i++){
			result[i] = 'X';	//avoids null pointer exception
		}
		//each guess peg can only generate one black or white peg
		//Calculates black pegs (pegs correct in position and color)
		for(int i = 0; i < codeSize; i++)
		{
			if(guessArray[i] == codeArray[i])
			{
				guessArray[i] = '-';
				codeArray[i] = '-';
				result[index] = 'B';
				index++;
			}
		}
		//Calculates white pegs (pegs correct in color only)
		for(int i = 0; i < codeSize; i++)
		{
			if(guessArray[i] == '-')
			{
				continue;
			}
			for(int j = 0; j < codeSize; j++)
			{
				if(guessArray[i] == codeArray[j])
				{
					guessArray[i] = '-';
					codeArray[j] = '-';
					result[index] = 'W';
					index++;
					break;
				}
			}	
		}
		
		return result;
	}
	
	
}