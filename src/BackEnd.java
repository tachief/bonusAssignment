import java.awt.Color;
import java.util.Random;

public class BackEnd
{
	//'E' => grey
	//'K' => pink
	final static char colors[] = {'R', 'O', 'Y', 'G', 'B', 'P', 'E', 'K'};
	
	private char[] code;
	
	public void generateCode(int codeSize, int colorSize)
	{
		
		char[] code = new char[codeSize];
		for(int i = 0; i < codeSize; i++)
		{
			Random rand = new Random();
			int randomInt = rand.nextInt(colorSize);
			code[i] = colors[randomInt];
		}
		this.code = code;
	}
	
//	public boolean isValidInput(String input)
//	{
//		if(input.length() > codeSize)
//			return false;
//		
//		return true;
//	}
//	
//	public boolean isValidGuess(String guess)
//	{
//		
//		if(guess.length() > codeSize)
//			return false;
//		for(int i = 0; i < codeSize; i++)
//		{
//			if(!isValidColor(guess.charAt(i)))
//			{
//				return false;
//			}
//		}
//		
//		return true;
//	}
//	
//	public boolean isValidColor(char guess)
//	{
//		
//		int check = 0;
//		for(int i = 0; i < colors.length; i++)
//		{
//			if(guess == colors[i])
//				check = 1;
//		}
//		if(check == 0)
//			return false;
//		
//		return true;
//	}
	
	/*
	 * TODO: I will filling a char array called hints
	 * 'B' => black peg
	 * 'W' => white peg
	 * arguments: guess array, hints array, size, color amount
	 */
	
	
	public char[] calculateResult(char guessArray[])
	{
		//TODO
		int codeSize = code.length;
		char[] result = new char[code.length];
		char[] codeArray = this.code;
		int index = 0;

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