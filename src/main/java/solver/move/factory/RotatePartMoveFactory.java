package solver.move.factory;

import domain.Packing;
import domain.Part;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.solution.Solution;
import solver.move.RotatePartMove;

import java.util.ArrayList;
import java.util.List;

public class RotatePartMoveFactory implements MoveListFactory {

    public List<Move> createMoveList(Solution solution) {
        Packing packing = (Packing) solution;
        List<Move> moveList = new ArrayList<Move>();
        for(Part part : packing.getPartList()) {
            moveList.add(new RotatePartMove(part));
        }
        return moveList;
    }

}
