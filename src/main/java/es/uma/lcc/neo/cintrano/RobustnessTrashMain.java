package es.uma.lcc.neo.cintrano;

import es.uma.lcc.neo.cintrano.shortestpath.BiObjectiveShortestPathProblem;
import es.uma.lcc.neo.cintrano.shortestpath.CrossoverSimple;
import es.uma.lcc.neo.cintrano.shortestpath.PathSolution;
import es.uma.lcc.neo.cintrano.shortestpath.model.*;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printFinalSolutionSet;

/**
 * Created by Christian Cintrano on 03/12/2016.
 * Test main class fot the robustness bi-objective shortest path problem learning
 */
public class RobustnessTrashMain {

    public static void main (String[] args) {
        System.out.println("=== START ===");
        System.out.println(applyWeights(parserGraph("graph.xml"), "weights.xml").toString());
        //executeAlgorithm(args);
        System.out.println("=== END ===");
    }

    private static void executeAlgorithm(String[] args) {
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
    }


    public static Graph parserGraph(String s) {
        Graph graph = new Graph();
        List<Node> nodes = new ArrayList<Node>();
        List<Edge> edges = new ArrayList<Edge>();
        try {
            File fXmlFile = new File(s);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("node");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                org.w3c.dom.Node nNode = nList.item(temp);
                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    nodes.add(new Node(eElement.getAttribute("id")));
                }
            }

            nList = doc.getElementsByTagName("arc");
            System.out.println(nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                org.w3c.dom.Node nNode = nList.item(temp);
                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Node nFrom = null, nTo = null;
                    for (Node n : nodes) {
                        if (n.getName().equals(eElement.getAttribute("from"))) {
                            nFrom = n;
                        } else if (n.getName().equals(eElement.getAttribute("to"))) {
                            nTo = n;
                        }
                    }
                    if (nFrom != null && nTo != null) {
                        edges.add(new Edge(Integer.parseInt(eElement.getAttribute("arcid")), nFrom, nTo));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        graph.setNodes(nodes);
        graph.setEdges(edges);
        return graph;
    }

    public static Graph applyWeights(Graph graph, String file) {
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("weight");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                org.w3c.dom.Node nNode = nList.item(temp);
                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode; // eElement = weight
                    // Search arc reference
                    Edge edge = null;
                    for (Edge e: graph.getEdges()) {
                        if (e.getId() == Integer.parseInt(eElement.getAttribute("arcid"))) {
                            edge = e;
                            break;
                        }
                    }
                    if (edge != null ) {
                        edge.addWeight(new Weight(Double.parseDouble(eElement.getAttribute("value")), eElement.getAttribute("type")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
}
