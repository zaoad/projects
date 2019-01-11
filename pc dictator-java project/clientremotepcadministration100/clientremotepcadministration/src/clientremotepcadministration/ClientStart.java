/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientremotepcadministration;





import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ClientStart {

    Socket socket = null;

    public  void done(){
//        String ip = JOptionPane.showInputDialog("Please enter server IP");
//        String port = JOptionPane.showInputDialog("Please enter server port");
            String ip=client.hostname;
            int port=999;
        int opcion = JOptionPane.showConfirmDialog(null, "are you sure to Screen with server computer?", "Screen", JOptionPane.YES_NO_OPTION);
                           if (opcion == 0) { 
                                 new ClientStart().initialize(ip, port);
                           }
                           else{
                               int a=1;
                           }
       
    }
    public void initialize(String ip, int port ){

        Robot robot = null; //Used to capture the screen
        Rectangle rectangle = null; //Used to represent screen dimensions

        try {
            System.out.println("Connecting to server ..........");
            socket = new Socket(ip, port);
            System.out.println("Connection Established.");

           
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();

         
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            robot = new Robot(gDev);

            
            drawGUI();
            
            new ScreenCatcher(socket,robot,rectangle);
           
            new ServerCommand(socket,robot);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (AWTException ex) {
                ex.printStackTrace();
        }
    }

    private void drawGUI() {
        JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("Terminate");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
      );
      frame.setVisible(true);
    }
}
