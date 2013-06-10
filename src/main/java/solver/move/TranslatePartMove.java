package solver.move;

import domain.Coordinate;
import domain.Part;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;

public class TranslatePartMove implements Move {

    Part part;
    Coordinate newCoordinate;


    public TranslatePartMove(Part part, Coordinate newCoordinate) {
        this.part = part;
        this.newCoordinate = newCoordinate;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !(part.getCoordinates().equals(newCoordinate));
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new TranslatePartMove(part, part.getCoordinates());
    }

    public void doMove(ScoreDirector scoreDirector){
        part.setCoordinates(newCoordinate);
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(newCoordinate);
    }
}
