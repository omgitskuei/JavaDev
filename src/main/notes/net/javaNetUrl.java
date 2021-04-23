package main.notes.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class javaNetUrl {

	public static void main(String[] args) {
		
		try {
			/**
			 * URL = PROTOCOL + HOST + PATH
			 */
			
			URL url = new java.net.URL("https://www.google.com");
			System.out.println(url);					// https://www.google.com
			System.out.println("Authority:    " + url.getAuthority());		// www.google.com
			System.out.println("Class:        " + url.getClass());			// class java.net.URL
//			System.out.println(url.getContent());							// > > > > this throws an error...
			System.out.println("Default Port: " + url.getDefaultPort());	// 443
			System.out.println("File:         " + url.getFile());			// 
			System.out.println("Host:         " + url.getHost());			// www.google.com
			System.out.println("Path:         " + url.getPath());			// 
			System.out.println("Port:         " + url.getPort());			// -1
			System.out.println("Protocol:     " + url.getProtocol());		// https
			System.out.println("Query:        " + url.getQuery());			// null
			System.out.println("Ref:          " + url.getRef());			// null
			System.out.println("Info:         " + url.getUserInfo());		// null
			
			System.out.println("-------------------------------------------------------");
			
			url = new java.net.URL("https://www.baeldung.com/java-8-streams-introduction");
			System.out.println(url);					// https://www.baeldung.com/java-8-streams-introduction
			System.out.println("Authority:    " + url.getAuthority());		// www.baeldung.com
			System.out.println("Class:        " + url.getClass());			// class java.net.URL
//			System.out.println(url.getContent());							// > > > > this throws an error...
			System.out.println("Default Port: " + url.getDefaultPort());	// 443
			System.out.println("File:         " + url.getFile());			// /java-8-streams-introduction
			System.out.println("Host:         " + url.getHost());			// www.baeldung.com
			System.out.println("Path:         " + url.getPath());			// /java-8-streams-introduction
			System.out.println("Port:         " + url.getPort());			// -1
			System.out.println("Protocol:     " + url.getProtocol());		// https
			System.out.println("Query:        " + url.getQuery());			// null
			System.out.println("Ref:          " + url.getRef());			// null
			System.out.println("Info:         " + url.getUserInfo());		// null
			
			
//			InputStream iS = url.openStream();
//			System.out.println(iS);
			
//			java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A");
//			System.out.println("My current IP address is " + s.next());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
