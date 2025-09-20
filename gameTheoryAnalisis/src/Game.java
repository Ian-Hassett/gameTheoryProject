import java.util.LinkedList;

public class Game {
    public PayoffMatrix payoffs;
    static LinkedList<Node> gamepath = new LinkedList<>();
    public Node start; 

    public Game(){
        generateNodes();
        assignInfoSets();
        distributeCordinates();
        payoffs = new PayoffMatrix(start);
        payoffs.display();
    }

    public void findNashEqualibrim(){
        throw new UnsupportedOperationException("Unimplemented method 'findNashEqualibrim'");
    }
    
    private void generateNodes() {
        //example game Rock paper scissors
        //generate start
        start = new Node("Start", true);
        //generate p1 options
        start.addFollower(new Node("rock"));
        start.addFollower(new Node("paper"));
        start.addFollower(new Node("siccers"));
        //generate p2 options
        int i = 0;
        for (Node elem : start.followers) {
            elem.addFollower(new Node(elem.name+",rock",-(++i%3-1),(i%3-1)));
            elem.addFollower(new Node(elem.name+",paper",-(++i%3-1),(i%3-1)));
            elem.addFollower(new Node(elem.name+",siccers",-(++i%3-1),(i%3-1)));
            i--;
        }
        
    }

    public PayoffMatrix getPayoffMatrix() {
        return payoffs;
    }

    public Node getStart() {
        return start;
    }

    private void distributeCordinates() {
        start.setDefaultCordnates(Constants.defaultX,Constants.defaultY);
    }

    private void assignInfoSets() {

        Infoset p1turn = new Infoset("p2 turn");
        for (Node elem : start.followers) {
            p1turn.add(elem);
        }
    }
   
    

}
