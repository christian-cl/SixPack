package es.uma.lcc.neo.cintrano;

import es.uma.lcc.neo.cintrano.shortestpath.BiObjectiveShortestPathProblem;
import es.uma.lcc.neo.cintrano.shortestpath.PathSolution;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemUtils;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

import java.util.List;

import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printFinalSolutionSet;
import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printQualityIndicators;

/**
 * Created by Christian Cintrano on 03/12/2016.
 * Test main class fot the robustness bi-objective shortest path problem learning
 */
public class RobustnessTrashMain {

    public static void main (String[] args) {
        System.out.println("=== Init Run ===");

        Problem problem = new BiObjectiveShortestPathProblem();
        Algorithm<List<PathSolution>> algorithm;
        CrossoverOperator<PathSolution> crossover;
        MutationOperator<PathSolution> mutation;
        SelectionOperator<List<PathSolution>, PathSolution> selection;
        String referenceParetoFront = "" ;

        String problemName;
        if (args.length == 1) {
            problemName = args[0];
        } else if (args.length == 2) {
            problemName = args[0] ;
            referenceParetoFront = args[1] ;
        } else {
            problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
            referenceParetoFront = "src/main/resources/ZDT1.pf" ;
        }


        double crossoverProbability = 0.9 ;
        double crossoverDistributionIndex = 20.0 ;
        crossover = new SBXCrossover(crossoverProbability, crossoverDistributionIndex) ;

        double mutationProbability = 1.0 / problem.getNumberOfVariables() ;
        double mutationDistributionIndex = 20.0 ;
        mutation = new PolynomialMutation(mutationProbability, mutationDistributionIndex) ;

        selection = new BinaryTournamentSelection<PathSolution>(new RankingAndCrowdingDistanceComparator<PathSolution>());

        algorithm = new NSGAIIBuilder<PathSolution>(problem, crossover, mutation)
                .setSelectionOperator(selection)
                .setMaxIterations(250)
                .setPopulationSize(100)
                .build() ;

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute() ;

        List<PathSolution> population = algorithm.getResult() ;
        long computingTime = algorithmRunner.getComputingTime() ;

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);
        if (!referenceParetoFront.equals("")) {
            printQualityIndicators(population, referenceParetoFront) ;
        }
        System.out.println("=== END ===");
    }
}
