package com.Utils.Streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

public class PipeIO implements Callable<Boolean>
{
	InputStream in;
	OutputStream out;

	public PipeIO(InputStream parIn, OutputStream parOut)
	{
		this.in = parIn;
		this.out = parOut;
	}

	@Override
	public Boolean call() throws IOException
	{
		boolean ok = false;
		try
		{
			byte[] buffer = new byte[8192];
			while (true)
			{
				int bytesRead = this.in.read(buffer, 0, 8192);
				if (bytesRead == -1)
					break;
				this.out.write(buffer, 0, bytesRead);
			}
			ok = true;
		}
		catch (Exception e)
		{
			throw new IOException(e);
		}
		finally
		{
			this.in.close();
			this.out.close();
		}

		return ok;
	}
}
