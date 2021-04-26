package com.Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Log
{

	// Enums

	/**
	 * Log type: info, warning, error
	 */
	public enum LogType
	{
		Info, Warning, Error
	}

	//endregion

	//region Private Variables

	/**
	 * Application Name
	 */
	private static String appName;

	/**
	 * Process ID
	 */
	private static Integer processID;

	/**
	 * Debug file (only for Unix and Unix-Like)
	 */
	private static String debugFile;

	/**
	 * Is initialized
	 */
	public static boolean isInitialized = false;

	//endregion

	//region Methods

	/**
	 * Initialize ogging variables
	 *
	 * @param parAppName   Application name (optional)
	 * @param parDebugFile Debug file path (optional) (only for unix and unix-like OSes)
	 * @param parProcessID Process ID (optional)
	 */
	public static void Initialize(Optional<String> parAppName, Optional<String> parDebugFile, Optional<Integer> parProcessID)
	{
		appName = parAppName.orElse("Java");
		debugFile = parDebugFile.orElse("");
		processID = parProcessID.orElse(666);
		isInitialized = true;
	}

	/**
	 * Add log
	 *
	 * @param parMessage Log message
	 * @param parLogType Log type
	 */
	public static void add(String parMessage, LogType parLogType)
	{
		String type;

		switch (parLogType)
		{
			case Warning:
				type = "WARNING";
				break;
			case Error:
				type = "ERROR";
				break;
			default:
				type = "INFORMATION";
				break;
		}

		try
		{

			// Windows
			if (debugFile.equals(""))
			{
				Process process = Runtime.getRuntime().exec("EventCreate /t " + type + " /id " + processID.toString() + " /l APPLICATION /so " + appName + " /d \"" + parMessage + "\"");
				process.waitFor(2, TimeUnit.SECONDS);
				if (process.isAlive())
					process.destroy();
			}

			// Unix
			else
			{
				Writer output;
				File file = new File(debugFile);
				if (!file.exists())
					file.createNewFile();
				output = new BufferedWriter(new FileWriter(debugFile, true));
				output.append(LocalDateTime.now().toString() + " #" + type + " ####    " + parMessage);
				output.append("\n");
				output.close();
			}
		}
		catch (InterruptedException | IOException e)
		{
			e.printStackTrace();
		}
	}

	//endregion
}
