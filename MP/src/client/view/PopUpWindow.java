package client.view;



import javax.swing.*;
import java.awt.*;

import static java.awt.Font.ROMAN_BASELINE;

public class PopUpWindow {
    private JFrame frame = new JFrame();

    public PopUpWindow() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 100, 350, 150);
        Container container = frame.getContentPane();
        container.setLayout(null);

        JLabel aviso = new JLabel("Introduzca el nombre de su jugador");
        aviso.setSize(100,100);
        aviso.setBounds(65,15,250,50);
        JFormattedTextField jtext = new JFormattedTextField();
        jtext.setBounds(65,50,200,30);
        container.add(aviso);
        container.add(jtext);
        frame.setVisible(true);
    }

    public static  void main(String []args){
        PopUpWindow pop = new PopUpWindow();
    }
}
