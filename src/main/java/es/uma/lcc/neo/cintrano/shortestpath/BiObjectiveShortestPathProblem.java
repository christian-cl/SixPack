package es.uma.lcc.neo.cintrano.shortestpath;

import es.uma.lcc.neo.cintrano.shortestpath.model.Edge;
import es.uma.lcc.neo.cintrano.shortestpath.model.Graph;
import es.uma.lcc.neo.cintrano.shortestpath.model.Node;
import org.uma.jmetal.problem.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Christian Cintrano on 5/12/16.
 * Bi-objective shortest path problem
 */
public class BiObjectiveShortestPathProblem implements Problem<PathSolution> {
    private Graph graph;
    private Node start;
    private Node end;
    private int numVariables;
    private final int numObjectives = 2;
    private final int numConstraints = 0;

    /**
     * Constructor.
     * Creates a default instance of the Bi-Objective Shortest Path problem.
     */
    public BiObjectiveShortestPathProblem() {
        // 3 variables by default
        graph = new Graph();
        Node n1 = new Node("n1");
        Node n2 = new Node("n2");
        Node n3 = new Node("n3");
        Node n4 = new Node("n4");

        Edge e1 = new Edge(n1, n2, 1, 4);
        Edge e2 = new Edge(n1, n3, 2, 3);
        Edge e3 = new Edge(n2, n4, 2, 3);
        Edge e4 = new Edge(n3, n4, 3, 2);

        List nodes = new ArrayList();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);

        List edges = new ArrayList();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);

        graph.setNodes(nodes);
        graph.setEdges(edges);

        start = n1;
        end = n4;

        setNumVariables(4);
    }

    public BiObjectiveShortestPathProblem(Graph graph, Integer numVariables) {
        this.graph = graph;
        setNumVariables(numVariables);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setNumVariables(int numVariables) {
        this.numVariables = numVariables;
    }

    public int getNumberOfVariables() {
        return numVariables;
    }

    public int getNumberOfObjectives() {
        return numObjectives;
    }

    public int getNumberOfConstraints() {
        return numConstraints;
    }

    public String getName() {
        return "BIOSPP";
    }

    public void evaluate(PathSolution pathSolution) {
        float f0 = 0, f1 = 0;
        for (int i = 0; i < pathSolution.getNumberOfObjectives(); i++) {
            f0 += pathSolution.getVariableValue(0).getWeight(0);
            f1 += pathSolution.getVariableValue(1).getWeight(1);
        }

        pathSolution.setObjective(0,f0);
        pathSolution.setObjective(1,f1);
    }

    public PathSolution createSolution() {
        List<Edge> aux = new ArrayList<Edge>();
        Node current = start;
        List<Edge> path = new ArrayList<Edge>();
        boolean endCreate = false;
        Random rand = new Random();
        while (!endCreate) {
            for (Edge e : graph.getEdges()) {
                if (e.getStart() == current) {
                    aux.add(e);
                }
            }
            for (Edge e : aux) {
                if (e.getEnd() == end) {
                    path.add(e);
                    endCreate = true;
                }
            }
            if (!endCreate) {
                // Select a random edge
                path.add(aux.get(rand.nextInt(aux.size())));
                aux = new ArrayList<Edge>();
                current = path.get(path.size() - 1).getEnd();
            }
        }

        Edge[] pathArray = new Edge[path.size()];
        for (int i = 0; i < path.size(); i++) {
            pathArray[i] = path.get(i);
        }
        return new PathSolution(new double[getNumberOfObjectives()], pathArray);
    }

    @Override
    public String toString() {
        return "BiObjectiveShortestPathProblem{" +
                "graph=" + graph +
                ", start=" + start +
                ", end=" + end +
                ", numVariables=" + numVariables +
                ", numObjectives=" + numObjectives +
                ", numConstraints=" + numConstraints +
                '}';
    }
}
