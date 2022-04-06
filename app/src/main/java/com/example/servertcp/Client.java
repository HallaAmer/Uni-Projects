package com.example.servertcp;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends AsyncTask<String,Void,Void> {

    Socket Socket;
    DataOutputStream DataOutStream;
    PrintWriter PrintWriter;

    @Override
    protected Void doInBackground(String... voids) {

        String message= voids[0];
        try {
            Socket = new Socket("192.168.0.105", 4000);
            PrintWriter = new PrintWriter(Socket.getOutputStream()) ;
            PrintWriter.write(message);
            PrintWriter.flush();
            Socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
