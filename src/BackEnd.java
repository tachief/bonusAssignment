import java.util.Random;

public class BackEnd
{
	class Result
	{
		int blackPegs;
		int whitePegs;
	}
	
	final static String HISTORY = "history";
	
	private String code = "";
	private int codeSize = 4;
	private char colors[] = {'R', 'O', 'Y', 'G', 'B', 'I', 'P'};
	
	public void generateCode()
	{
		
		String code = "";
		for(int i = 0; i < codeSize; i++)
		{
			Random rand = new Random();
			int randomInt = rand.nextInt(colors.length);
			code += colors[randomInt];
			
		}
		
		this.code = code;
		
	}
	
	public boolean isValidInput(String input)
	{
		if(!input.equals(HISTORY) && input.length() > codeSize)
			return false;
		
		return true;
	}
	
	public boolean isValidGuess(String guess)
	{
		
		if(guess.length() > codeSize)
			return false;
		for(int i = 0; i < codeSize; i++)
		{
			if(!isValidColor(guess.charAt(i)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isValidColor(char guess)
	{
		
		int check = 0;
		for(int i = 0; i < colors.length; i++)
		{
			if(guess == colors[i])
				check = 1;
		}
		if(check == 0)
			return false;
		
		return true;
	}
	
	public void calculateResult(String guess, String code)
	{
		//TODO
		int blackPegs = 0;
		int whitePegs = 0;
		char guessArray[] = guess.toCharArray();
		char codeArray[] = code.toCharArray();
		for(int i = 0; i < codeSize; i++)
		{
			if(guessArray[i] == codeArray[i])
			{
				guessArray[i] = '-';
				codeArray[i] = '-';
				blackPegs++;
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
					codeArray[i] = '-';
					whitePegs++;
					break;
				}
			}	
		}
		//Still need to return number of each peg to make result
		
		
	}
}