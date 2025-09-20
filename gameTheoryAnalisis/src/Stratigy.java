import java.util.function.BiFunction;
import java.util.function.Function;

public class Stratigy{
    Function<Node,Node> strat;
    public Stratigy(Function<Node,Node> strat){
        this.strat = strat;
    }

    public Node play(Node Node){
        Game.gamepath.addLast(strat.apply(Node));
        return strat.apply(Node);
    }
    
}
