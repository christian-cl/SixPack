package es.uma.lcc.neo.cintrano.shortestpath;

import es.uma.lcc.neo.cintrano.shortestpath.model.Edge;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.solutionattribute.SolutionAttribute;


/**
 * Created by christian on 5/12/16.
 * Path solution in the graph
 */
public class PathSolution implements Solution<Edge>, SolutionAttribute<PathSolution, Object> {
    private double[] objectives;
    private Edge[] variables;

    public PathSolution(int numOfObjectives, int numOfVariables) {
        objectives = new double[numOfObjectives];
        variables = new Edge[numOfVariables];
    }

    public PathSolution(double[] objectives, Edge[] variables) {
        this.objectives = objectives;
        this.variables = variables;
    }

    public void setObjective(int i, double v) {
        objectives[i] = v;
    }

    public double getObjective(int i) {
        return objectives[i];
    }

    public Edge getVariableValue(int i) {
        return variables[i];
    }

    public void setVariableValue(int i, Edge t) {
        variables[i] = t;
    }

    public String getVariableValueString(int i) {
        return variables[i].toString();
    }

    public int getNumberOfVariables() {
        return variables.length;
    }

    public int getNumberOfObjectives() {
        return objectives.length;
    }

    public PathSolution copy() {
        return this;
    }

    public void setAttribute(Object o, Object o1) {

    }

    public Object getAttribute(Object o) {
        return null;
    }

    public Object getAttribute(PathSolution solution) {
        return solution.getAttribute(getAttributeID());
    }

    public void setAttribute(PathSolution solution, Object value) {
        solution.setAttribute(getAttributeID(), value);
    }

    public Object getAttributeID() {
        return this.getClass() ;
    }


}
