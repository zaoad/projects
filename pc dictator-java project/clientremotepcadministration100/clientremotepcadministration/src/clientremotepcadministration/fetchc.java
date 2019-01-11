/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientremotepcadministration;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class fetchc {
    static String fst;
    fetchc(String fst)
    {
        this.fst=fst;
    }
    public void done() throws IOException {
        Socket socket = null;
        String host = client.hostname;

        socket = new Socket(host, 4444);

//        File file = new File("C:\\Users\\Hp\\Desktop\\Sharing\\output1.txt");
        File file = new File(fst);

        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
    }
}
