package de.ghse.smartlife.objects;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class TCPStream {
    private Scanner input;
    private PrintWriter output;

    /**
     * Call when TCPStream was created
     *
     * @param ip   server ip to send data
     * @param port server port to send data
     */
    public TCPStream(String ip, int port) throws IOException {
        Socket s = new Socket(ip, port);
        input = new Scanner(s.getInputStream());                //Create scanner to get answer from server
        output = new PrintWriter(s.getOutputStream());            //Create writer to send data to server
    }

    /**
     * Send data to Server
     *
     * @param data data to send to server
     */
    public int sendData(final String[] data) throws java.io.IOException {

        Runnable runnable = new Runnable() {
            public void run() {
                for (String aData : data) {
                    output.println(aData);                   //Write data
                }
                output.flush();                            //Send data
            }
        };
        runnable.run();

        int serverAnswer = 0;                                //Answer from server
        int i = 0;
        while (true)                                            //Waiting for answer in main thread!
        {
            if (input.hasNextLine())                        //Check if there is an answer
            {
                serverAnswer = Integer.parseInt(input.nextLine());


                System.out.println(serverAnswer);           //Log the answer
                return serverAnswer;                        //Stop waiting for answer and return answer
            } else {
                serverAnswer = 0;

                //Retry
            }
            i++;
            if (i > 5000) {
                break;
            }
        }

        return serverAnswer;
    }

}