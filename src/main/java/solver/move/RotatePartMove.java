package solver.move;

import domain.Part;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;

public class RotatePartMove implements Move {
    Part part;

    public RotatePartMove (Part part) {
        this.part = part;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !(part.getWidth() == part.getHeight());
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new RotatePartMove(part);
    }

    public void doMove(ScoreDirector scoreDirector) {
        scoreDirector.beforeVariableChanged(part, "width, height");
        double temp = part.getWidth();
        part.setWidth(part.getHeight());
        part.setHeight(temp);
        scoreDirector.afterVariableChanged(part, "width, height");
    }

    public String toString() {
        return part + " Rotated W: " + part.getWidth() + "H: " + part.getHeight();
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<?extends Object> getPlanningValues() {
        return Collections.singletonList(null);
    }



}
