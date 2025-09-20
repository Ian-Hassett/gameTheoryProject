public class App {
    public static UI ui;
    public static void main(String[] args) throws Exception {
        ui = new UI();
        Node.setUI(ui);
        Game g = new Game();
        
        
        //always rock
        Stratigy r = new Stratigy((Node)->Node.followers.get(0));
        Stratigy p = new Stratigy((Node)->Node.followers.get(1));
        Stratigy s = new Stratigy((Node)->Node.followers.get(2));
        //playing game with the shared strat above
        //System.out.println(s.play(p.play(g.getStart())).toLongString());
        // System.out.println(ui.toString());
    }
}
