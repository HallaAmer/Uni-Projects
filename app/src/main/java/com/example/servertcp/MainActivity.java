package com.example.servertcp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    EditText Text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text1 = (EditText) findViewById(R.id.editTextTextPersonName);

        Thread MyThread = new Thread(new MyServerThread());
        MyThread.start();

    }
    class MyServerThread implements Runnable {
        Socket Socket;
        ServerSocket ServerSocket;
        InputStreamReader inputStreamReader;
        BufferedReader bufferReader;
        Handler handler = new Handler();
        String message;



        @Override
        public void run() {
            try {
                ServerSocket = new ServerSocket(4001);
                while(true){
                    Socket = ServerSocket.accept();
                    inputStreamReader = new InputStreamReader(Socket.getInputStream());
                    bufferReader = new BufferedReader(inputStreamReader);
                    message = bufferReader.readLine();
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }catch(IOException e){
             e.printStackTrace();
            }

        }
    }

    public void Send(View V){

        Client client = new Client();
        client.execute(Text1.getText().toString());


    }
}