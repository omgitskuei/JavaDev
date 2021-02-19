package main.notes.net;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class jPortConnector {
	public static void main(String[] paramArrayOfString) {
		System.out.println("- - - - jPortConnector applet, by omgitskuei - - - -");
		
		Scanner localScanner = new Scanner(System.in);
		String ipAddress;
		int portNum;
		boolean repeat = true;
		
		while (repeat) {
			try {
				System.out.print("Enter IP Address: ");
				ipAddress = localScanner.next();
				System.out.print("Enter Port:       ");
				portNum = Integer.parseInt(localScanner.next());

				Socket localSocket = new Socket(ipAddress, portNum);
				
				localSocket.close();
				
				System.out.println(
					"Connection to [" + InetAddress.getByName(ipAddress).getHostAddress() + ":" + portNum + "] Success!");
			} catch (ConnectException connE) {
				System.out.println("Connection Failed/Rejected! Possibly by firewall.");
			} catch (NumberFormatException e) {
				System.out.println("Please type Numbers only for Port.");
			} catch (Exception e) {
				System.out.println("Connection failed with "+e.getClass()+",\r\n" + "StackTrace:");
				e.printStackTrace();
			} finally {
				System.out.print("More tests (Y/N)? ");
				repeat = localScanner.next().equals("N") ? false : true;
				System.out.println("");
			}
		}
		
		localScanner.close();
		System.out.println("- - - - jPortConnector applet, by omgitskuei - - - -");
	}
}
