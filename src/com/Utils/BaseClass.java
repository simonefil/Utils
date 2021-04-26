package com.Utils;

public abstract class BaseClass
{

	public boolean hasError;
	public String errorDescription;

	public BaseClass()
	{
		this.hasError = false;
		this.errorDescription = "";
	}

	protected void setError(String parMessage)
	{
		this.hasError = true;
		this.errorDescription = parMessage;
		if (Log.isInitialized)
			Log.add(parMessage, Log.LogType.Error);
	}
}
