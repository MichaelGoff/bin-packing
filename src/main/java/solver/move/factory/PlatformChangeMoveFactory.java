package solver.move.factory;

import domain.Packing;
import domain.Part;
import domain.Platform;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.solution.Solution;
import solver.move.PlatformChangeMove;

import java.util.ArrayList;
import java.util.List;

public class PlatformChangeMoveFactory implements MoveListFactory {

    public List<Move> createMoveList(Solution solution) {
        Packing packing = (Packing) solution;
        List<Move> moveList = new ArrayList<Move>();
        List<Platform> platformList = packing.getPlatformList();
        for(Part part : packing.getPartList()) {
            for(Platform platform : platformList) {
                moveList.add(new PlatformChangeMove(part, platform));
            }
        }
        return moveList;
    }
}
