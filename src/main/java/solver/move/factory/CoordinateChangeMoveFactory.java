package solver.move.factory;

import domain.Coordinate;
import domain.Packing;
import domain.Part;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.solution.Solution;
import solver.move.CoordinateChangeMove;

import java.util.ArrayList;
import java.util.List;

public class CoordinateChangeMoveFactory implements MoveListFactory {
    public List<Move> createMoveList(Solution solution) {
        List<Move> moveList = new ArrayList<Move>();
        Packing packing = (Packing) solution;
        for(Part part : packing.getPartList()) {
            for(Coordinate coordinate : packing.getCoordinateList()) {
                moveList.add(new CoordinateChangeMove(part, coordinate));
            }
        }

        return moveList;
    }



}
