/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverremotepcadministration;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFileChooser;

public class sender {
//    private static final String HOSTNAME = "192.168.1.105";
//    private static final int PORT = 51111;
    sender(int PORT)
    {
        this.PORT=PORT;
    }
    private static int PORT ;
    private static final int CHUNK_SIZE = 1024;
    private static File target_file;
//    public static void done() {
////        JFileChooser jfc = new JFileChooser();
////        int dialog_value = jfc.showOpenDialog(null);
////        if (dialog_value == JFileChooser.APPROVE_OPTION) {
////        File target_file = jfc.getSelectedFile();
//        sendFile(target_file);
////    }
//    }

    public  void sendFile(File  file) {
    if (file == null) {
        throw new NullPointerException("Path is null");
    }

    //File file = new File(path);
    Socket socket = null;
    try {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Connecting to server...");
        socket = server.accept();
        
        System.out.println("Connected to server at "
                + socket.getInetAddress());

        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));) {
            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            System.out.println("Sending " + file.getName() + " ("
                    + file.length() + " bytes) to server...");
            writeFile(file, dos);
            System.out.println("Finished sending " + file.getName()
                    + " to server");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    
}

    private static void writeFile(File file, OutputStream outStream) {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream(file);
            byte[] buffer = new byte[CHUNK_SIZE];
            int pos = 0;
            int bytesRead;
            while ((bytesRead = reader.read(buffer, 0, CHUNK_SIZE)) >= 0) {
                outStream.write(buffer, 0, bytesRead);
                outStream.flush();
                pos += bytesRead;
                System.out.println(pos + " bytes (" + bytesRead + " bytes read)");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while writing " + file.toString() + " to output stream");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}