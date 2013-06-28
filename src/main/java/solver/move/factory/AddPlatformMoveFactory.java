package solver.move.factory;

import domain.Packing;
import domain.Part;
import domain.Platform;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.solution.Solution;
import solver.move.AddPlatformMove;

import java.util.ArrayList;
import java.util.List;

public class AddPlatformMoveFactory implements MoveListFactory{
    public List<Move> createMoveList(Solution solution) {
        Packing packing = (Packing) solution;
        List<Move> moveList = new ArrayList<Move>();
        List<Platform> platformList = packing.getPlatformList();
        for(Part part : packing.getPartList()) {
            moveList.add(new AddPlatformMove(part, platformList, new Platform(new Long(platformList.size()), 10.0, 10.0)));
        }
        return moveList;
    }
}
