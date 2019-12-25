import java.util.*;
import java.io.*;

public class AnagramDecoder
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader dictionary = new BufferedReader(new FileReader(args[0]));
		ArrayList<String> lookUpTable = new ArrayList<String>();
		while (dictionary.ready())
		{
			String dWord = dictionary.readLine();
			lookUpTable.add(dWord);
		}
		dictionary.close();
		ArrayList<Character> word = new ArrayList<Character>();
		

		
		String input= (String)args[1];

		System.out.println("Deciphering "+ input+ ".... \n");
		for (int i = 0; i<input.length(); i++)
		{
			if (input.charAt(i)!=' ') word.add(input.charAt(i));
		}
		ArrayList<String> waitList = new ArrayList<String>();

		waitList = getMatches(lookUpTable, waitList, word, true);		//put all possible matches from the dictionary into waitList

		ArrayList<String> result = new ArrayList<String>();
		showResult(waitList, result, word);		//output the final result by deciphering waitList
	}
	
	public static ArrayList<String> getMatches (ArrayList<String> lookUpTable, ArrayList<String> waitList, ArrayList<Character> word, boolean found)
	{
		int numOfWords = lookUpTable.size();
		for (int i = 0; i<numOfWords; i++)		
		{
			String w = lookUpTable.get(i);
			
			ArrayList<Character> tempWord = getCharCopy(word);


			for (int j = 0; j<w.length(); j++)
			{
				if (!tempWord.contains(w.charAt(j)))
				{
					found = false;
					break;
				}
				else
				{
					removeChar(tempWord, w.charAt(j));
				} 
			}
			if (found) 
			{
				waitList.add(w);
			}
			found = true;
		}
		return waitList;
	}

	public static ArrayList<Character> getCharCopy (ArrayList<Character> template)	//this function is made to Copy an ArrayList and return it
																					//because passing an ArrayList
																					//to functions only passes its reference which is not 
																					//what I expected.
	{
		ArrayList<Character> newArray = new ArrayList<Character>();
		for (int i = 0; i<template.size(); i++)
		{
			newArray.add(template.get(i));
		}
		return newArray;
	}
	public static ArrayList<String> getStrCopy (ArrayList<String> template)			//Same as getCharCopy
	{
		ArrayList<String> newArray = new ArrayList<String>();
		for (int i = 0; i<template.size(); i++)
		{
			newArray.add(template.get(i));
		}
		return newArray;
	}
	public static void removeChar(ArrayList<Character> word, char element)
	{
		for (int i = 0; i<word.size(); i++)
		{
			if(word.get(i)==element)
			{
				word.remove(i);
				return;
			}
		}
		return;
	}

	public static void removeStr(ArrayList<String> word, String element)
	{
		for (int i = 0; i<word.size(); i++)
		{
			if(word.get(i)==element)
			{
				word.remove(i);
				return;
			}
		}
		return;
	}

	public static void showResult(ArrayList<String> waitList, ArrayList<String> result, ArrayList<Character> word)
	{
		for (int i = 0; i<waitList.size(); i++)
		{
			ArrayList<String> waitList_copy = getStrCopy(waitList);
			ArrayList<String> result_copy = getStrCopy(result);
			ArrayList<Character> word_copy = getCharCopy(word);
			String w = waitList_copy.get(i);
			boolean found = true;
			for (int j = 0; j<w.length(); j++)
			{
				if (word_copy.contains(w.charAt(j)))
					removeChar(word_copy, w.charAt(j));
				else
				{
					found = false;
					break;
				}			
			}
			if (found)
			{
				removeStr(waitList_copy, w);
				result_copy.add(w);
				if(word_copy.isEmpty())
				{
					System.out.println(result_copy);
					return;
				}
				showResult(waitList_copy, result_copy, word_copy);
			}
		}
		return;
	}
}