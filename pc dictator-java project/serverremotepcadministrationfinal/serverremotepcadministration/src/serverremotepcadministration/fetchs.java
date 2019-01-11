/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverremotepcadministration;


import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

public class fetchs {
    
    public void done() throws IOException {
        
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        DataOutputStream dout1=null;
        try {
            socket = serverSocket.accept();
            dout1=new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }
        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }
        try {
            out = new FileOutputStream("D:\\mainlast\\New folder\\serverremotepcadministrationfinal\\serverremotepcadministration");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
        
    }
}