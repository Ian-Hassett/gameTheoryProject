import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Node implements MatrixComponet{
    public ArrayList<Integer> payoffs = new ArrayList<>();
    public ArrayList<Node> followers = new ArrayList<>();
    public boolean isStart = false;
    private Point location = new Point();
    public String name;
    private Infoset infoset;
    static UI ui;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isStart() {
        return isStart;
    }
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }
    public Node(String name){
        this.name = name;
        isStart = false;
        App.ui.addNode(this);
    }
    public Node(boolean isStart){
        if (isStart)
            this.name = "start";
        else{
            this.name = super.toString();
        }
        this.isStart = isStart;
        App.ui.addNode(this);
    }
    public Node(String name, boolean isStart){
        this(name);
        this.isStart = isStart;
    }
    public Node(String name, int p1, int p2){
        this(name);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(p1);
        arr.add(p2);
        setPayoffs(arr);
    }

    public ArrayList<Integer> getPayoffs() {
        return payoffs;
    }
    public void setPayoffs(ArrayList<Integer> payoffs) {
        this.payoffs = payoffs;
    }
    
    public void addFollower(Node follower){
        followers.add(follower);
        App.ui.addConnection(this, follower);
    }
    public ArrayList<Node> getFollowers(){
        return followers;
    }
    public void addPayoff(int value){
        payoffs.add(value);
    }
    public void setPayoff(int pos, int value){
        payoffs.set(pos, value);
    }
    public String toLongString(){
        String output = "";
        output+=name+"\n";
        for (int i  = 1; i <= payoffs.size();i++) {
            output += "player "+i+" payoff:"+payoffs.get(i-1)+"\n";
        }
        if (!followers.isEmpty()) {
            output+="followers include:\n";
            for (Node follower : followers) {
                output+=follower.getName()+"\n";
            }
        }
        
        return output;
    }
    public String toString(){
        String output = this.name;
        for (int elem : payoffs) {
            output+=" "+elem+" ";
        }
        return output;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval(location.x, location.y, 10, 10);
        g.drawString(toString(), location.x, location.y-2);
    }

    public double getY() {
        return location.getY();
    }

    public double getX() {
        return location.getX();
    }

    public void setInfoset(Infoset infoset) {
        this.infoset = infoset;
    }
    public Infoset getInfoset(){
        return infoset;
    }

    public void setX(double x) {
        location.setLocation(x, location.getY());
    }

    public void setY(double y) {
        location.setLocation(location.getX(), y);
    }

    public void setDefaultCordnates(int x,int y) {
        int yspace = ui.getHeight(); 
        setX(x);
        setY(y);
        for (int i = 0;i<followers.size();i++) {
            followers.get(i).setDefaultCordnates((int)(x+Constants.dx),(int)(y+yspace/followers.size()*i),yspace/followers.size());
        }
    }
    public void setDefaultCordnates(int x,int y,int yspace) {
        setX(x);
        setY(y);
        for (int i = 0;i<followers.size();i++) {
            followers.get(i).setDefaultCordnates((int)(x+Constants.dx),(int)(y+yspace/followers.size()*i),yspace/followers.size());
        }
    }
    public static void setUI(UI newUI){
        ui = newUI;
    }
    public void copyPayoffs(Node node){
        this.payoffs = node.payoffs;
    }
}
