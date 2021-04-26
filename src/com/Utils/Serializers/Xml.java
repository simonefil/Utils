package com.Utils.Serializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;

public class Xml
{
	private static final Xml DEFAULT_SERIALIZER;

	static
	{
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

		DEFAULT_SERIALIZER = new Xml(xmlMapper);
        }

	public static Xml serializer()
	{
		return DEFAULT_SERIALIZER;
	}

	private final ObjectMapper xmlMapper;
	private final ObjectWriter xmlWriter;
	private final ObjectWriter xmlPrettyWriter;

	private Xml(XmlMapper parMapper)
	{
		this.xmlMapper = parMapper;
		this.xmlWriter = parMapper.writer();
		this.xmlPrettyWriter = parMapper.writerWithDefaultPrettyPrinter();
	}

	public ObjectMapper xmlMapper()
	{
		return xmlMapper;
	}

	public ObjectWriter xmlWriter()
	{
		return xmlWriter;
	}

	public ObjectWriter xmlPrettyWriter()
	{
		return xmlPrettyWriter;
	}

	public <T> T fromXml(byte[] parBytes, Class<T> parClass)
	{
		try
		{
			return xmlMapper.readValue(parBytes, parClass);
		}
		catch (IOException e)
		{
			throw new XmlException(e);
		}
	}

	public <T> T fromObject(Object parObj, Class<T> parClass)
	{
		try
		{
			return xmlMapper.readValue(toString(parObj), parClass);
		}
		catch (IOException e)
		{
			throw new XmlException(e);
		}
	}

	public <T> T fromInputStream(InputStream parStream, Class<T> parClass)
	{
		try
		{
			return xmlMapper.readValue(parStream, parClass);
		}
		catch (IOException e)
		{
			throw new XmlException(e);
		}
	}

	public String toString(Object parObj)
	{
		try
		{
			return xmlWriter.writeValueAsString(parObj);
		}
		catch (IOException e)
		{
			throw new XmlException(e);
		}
	}

	public String toPrettyString(Object parObj)
	{
		try
		{
			return xmlPrettyWriter.writeValueAsString(parObj);
		}
		catch (IOException e)
		{
			throw new XmlException(e);
		}
	}

	public byte[] toByteArray(Object parObj)
	{
		try
		{
			return xmlPrettyWriter.writeValueAsBytes(parObj);
		}
		catch (IOException e)
		{
			throw new XmlException(e);
		}
	}

	public static class XmlException extends RuntimeException
	{
		private XmlException(Exception ex)
		{
			super(ex);
		}
	}
}
