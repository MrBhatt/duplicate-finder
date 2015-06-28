package com.mrbhatt.duplicatefinder;

import com.mrbhatt.duplicatefinder.exceptions.InvalidPathException;
import com.mrbhatt.duplicatefinder.helper.FinderHelper;

/**
 * @author Anupam Bhatt
 *
 */
public class DuplicateFinder {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			String dirname = null;
			if (args.length > 0)
			{
				dirname = args[0];
			}
			else
			{
				System.out.println("Usage: java DuplicateFinder <dir name>");
				System.exit(-1);
			}
			
			FinderHelper.findDuplicates(dirname);
			System.out.println("Done");
		} 
		catch (InvalidPathException e) 
		{
			e.printStackTrace();
		}
	}

}
