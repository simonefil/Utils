package com.Utils;

import java.io.File;

public class Dir
{

	public static String combine(String parRootDir, String... parDirs)
	{
		String finalString = "";

		finalString = parRootDir;

		for (String subDir : parDirs)
		{
			finalString += "/" + subDir;
		}

		if (File.separatorChar == '\\')
		{
			finalString = finalString.replace("/", "\\");
		}
		else
                {
			finalString = finalString.replace('\\', '/');
		}

		return finalString;
	}
}
