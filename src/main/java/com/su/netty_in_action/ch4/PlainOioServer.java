package com.su.netty_in_action.ch4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {
	public void server(int port) throws IOException {
		ServerSocket socket = new ServerSocket(port);
		for (;;) {
			Socket clientSocket = socket.accept();
			System.out.println("Accepted connection from " + clientSocket);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						OutputStream out = clientSocket.getOutputStream();
						out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
						out.flush();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							clientSocket.close();
						} catch (IOException e) {
							// ignore on close
						}
					}
				}
			}).start();
		}
	}
}
