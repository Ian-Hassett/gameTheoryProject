public class Player {
    public Stratigy strat;
    public Node play(Node Node){
        return strat.play(Node);
    }
}
