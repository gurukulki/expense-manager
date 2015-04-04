package com.appcoretech.spring.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextUtil
{
	public static ApplicationContext getApplicationContext()
	{
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		return context;
	}

	public static ServletContext getServletContext()
	{
		return getSession().getServletContext();
	}

	public static HttpSession getSession()
	{
		ServletRequestAttributes reqAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = reqAttrs.getRequest();
		HttpSession session = req.getSession();
		if (session == null)
		{
			throw new SessionAuthenticationException("Session Expired");
		}
		return session;
	}

	public static void session_put(String key, Object object)
	{
		HttpSession session = getSession();
		session.setAttribute(key, object);
	}

	public static Object session_get(String key, Object defaultValue)
	{
		HttpSession session = getSession();
		Object obj = session.getAttribute(key);
		if (obj != null)
			return obj;
		return defaultValue;
	}

	public static final String CONTEXT_TEMP_DIR = "javax.servlet.context.tempdir";

	public static final String SESSION_TEMP_DIR = "com.tdc.session.tempdir";

	public static File getContextDir()
	{
		return (File) getServletContext().getAttribute(CONTEXT_TEMP_DIR);
	}

	public static File getSessionDir()
	{
		File sessionDir = (File) session_get(SESSION_TEMP_DIR, null);
		if (sessionDir == null || !sessionDir.exists() || !sessionDir.isDirectory())
		{
			try
			{
				return createSessionDirectory();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (sessionDir == null)
			sessionDir = getContextDir();
		return sessionDir;
	}

	public static File context_putObject(String path, Serializable object)
	{
		return null;
	}

	public static File session_putObject(String key, Serializable object)
	{
		File sessionDir = getSessionDir();
		File outFile = new File(sessionDir, key);
		if (!outFile.exists())
		{
			outFile.delete();
		}
		FileOutputStream os = null;
		ObjectOutput out = null;
		try
		{
			os = new FileOutputStream(outFile, false);
			out = new ObjectOutputStream(os);
			out.writeObject(object);
			return outFile;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (out != null)
				try
				{
					out.close();
				}
				catch (IOException e)
				{}
		}
		return null;
	}

	public static Object session_getObject(String key)
	{
		File sessionDir = getSessionDir();
		File inFile = new File(sessionDir, key);
		if (inFile.exists())
		{
			FileInputStream fis = null;
			try
			{
				fis = new FileInputStream(inFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object object = ois.readObject();
				return object;
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public static File createSessionDirectory() throws IOException
	{
		File contextDir = (File) getServletContext().getAttribute(CONTEXT_TEMP_DIR);
		File sessionDir;
		final int maxAttempts = 9;
		int attemptCount = 0;
		do
		{
			attemptCount++;
			if (attemptCount > maxAttempts)
			{
				throw new IOException("The highly improbable has occurred! Failed to "
						+ "create a unique temporary directory after "
							+ maxAttempts
							+ " attempts.");
			}
			String dirName = UUID.randomUUID().toString();
			sessionDir = new File(contextDir, dirName);
		}
		while (sessionDir.exists());

		if (sessionDir.mkdirs())
		{
			session_put(SESSION_TEMP_DIR, sessionDir);
			return sessionDir;
		}
		else
		{
			throw new IOException("Failed to create temp dir named " + sessionDir.getAbsolutePath());
		}

	}

	public static void destroySessionDirectory(HttpSession session)
	{
		File sessionDir = (File) session.getAttribute(SESSION_TEMP_DIR);
		deleteFiles(sessionDir);
	}

	/**
	 * recursive function to delete the file/folder recursively
	 * 
	 * @param file
	 */
	public static void deleteFiles(File file)
	{
		if (file != null && file.exists())
		{
			if (!file.isDirectory())
			{
				file.delete();
			}
			else if (file.isDirectory())
			{
				File[] subFiles = file.listFiles();
				for (File subFile : subFiles)
				{
					deleteFiles(subFile);
				}
				file.delete();
			}
		}
	}
}
