package es.uma.lcc.neo.cintrano.shortestpath;

import org.uma.jmetal.problem.Problem;

/**
 * Created by Christian Cintrano on 5/12/16.
 * Bi-objective shortest path problem
 */
public class BiObjectiveShortestPathProblem<T> implements Problem<PathSolution<T>> {
    private int numVariables;
    private final int numObjectives = 2;
    private final int numConstraints = 0;

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
        return null;
    }

    public void evaluate(PathSolution<T> tPathSolution) {

    }

    public PathSolution<T> createSolution() {
        PathSolution<T> solution = new PathSolution<T>(new double[getNumberOfObjectives()], new T[getNumberOfVariables()]);
        return solution;
    }

}
