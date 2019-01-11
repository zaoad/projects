/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientremotepcadministration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class multirecievefile {
//    private static final int PORT = 51111;
    
    private static final int CHUNK_SIZE = 1024;
    private static final File _downloadDir = new File("D:\\mainlast\\New folder\\clientremotepcadministration100\\clientremotepcadministration");
        private static final String HOSTNAME = client.hostname;
   private static int PORT;
   multirecievefile(int PORT)
   {
       this.PORT=PORT;
   }
   
    public  void done() {
        if (!_downloadDir.exists()) {
            if (!_downloadDir.mkdirs()) {
                System.err.println("Error: Could not create download directory");
            }
        }

        Socket socket = null;
        try {
            //ServerSocket server = new ServerSocket(PORT);

            
                System.out.println("Waiting for connection...");
                socket = new Socket(HOSTNAME, PORT);
//                Socket socket = null;
    
               

DataInputStream dis = new DataInputStream(socket.getInputStream());
String name = dis.readUTF();
File file = new File(_downloadDir, name);
long fileSize = dis.readLong();
System.out.println("Saving " + file + " from user... ("
        + fileSize + " bytes)");

//                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//                String name = in.readLine();
//                File file = new File(_downloadDir, name);
//
//                String size = in.readLine();
//                int fileSize;
//                try {
//                    fileSize = Integer.parseInt(size);
//                } catch (NumberFormatException e) {
//                    System.err.println("Error: Malformed file size:" + size);
//                    e.printStackTrace();
//                    return;
//                }

                System.out.println("Saving " + file + " from user... (" + fileSize + " bytes)");
                saveFile(file, socket.getInputStream());
                System.out.println("Finished downloading " + file + " from user.");
                if (file.length() != fileSize) {
                    System.err.println("Error: file incomplete");
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

    private static void saveFile(File file, InputStream inStream) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);

            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            int pos = 0;
            while ((bytesRead = inStream.read(buffer, 0, CHUNK_SIZE)) >= 0) {
                pos += bytesRead;
                System.out.println(pos + " bytes (" + bytesRead + " bytes read)");
                fileOut.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Finished, filesize = " + file.length());
        
    }
}