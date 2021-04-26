package com.Utils;

import java.io.IOException;
import java.util.Locale;

public class OSInfo
{

	public enum OS
	{
		WINDOWS, LINUX, FREEBSD, UNIX, SOLARIS, MAC, OTHER;

		private String version;

		public String getVersion()
		{
			return version;
		}

		public void setVersion(String version)
		{
			this.version = version;
		}
	}

	private static OS os = OS.OTHER;

	static
	{
		try
		{
			String osName = System.getProperty("os.name");
			if (osName == null)
			{
				throw new IOException("os.name not found");
			}
			osName = osName.toLowerCase(Locale.ENGLISH);
			if (osName.contains("windows"))
			{
                            os = OS.WINDOWS;
			}
			else if (osName.contains("freebsd"))
			{
				os = OS.FREEBSD;
			}
			else if (osName.contains("linux"))
			{
				os = OS.LINUX;
			}
			else if (osName.contains("mpe/ix") || osName.contains("irix") || osName.contains("digital unix") || osName.contains("unix") || osName.contains("hp-ux") || osName.contains("aix"))
			{
				os = OS.UNIX;
			}
			else if (osName.contains("mac os"))
			{
				os = OS.MAC;
			}
			else if (osName.contains("sun os") || osName.contains("sunos") || osName.contains("solaris"))
			{
				os = OS.SOLARIS;
			}
			else
			{
				os = OS.OTHER;
			}
		}
		catch (Exception ex)
		{
			os = OS.OTHER;
		}
		finally
		{
			os.setVersion(System.getProperty("os.version"));
		}
	}

	public static OS getOs()
	{
		return os;
	}
}

