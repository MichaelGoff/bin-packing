package solver.move;

import domain.Part;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class RotatePartMove implements Move {
    Part part;

    public RotatePartMove (Part part) {
        this.part = part;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !(part.getCoordinates().getX() == part.getCoordinates().getY());
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new RotatePartMove(part);
    }

    public void doMove(ScoreDirector scoreDirector) {
        part.setCoordinate(new BigDecimal(part.getCoordinates().getY()), new BigDecimal(part.getCoordinates().getX()));
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<?extends Object> getPlanningValues() {
        return Collections.singletonList(part.getCoordinates());
    }



}
