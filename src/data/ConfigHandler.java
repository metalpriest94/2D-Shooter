package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigHandler {
	public ConfigHandler() {
		
	}
	public ArrayList<String> readListFile(String fileName)
	{
		ArrayList<String> listFileData;
		BufferedReader fileReader = null;
		String currentLine;
		listFileData = new ArrayList<String>();
		try
		{
			fileReader = new BufferedReader(new FileReader(new File("config" + File.separator + fileName)));
			while((currentLine = fileReader.readLine()) != null)
			{
				listFileData.add(currentLine);
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileReader != null)
					fileReader.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return listFileData;
	}
}
