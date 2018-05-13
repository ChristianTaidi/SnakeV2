package server.model;

/**
 * class that represents a node, needs 2 coords, so they can be sent to the client and printed
 */
public class Node {

    private int x;
    private int y;

    public Node(int x, int y){

        this.x=x;
        this.y=y;

    }

    public void swapNode(Node n){
        this.x=n.getX();
        this.y=n.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node increaseX() {
        return new Node(this.getX()+1,this.getY());
    }

    public Node decreaseX() {
        return new Node(this.getX()-1,this.getY());
    }

    public Node decreaseY() {
        return new Node(this.getX(),this.getY()-1);
    }

    public Node increaseY() {
        return new Node(this.getX(),this.getY()+1);
    }

    public void setPos(int x, int y) {
        this.setY(y);
        this.setX(x);
    }

    @Override
    public boolean equals(Object n){
        Node nod =(Node) n;
        return (this.getX() == nod.getX() && this.getY()==nod.getY() );
    }
}
