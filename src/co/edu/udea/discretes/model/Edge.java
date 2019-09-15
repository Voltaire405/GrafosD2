package co.edu.udea.discretes.model;

public class Edge {
    private int num;
    private Vertex in;
    private  Vertex out;

    public Edge(int num, Vertex in, Vertex out) {
        this.num = num;
        this.in = in;
        this.out = out;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Vertex getIn() {
        return in;
    }

    public void setIn(Vertex in) {
        this.in = in;
    }

    public Vertex getOut() {
        return out;
    }

    public void setOut(Vertex out) {
        this.out = out;
    }
}
