import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class UI extends JFrame {
    public ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Line> connections = new ArrayList<>();;
    private Node selectedNode = null;
    private Point lastPoint = null;
    public GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public UI() {
        setVisible(true);
        setTitle("Graph Visualization");
        device.setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Add mouse listeners for dragging
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Node node : nodes) {
                    if (new Rectangle((int)node.getX() - 10, (int)node.getY() - 10, 20, 20).contains(e.getPoint())) {
                        selectedNode = node;
                        lastPoint = e.getPoint();
                        return;
                    }
                }
                selectedNode = null;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedNode != null && lastPoint != null) {
                    Node endNode = getNodeAtPoint(e.getPoint());
                    if (endNode != null && endNode != selectedNode) {
                        connections.add(new Line(selectedNode, endNode));
                    }
                }
                selectedNode = null;
                lastPoint = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedNode != null) {
                    int dx = e.getX() - lastPoint.x;
                    int dy = e.getY() - lastPoint.y;
                    selectedNode.setX(selectedNode.getX() + dx);
                    selectedNode.setY(selectedNode.getY() + dy);
                    lastPoint = e.getPoint();
                    repaint();
                }
            }
        });
    }
    public void addNode(Node node){
        nodes.add(node);
    }
    public void addConnection(Node node,Node node2){
        connections.add(new Line(node, node2));
    }

    private Node getNodeAtPoint(Point point) {
        for (Node node : nodes) {
            if (new Rectangle((int)node.getX() - 10, (int)node.getY() - 10, 20, 20).contains(point)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Draw connections
        for (Line line : connections) {
            line.draw(g);
        }

        // Draw nodes
        for (Node node : nodes) {
            node.draw(g);
        }
    }
}

class Line {
    private Node startNode;
    private Node endNode;

    public Line(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawLine((int)startNode.getX(), (int)startNode.getY(), (int)endNode.getX(), (int)endNode.getY());
    }
}