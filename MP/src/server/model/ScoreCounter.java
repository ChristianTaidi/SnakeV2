package server.model;

import java.util.Observable;
import java.util.TreeMap;

public class ScoreCounter extends Observable{

    private TreeMap<String,Integer> scores;

    public ScoreCounter(){
        this.scores= new TreeMap<>();
    }

    public void addClient(String name, int score){
        this.scores.put(name,score);
        notifyObservers();
    }

    public boolean deleteClient (int id){
        if (this.scores.containsKey(id)) {
            this.scores.remove(id);
            notifyObservers();
            return true;
        }else{
            return false;
        }
    }

    public boolean updateScore(String name){
        if (this.scores.containsKey(name)) {
            this.scores.replace(name, this.scores.get(name)+100);
            notifyObservers();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        String str= "PTS; " + String.valueOf(scores.size())+";";
        for(String client:this.scores.keySet()){
            str = str + client + ";" + this.scores.get(client) + ";" ;
        }
        return str;
    }

}
