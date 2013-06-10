package solver.move.factory;


import domain.Coordinate;
import domain.Packing;
import domain.Part;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.solution.Solution;
import solver.move.TranslatePartMove;

import java.util.ArrayList;
import java.util.List;

public class TranslatePartMoveFactory implements MoveListFactory{

    public List<Move> createMoveList(Solution solution) {
        Packing packing = (Packing) solution;
        List<Move> moveList = new ArrayList<Move>();
        List<Coordinate> coordinateList = packing.getCoordinateList();
        for(Part part : packing.getPartList()) {
            for(Coordinate coordinate : coordinateList) {
                moveList.add(new TranslatePartMove(part, coordinate));
            }
        }
        return moveList;
    }

}
