package client.view;

import java.awt.*;
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

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.setVisible(true);

    }

    public void update(String[] msg){
        this.players.get(msg[2]).setText(msg[3]);
    }

    public void setPlayersInGame(String[] msg) {
        if (!this.players.containsKey(msg[2])) {
            players.put(msg[2], new JFormattedTextField());

            JLabel jlabel123 = new JLabel("        " + msg[2]);
            panel.add(jlabel123);
            panel.add(players.get(msg[2]));
            panel.validate();
            panel.repaint();
        }else{
            if(Boolean.parseBoolean(msg[1])){
                this.deleteInfo(msg[2]);
            }else {
                this.update(msg);
            }
        }

        System.out.println(this.players.size());
    }

    public void deleteInfo(String name){
        System.out.println("Desconectando:..."+name);
        this.players.get(name).setBackground(Color.red);
    }

    public HashMap<String, JFormattedTextField> getPlayers() {
        return players;
    }


    public boolean contains(String name) {
        return this.players.containsKey(name);
    }
}