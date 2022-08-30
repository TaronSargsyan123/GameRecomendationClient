package com.example.gamesclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientLogic {
    private final String HOST = "your host";
    private final int PORT =8080;

    public String predict(String title){

        String msg = "Games Not Found";

        try {
            Socket soc = new Socket(HOST, PORT);

            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
            DataInputStream in = new DataInputStream(soc.getInputStream());

            dout.writeUTF(title);
            System.out.println("data resented");
            msg = (String) in.readUTF();
            dout.flush();
            dout.close();
            soc.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return msg;
    }
}
