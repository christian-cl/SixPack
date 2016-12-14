package es.uma.lcc.neo.cintrano;

import es.uma.lcc.neo.cintrano.shortestpath.BiObjectiveShortestPathProblem;
import es.uma.lcc.neo.cintrano.shortestpath.CrossoverSimple;
import es.uma.lcc.neo.cintrano.shortestpath.PathSolution;
import es.uma.lcc.neo.cintrano.shortestpath.model.Graph;
import es.uma.lcc.neo.cintrano.shortestpath.model.MutationSimple;
import es.uma.lcc.neo.cintrano.shortestpath.model.SelectionSimple;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;

import java.util.List;

import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printFinalSolutionSet;

/**
 * Created by Christian Cintrano on 03/12/2016.
 * Test main class fot the robustness bi-objective shortest path problem learning
 */
public class RobustnessTrashMain {

    public static void main (String[] args) {
        System.out.println("=== START ===");

        Problem problem = new BiObjectiveShortestPathProblem();
        System.out.println("problem:: " + problem);
        Algorithm<List<PathSolution>> algorithm;
        CrossoverOperator crossover;
        MutationOperator mutation;
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
        crossover = new CrossoverSimple(crossoverProbability, crossoverDistributionIndex);

        double mutationProbability = 1.0 / problem.getNumberOfVariables() ;
        double mutationDistributionIndex = 20.0 ;
        mutation = new MutationSimple();

        selection = new SelectionSimple();

        algorithm = new NSGAIIBuilder<PathSolution>(problem, crossover, mutation)
    //            .setSelectionOperator(selection)
                .setMaxIterations(250)
                .setPopulationSize(100)
                .build() ;

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute() ;

        List<PathSolution> population = algorithm.getResult() ;
        long computingTime = algorithmRunner.getComputingTime() ;

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);
//        if (!referenceParetoFront.equals("")) {
//            printQualityIndicators(population, referenceParetoFront) ;
//        }
        System.out.println("=== END ===");
    }
}
