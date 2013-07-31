package app;

import domain.Packing;
import domain.Part;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.config.solver.XmlSolverFactory;
import persistence.PackingGenerator;

import java.io.File;

public class PackingApp  {

    public static void main (String[] args) {

        //creating move
        XmlSolverFactory solverFactory = new XmlSolverFactory("/solver/packingSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        Packing unsolvedPacking = new PackingGenerator().createPacking();

        SolutionFileWriter solutionFileWriter = new SolutionFileWriter();
        File solutionFile = new File("src/main/resources/benchmark/data/unsolved/test_input.xml");

        solutionFileWriter.write(unsolvedPacking, solutionFile);

        //Solving and producing result.
        solver.setPlanningProblem(unsolvedPacking);
        solver.solve();
        Packing solvedPacking = (Packing) solver.getBestSolution();

        System.out.println("Score: " + solvedPacking.getScore());
        System.out.println("Part assignment: \n" + toDisplayString(solvedPacking));

        SVGGenerator generator = new SVGGenerator();
        generator.write(solvedPacking.getPartList(), solvedPacking.getPlatformList());
    }

    public static String toDisplayString(Packing packing) {
        String text = "";
        for(Part part : packing.getPartList()) {
            text = text.concat("" + part + " " + part.getPlatform() + " " + part.getCoordinates() + "\n");
        }

        return text;
    }
}
