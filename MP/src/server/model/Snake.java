package server.model;



import server.exceptons.AutoHitException;

import java.util.*;

/**
 * Class that contains the data of the snake, such as name, movement direction, length, position of the head, collection of all the nodes that form the snake, the tail of the snake
 * and all the scores of every player
 */
public class Snake extends Observable {


    private int id;
    private String name;

    private String mov;

    private int length;

    private Node head;
    private Queue<Node> snake;

    private Node lastTail;

    private ScoreCounter scores;

    /**
     * initializes the fields
     * @param counter
     * @param id
     */
    public Snake(ScoreCounter counter,int id){
        this.snake= new LinkedList<Node>();
        this.lastTail= new Node(46,45);
        this.head = new Node(45,45);
        this.mov="none";

        this.snake.add(this.lastTail);
        this.snake.add(this.head);

        this.id = id;
        this.scores = counter;
    }

    /**
     * moves the snake depending on the mov field
     * if the check string is KEEP, the snake grows
     * if not the snake continues
     * @param check
     * @throws AutoHitException
     */
    public void move(String check) throws AutoHitException {

        Node aux;
        switch (this.mov){
            case "UP":
                    aux = this.head.increaseX();
                break;
            case "DWN":
                    aux = this.head.decreaseX();
                break;
            case "LFT":
                    aux = this.head.decreaseY();
                break;
            case "RGT":
                    aux = this.head.increaseY();
                break;
            default:
                aux=new Node(0,0);
                System.out.println("Error");

        }
        if(!this.snake.contains(aux)){
            if(check.equals("keep")){

                this.lastTail = this.snake.peek();

            }if(check.equals("poll")){

                this.lastTail = this.snake.poll();

            }else{
                System.out.println("Error");
            }
        }else{
            throw new AutoHitException("FIN;Fin de la partida");
        }


        this.snake.add(aux);
        this.head = aux;
        setChanged();
        notifyObservers();
    }

    /**
     * increases the score of the instance in the score counter
     */
    public void addScore(){

        this.scores.updateScore(this.name);
        this.length += 1;
    }

    /**
     * changes the mov field, depending on the current mov, it updates or nor ex: if mov is up, it cant go down
     * @param mov
     */
    public void setMov(String mov){

        if(mov.equals("UP")){
            if (!this.mov.equals("DWN")){
                this.mov=mov;
            }

        }if(mov.equals("LFT")){
            if (!this.mov.equals("RGT")){
                this.mov=mov;
            }
        }if(mov.equals("RGT")){
            if (!this.mov.equals("LFT")){
                this.mov=mov;
            }
        }if(mov.equals("DWN")){
            if (!this.mov.equals("UP")){
                this.mov=mov;
            }
        }


    }

    public int getX() {
        return this.head.getX();
    }


    public int getY() {
        return this.head.getY();
    }

    /**
     * method that transforms to string the positions of the head and tail of the snake
     * @return
     */
    @Override
    public String toString() {
        return "MOV; " + this.id + ";" + this.getX() + ";" + this.getY() + ";" + this.lastTail.getX() + ";" + this.lastTail.getY();
    }

    public Node getLastTail() {
        return lastTail;
    }


    public void setName(String name) {
        this.name = name;
        this.scores.addClient(this.name,0);
    }



    public Node getTail() {
        return this.snake.peek();
    }

    public void delete() {
        this.scores.deleteClient(this.name);
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
