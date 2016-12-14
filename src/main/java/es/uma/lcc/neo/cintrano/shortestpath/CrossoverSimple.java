package es.uma.lcc.neo.cintrano.shortestpath;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.solution.util.RepairDoubleSolution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Cintrano on 14/12/16.
 * Simple 1 point crossover
 */
public class CrossoverSimple implements CrossoverOperator<PathSolution> {

    private double distributionIndex;
    private double crossoverProbability;

    private JMetalRandom randomGenerator;


    /** Constructor */
    public CrossoverSimple(double crossoverProbability, double distributionIndex) {
        if (crossoverProbability < 0) {
            throw new JMetalException("Crossover probability is negative: " + crossoverProbability) ;
        } else if (distributionIndex < 0) {
            throw new JMetalException("Distribution index is negative: " + distributionIndex);
        }

        this.crossoverProbability = crossoverProbability;
        this.distributionIndex = distributionIndex;

        randomGenerator = JMetalRandom.getInstance() ;
    }

    public List<PathSolution> execute(List<PathSolution> solutions){
        if (null == solutions) {
            throw new JMetalException("Null parameter") ;
        } else if (solutions.size() != 2) {
            throw new JMetalException("There must be two parents instead of " + solutions.size()) ;
        }

        return doCrossover(crossoverProbability, solutions.get(0), solutions.get(1)) ;
    }

    private List<PathSolution> doCrossover(double crossoverProbability, PathSolution solution1, PathSolution solution2) {
        List<PathSolution> list = new ArrayList<PathSolution>();
        list.add(solution1);
        list.add(solution2);
        return list;
    }
}
