public class Node {
    private Node prior;
    private Point pos;
    private double fValue;
    private double hValue;
    private double gValue;
    private Point goal;

    public Node(Point pos, Point goal, Node prior) {
        this.prior = prior;
        this.pos = pos;
        this.goal = goal;
        this.hValue = goal.distance(pos);
        this.gValue = 0;
        this.fValue = hValue + gValue;
    }

    public void setPrior(Node prior) { this.prior = prior; }

    public void generategValue() { gValue = prior.gValue + this.pos.distance(prior.pos); }

    public void generatefValue() { fValue = gValue + hValue; }

    public void generatehValue() { hValue = goal.distance(pos); }

    public double getgValue() { return gValue; }

    public double getfValue() { return fValue; }

    public Point getPos() { return pos; }

    public Node getPrior() { return prior; }

}
