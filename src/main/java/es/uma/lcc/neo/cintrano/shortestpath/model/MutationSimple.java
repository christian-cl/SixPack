package es.uma.lcc.neo.cintrano.shortestpath.model;

import es.uma.lcc.neo.cintrano.shortestpath.PathSolution;
import org.uma.jmetal.operator.MutationOperator;

/**
 * Created by christian on 14/12/16.
 */
public class MutationSimple implements MutationOperator<PathSolution> {

    public PathSolution execute(PathSolution pathSolution) {
        return pathSolution;
    }
}
