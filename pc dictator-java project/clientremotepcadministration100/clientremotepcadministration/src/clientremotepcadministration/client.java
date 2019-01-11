/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientremotepcadministration;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;                                
import javax.swing.JFrame;
/**
 *
 * @author User
 */
public class client extends Thread{
    static int recievefile=511,multirecievefile=250,numberofclient;
    static Socket s;
static DataInputStream din ;
 static DataOutputStream dout;
 static boolean command=false;
 static String hostname; 
 static int portid;
 client(String hostname,int portid)
 {
     this.hostname=hostname;
     this.portid=portid;
 }
 public static void writecmd(String s) throws IOException, InterruptedException
        {
            Process p = Runtime.getRuntime().exec("cmd");
            new Thread(() -> {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null){
                if(command==true){dout.writeUTF(line);
                dout.flush();
                System.err.println("command");
                }
                System.out.println(line);
            }
            if(command==true)
                dout.writeUTF("sesh");
        } catch (Exception e) {
            e.printStackTrace();        }
        }).start();
        try (PrintStream out = new PrintStream(p.getOutputStream())) {
            out.println(s);
        // .....
        }
        p.waitFor();     
        }
 /**
     * @param args the command line arguments
     */
    public void run() {

        // TODO code application logic here
        String str=" ";
        try {
            s=new Socket(hostname,portid);
            new Thread(new serverconnected()).start();
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(s);
        try {
            din=new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dout=new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writecmd("ping google.com");
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            numberofclient =din.readInt();
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    do{
            
            try {
                str=din.readUTF();
                System.err.println(str);
                if(str.equals("com"))
                {
//                    int a=1;
                    
                    System.err.println("gecebeshi");
                    command=true;
                    str=din.readUTF();
                }
                if(str.equals("sendfile"))
                {
                    System.err.println("receive e dokce");
                    int i=0;
                    recievefile receive=new recievefile(recievefile);
                    receive.done();
                    recievefile=recievefile+1000;
                }
                if(str.equals("multisendfile"))
                {
                    System.err.println("receive e dokce");
                    int i=0;
                   multirecievefile receive=new multirecievefile(multirecievefile);
                   multirecievefile=multirecievefile+numberofclient*1000;
                    receive.done();
                }
                if(str.equals("screen"))
                {
                    System.err.println("screen e dokce");
                    int i=0;
                    ClientStart c=new ClientStart();
                    c.done();
                }
                if(str.equals("fetch"))
                {
                    System.err.println("fetch e dokce");
//                    recievefile receive=new recievefile(s);
//                    receive.receive();
                      String sf=din.readUTF();
                      System.out.println("fetcher dir"+sf);
                      fetchc cl=new fetchc(sf);
                      cl.done();
                      System.err.println("sdfjs;af");

                }
                if(str.equals("message"))
                {
                    String s4;
                    s4=din.readUTF();
                    System.err.println(s4);
                    messagercv m=new messagercv(s4);
                    m.setVisible(true);
                    m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                    writecmd(str);
                dout.flush();
            } catch (InterruptedException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }while(!str.equals("stop"));
    
}
}
