package es.uma.lcc.neo.cintrano.shortestpath;

import es.uma.lcc.neo.cintrano.shortestpath.model.Node;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import org.uma.jmetal.util.solutionattribute.SolutionAttribute;


/**
 * Created by christian on 5/12/16.
 * Path solution in the graph
 */
public class PathSolution<T> implements Solution<T> {
    private double[] objectives;
    private T[] variables;

    public void setObjective(int i, double v) {
        objectives[i] = v;
    }

    public double getObjective(int i) {
        return objectives[i];
    }

    public T getVariableValue(int i) {
        return variables[i];
    }

    public void setVariableValue(int i, T t) {
        variables[i] = t;
    }

    public String getVariableValueString(int i) {
        return null;
    }

    public int getNumberOfVariables() {
        return variables.length;
    }

    public int getNumberOfObjectives() {
        return objectives.length;
    }

    public Solution<T> copy() {
        return null;
    }

    public void setAttribute(Object o, Object o1) {
        ((Solution) o).setAttribute(getAttributeID(), o1);
    }

    public Object getAttribute(Object o) {
        return ((Solution) o).getAttribute(getAttributeID());
    }

    public Object getAttributeID() {
        return this.getClass() ;
    }

    //protected double overallConstraintViolationDegree ;
    //protected int numberOfViolatedConstraints ;
    //protected Map<Object, Object> attributes ;

    /*
    public PathSolution(double[] objectives, Node[] variables) {
        this.objectives = objectives;
        this.variables = variables;
    }

    public void setObjective(int i, double v) {
        objectives[i] = v;
    }

    public double getObjective(int i) {
        return objectives[i];
    }

    public Object getVariableValue(int i) {
        return variables[i];
    }

    public void setVariableValue(int i, Object o) {
        variables[i] = (Node) o;
    }

    public String getVariableValueString(int i) {
        return variables[i].name;
    }

    public int getNumberOfVariables() {
        return variables.length;
    }

    public int getNumberOfObjectives() {
        return objectives.length;
    }

    public Solution copy() {
        return null;
    }

    public void setAttribute(Object o, Object o1) {
        ((Solution) o).setAttribute(getAttributeID(), o1);
    }

    public Object getAttribute(Object o) {
        return ((Solution) o).getAttribute(getAttributeID());
    }

    public Object getAttributeID() {
        return this.getClass() ;
    }
    */
}
