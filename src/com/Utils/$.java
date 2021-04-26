package com.Utils;

import java.util.Objects;

public class $<T>
{

	public T Value = null;

	public $(T parValue)
	{
		this.Value = parValue;
	}

	public $()
	{
	}

	public T get()
	{
		return this.Value;
	}

	public void set(T parValue)
	{
		this.Value = parValue;
	}

	public $<T> ref()
	{
		return this;
	}

	public $<T> out()
	{
		this.Value = null;
		return this;
	}

	@Override
	public String toString()
	{
		if (this.Value == null)
			return "";
		else
			return this.Value.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		return Objects.equals(this.Value, obj);
	}

	@Override
	public int hashCode()
	{
		return this.Value.hashCode();
	}

	public boolean isNull()
	{
		return (this.Value == null);
	}
}
