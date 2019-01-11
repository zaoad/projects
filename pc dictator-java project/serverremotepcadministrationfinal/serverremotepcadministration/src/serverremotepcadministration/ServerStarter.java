/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverremotepcadministration;

/**
 *
 * @author Tauhid Tanjim
 */

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the entry class of the server
 */
public class ServerStarter extends Thread{
    //Main server frame
    private JFrame frame = new JFrame();
    //JDesktopPane represents the main container that will contain all
    //connected clients' screens
    private JDesktopPane desktop = new JDesktopPane();

    public void run(){
        String port = JOptionPane.showInputDialog("Please enter listening port");
        new ServerStarter().initialize(Integer.parseInt(port));
    }

    public void initialize(int port){

        try {
            ServerSocket sc = new ServerSocket(port);
           
            drawGUI();
          
            while(true){
                Socket client = sc.accept();
                System.out.println("New client Connected to the server");
           
                new ClientStarter(client,desktop);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Draws the main server GUI
     */
    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
