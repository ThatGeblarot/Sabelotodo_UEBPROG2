package co.edu.unbosque.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class Client {

	private DatagramSocket socket;
	private InetAddress address;
	private byte[] buf;


	public void run() throws IOException {
		Scanner sc= new Scanner(System.in); //System.in is a standard input stream
		System.out.print("Enter a string: ");
		String str= sc.nextLine();              //reads string
		sendEcho(str);
	}

	public String sendEcho(String msg) throws IOException {
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
		buf = msg.getBytes();
		DatagramPacket packet
				= new DatagramPacket(buf, buf.length, address, 8888);
		socket.send(packet);

		Scanner sc= new Scanner(System.in); //System.in is a standard input stream
		System.out.print("Presione lo que le de la gana para continuar leyendo ");
		String str= sc.nextLine();

		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(
				packet.getData(), 0, packet.getLength());
		System.out.println(received);
		return received;
	}

	public void close() {
		socket.close();
	}

}
