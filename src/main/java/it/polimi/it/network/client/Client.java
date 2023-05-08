package it.polimi.it.network.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, NotBoundException {

        System.out.println("Choose a connection type");

        Scanner stdin = new Scanner(System.in);
        String inputLine = stdin.nextLine();
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader( new FileReader("src/main/java/it/polimi/it/network/ServerConfig.json"));
        JsonObject jsonObject = gson.fromJson(jsonReader,JsonObject.class);

        ClientTCP clientTCP=null;
        ClientRMIApp clientRMIApp=null;


        ClientInputReader cliR = new ClientInputReader();
        cliR.setConnectionType(inputLine.toLowerCase());
        Thread thread = new Thread(cliR);
        thread.start();
    }
}
