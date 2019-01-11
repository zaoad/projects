/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientremotepcadministration;


import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;


public class ScreenCatcher extends Thread {

    Socket socket = null; 
    Robot robot = null; 
    Rectangle rectangle = null; 
    boolean continueLoop = true; //Used to exit the program
    
    public ScreenCatcher(Socket socket, Robot robot,Rectangle rect) {
        this.socket = socket;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    public void run(){
        ObjectOutputStream oos = null; 


        try{
            
            oos = new ObjectOutputStream(socket.getOutputStream());
            
            oos.writeObject(rectangle);
        }catch(IOException ex){
            ex.printStackTrace();
        }

       while(continueLoop){
            //Capture screen
            BufferedImage image = robot.createScreenCapture(rectangle);
            
            ImageIcon imageIcon = new ImageIcon(image);

            
            try {
                System.out.println("before sending image");
                oos.writeObject(imageIcon);
                oos.reset(); 
                System.out.println("New screenshot sent");
            } catch (IOException ex) {
               ex.printStackTrace();
            }

          
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}
