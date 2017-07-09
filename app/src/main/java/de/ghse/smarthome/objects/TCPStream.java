package de.ghse.smarthome.objects;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Thorben Auer on 01.06.2017.
 */

public class TCPStream {
    //GLOBAL ATTRIBUTES
    private Socket s;
    private Scanner input;
    private PrintWriter output;

    public TCPStream(String ip, int port) throws IOException {
        s = new Socket(ip, port);                    //Connect to server
        input = new Scanner(s.getInputStream());                //Create scanner to get answer from server
        output = new PrintWriter(s.getOutputStream());            //Create writer to send data to server
    }

    public String sendData(final String[] data) throws java.io.IOException {

        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 0; i < data.length; i++) {
                    output.println(data[i]);                   //Write data
                }
                output.flush();                            //Send data
            }
        };
        runnable.run();

        String serverAnswer = "";                                //Answer from server
        int i = 0;
        while (true)                                            //Waiting for answer in main thread!
        {
            if (input.hasNextLine())                        //Check if there is an answer
            {
                serverAnswer = input.nextLine();            //Save answer to String
                System.out.println(serverAnswer);           //Log the answer
                return serverAnswer;                        //Stop waiting for answer and return answer
            } else {
                serverAnswer = "";

                //Retry
            }
            i++;
            if(i > 10000){
                break;
            }
        }

        return serverAnswer;
    }

}