package es.uma.lcc.neo.cintrano.shortestpath.model;

import java.util.List;

/**
 * Created by christian on 5/12/16.
 */
public class Graph {
    List<Node> nodes;
    List<Edge> edges;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        String s = "Graph{" +
                "n nodes=" + nodes.size() +
                ", n edges=" + edges.size() +
                "}\n";
        for (Edge e : edges) {
            s += e.toString();
        }
        return s;
    }
}
