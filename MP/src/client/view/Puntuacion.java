package client.view;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author k.lisowski
 */
public class Puntuacion {
    private HashMap<String, JFormattedTextField> players;
    private JPanel panel;


    public Puntuacion(){
        this.players= new HashMap<>();
        JFrame frame= new JFrame("SCORE");
        this.panel = new JPanel();
        panel.setLayout(new GridLayout(players.size(),2,10,10));

       /* JLabel label1= new JLabel("         PLAYER1");
        JLabel label2= new JLabel("         PLAYER2");
        JLabel label3= new JLabel("         PLAYER3");
        JLabel label4= new JLabel("         PLAYER4");
        JLabel label5= new JLabel("         PLAYER5");
        JFormattedTextField field1= new JFormattedTextField();
        JFormattedTextField field2= new JFormattedTextField();
        JFormattedTextField field3= new JFormattedTextField();
        JFormattedTextField field4= new JFormattedTextField();
        JFormattedTextField field5= new JFormattedTextField();
        panel.setLayout(new GridLayout(5,2,5,5));*/
      /*  panel.add(label1);
        panel.add(field1);
        panel.add(label2);
        panel.add(field2);
        panel.add(label3);
        panel.add(field3);
        panel.add(label4);
        panel.add(field4);
        panel.add(label5);
        panel.add(field5); */

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.setVisible(true);

    }


    public void setPlayersInGame(String msg)
    {
        players.put(msg, new JFormattedTextField());
      //  actualizarTabla();
        JLabel jlabel123 = new JLabel(msg);
        panel.add(jlabel123);
        panel.add(players.get(msg));
        System.out.println("Maria es una pringada");
        panel.validate();
        panel.repaint();

    }

    public HashMap<String, JFormattedTextField> getPlayers() {
        return players;
    }

    public void actualizarTabla(){
        this.panel.setLayout(new GridLayout(players.size(),2,5,5));
    }
    public static void main(String[] args){
        Puntuacion points = new Puntuacion();
    }



}