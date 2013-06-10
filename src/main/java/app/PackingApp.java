package app;

import domain.Packing;
import domain.Part;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.config.solver.XmlSolverFactory;
import persistence.PackingGenerator;


public class PackingApp  {

    public static void main (String[] args) {

        //creating move
        XmlSolverFactory solverFactory = new XmlSolverFactory("/solver/packingSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        Packing unsolvedPacking = new PackingGenerator().createPacking();

        //Solving and producing result.
        solver.setPlanningProblem(unsolvedPacking);
        solver.solve();
        Packing solvedPacking = (Packing) solver.getBestSolution();

        System.out.println("Part assignment: \n" + toDisplayString(solvedPacking));
    }

    public static String toDisplayString(Packing packing) {
        String text = "";
        for(Part part : packing.getPartList()) {
            text = text.concat("" + part + "\n");
        }

        return text;
    }
}
