package solver.move;

import domain.Part;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
        return part + " Rotated";
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<?extends Object> getPlanningValues() {
        return Collections.singletonList(null);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof RotatePartMove) {
            RotatePartMove other = (RotatePartMove) o;
            return new EqualsBuilder()
                    .append(part, other.part)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(part)
                .toHashCode();
    }

}
