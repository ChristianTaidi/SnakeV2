package server.model;

import java.util.Observable;
import java.util.TreeMap;

public class ScoreCounter extends Observable{

    private String lastUpdated;
    private TreeMap<String,Integer> scores;
    private boolean delete;

    public ScoreCounter(){
        this.scores= new TreeMap<>();
    }

    public void addClient(String name, int score){
        this.scores.put(name,score);
        this.lastUpdated=name;
        this.delete=false;
        setChanged();
        notifyObservers();
    }

    public boolean deleteClient (String name){
        if (this.scores.containsKey(name)) {
            this.scores.remove(name);
            this.lastUpdated=name;
            this.delete=true;
            setChanged();
            notifyObservers();
            return true;
        }else{
            return false;
        }
    }

    public boolean updateScore(String name){
        if (this.scores.containsKey(name)) {
            this.scores.replace(name, this.scores.get(name)+100);
            this.lastUpdated=name;
            this.delete=false;
            setChanged();
            notifyObservers();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        String str= "PTS; " + String.valueOf(scores.size())+";";
        str+=this.delete+";"+this.lastUpdated+";"+this.scores.get(lastUpdated);
        return str;
    }

}
