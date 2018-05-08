package server.model;



import java.util.*;

public class Snake extends Observable {


    private int id;
    private String name;

    private String mov;

    private int length;

    private Node head;
    private Queue<Node> snake;

    private Node lastTail;

    private ScoreCounter scores;

    public Snake(ScoreCounter counter,int id){
        this.snake= new LinkedList<Node>();
        this.lastTail= new Node(46,45);
        this.head = new Node(45,45);

        this.snake.add(this.lastTail);
        this.snake.add(this.head);

        this.id = id;
        this.scores = counter;
    }

    public void move(String check){

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
        if(check.equals("keep")){

            this.lastTail = this.snake.peek();

        }if(check.equals("poll")){

            this.lastTail = this.snake.poll();

        }else{
            System.out.println("Error");
        }

        this.snake.add(aux);
        this.head = aux;
        setChanged();
        notifyObservers();
    }

    public void addScore(){

        this.scores.updateScore(this.name);
        this.length += 1;
    }

    public void setMov(String mov){
        this.mov = mov;

    }

    public int getX() {
        return this.head.getX();
    }


    public int getY() {
        return this.head.getY();
    }


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
}
