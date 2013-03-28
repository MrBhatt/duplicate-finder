/**
 * 
 */
package org.mrbhatt.duplicatefinder.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.mrbhatt.duplicatefinder.exceptions.InvalidPathException;

/**
 * @author ABy
 *
 */
public class FinderHelper 
{
	public static void findDuplicates(String pathToLocation) throws InvalidPathException
	{
		File dirPath = new File(pathToLocation);
		if ( dirPath.isFile() )
		{
			throw new InvalidPathException();
		}
		
		Map<String, String> fileNameMap = new HashMap<String, String>();
		finder(dirPath, fileNameMap);
	}
	
	private static void finder(File dirPath, Map<String, String> fileNameMap)
	{
		//System.out.println("Checking directory:" + dirPath);
		File[] fileNameList = dirPath.listFiles();
		
		for ( File fileObj:fileNameList )
		{
			if (fileObj.isDirectory())
			{
				finder(fileObj, fileNameMap);
			}
			else
			{
				try 
				{
					String fileKey = DigestUtils.sha256Hex(new FileInputStream(fileObj));
					if ( !fileNameMap.containsKey(fileKey) )
					{
						fileNameMap.put(fileKey, fileObj.getAbsolutePath());
					}
					else
					{
						// possible duplicate found
						System.out.println("   ");
						System.out.println("Possible duplicate found:");
						System.out.println("---------------------------");
						System.out.println(fileObj.getAbsolutePath());
						System.out.println(fileNameMap.get(fileKey));
						System.out.println("   ");
					}
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
