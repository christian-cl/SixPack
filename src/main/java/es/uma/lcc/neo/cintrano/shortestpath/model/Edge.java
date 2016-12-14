package es.uma.lcc.neo.cintrano.shortestpath.model;

import java.util.Arrays;

/**
 * Created by christian on 5/12/16.
 */
public class Edge {
    private Node start;
    private Node end;
    private double[] weights;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Edge(Node start, Node end, double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }
    public Edge(Node start, Node end, double w1, double w2) {
        this(start, end, new double[]{w1, w2});
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

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double getWeight(int i) {
        return weights[i];
    }

    public void setWeight(int i, double weight) {
        this.weights[i] = weight;
    }

    @Override
    public String toString() {
        return "<" + start +
                "," + end +
                ">";
    }
}
