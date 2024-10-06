package dev.pschmalz.clean_architecture_demo_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import jakarta.json.bind.JsonbBuilder;

public class Application {
	public static void main(String[] args) throws UnknownHostException, IOException {
		var clientSocket = new Socket("localhost", 8080);
		var out = new OutputStreamWriter(clientSocket.getOutputStream());
		var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		var jsonb = JsonbBuilder.create();
		
		var message = new Message();
		
		message.setSource("player");
		
		out.write(jsonb.toJson(message) + "\n");
		out.flush();
		
		
		try {
			System.out.println("BEFORE SLEEP");
			Thread.sleep(10000);
			System.out.println("AFTER SLEEP");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.write(jsonb.toJson(message) + "\n");
		
		System.out.println("HERE");
		clientSocket.close();
		in.close();
		
	}
}
