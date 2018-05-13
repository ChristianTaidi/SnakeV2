package server.model;

import java.util.Observable;
import java.util.TreeMap;


/**
 * class used as a shared memory instance for all the snakes, everyone connected to the server sees this scores, also is an observable that notifies the snake controller instances
 * lastUpdated: last player that disconnected, joined, or got a treasure
 * scores: map with all the scores indexed by the name of the player
 * delete: state to notify the client if the info should be deleted or just updated
 */
public class ScoreCounter extends Observable{

    private String lastUpdated;
    private TreeMap<String,Integer> scores;
    private boolean delete;

    /**
     * initializes the scores
     */
    public ScoreCounter(){
        this.scores= new TreeMap<>();
    }

    /**
     * adds a client by its name and score, sets the delete field at false
     * @param name
     * @param score
     */
    public void addClient(String name, int score){
        this.scores.put(name,score);
        this.lastUpdated=name;
        this.delete=false;
        setChanged();
        notifyObservers();
    }

    /**
     * deletes a client indexed by its name, sets the delete field at true
     * @param name
     * @return
     */
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


    /**
     * updates the score of an existing client, indexed by its name it replaces the score for a new one increased by 100
     * @param name
     * @return
     */
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

    /**
     * method that returns a string with the last updated player´s information
     * @return
     */
    @Override
    public String toString(){
        String str= "SCR;";

        str+=String.valueOf(this.delete)+";"+this.lastUpdated+";"+this.scores.get(lastUpdated);
        return str;
    }

}
