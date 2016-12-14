package es.uma.lcc.neo.cintrano.shortestpath.model;

import java.util.List;

/**
 * Created by christian on 5/12/16.
 */
public class Node {
    private String name;
    private List<Edge> edges;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return name;
    }
}
