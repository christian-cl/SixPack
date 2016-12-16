package es.uma.lcc.neo.cintrano.shortestpath.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Cintrano on 5/12/16.
 */
public class Edge {
    private int id;
    private Node start;
    private Node end;
    private List<Weight> weights;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Edge(int id, Node start, Node end) {
        this.id = id;
        this.start = start;
        this.end = end;
        weights = new ArrayList<Weight>();
    }

    public Edge(Node start, Node end, List<Weight> weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    public Edge(Node start, Node end, double w1, double w2) {
        this(start, end);
        weights.add(new Weight(w1, "t1"));
        weights.add(new Weight(w2, "t2"));
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Weight getWeight(int i) {
        return weights.get(i);
    }

    public void setWeight(int i, Weight weight) {
        this.weights.set(i, weight);
    }

    public void addWeight(Weight weight) {
        this.weights.add(weight);
    }

    @Override
    public String toString() {
        String s = "<" + start +
                "," + end +
                "> : [";
        for (Weight w : weights) {
            s += w.toString();
        }
        s+= "]\n";
        return s;
    }
}
