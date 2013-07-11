package solver.move;

import domain.Coordinate;
import domain.Part;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;

public class CoordinateChangeMove implements Move {
    private Part part;
    private Coordinate newCoordinate;

    public CoordinateChangeMove(Part part, Coordinate newCoordinate) {
        this.part = part;
        this.newCoordinate = newCoordinate;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !part.getCoordinates().equals(newCoordinate);
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new CoordinateChangeMove(part, part.getCoordinates());
    }

    public void doMove(ScoreDirector scoreDirector) {
        scoreDirector.beforeVariableChanged(part, "coordinate");
        part.setCoordinates(newCoordinate);
        scoreDirector.afterVariableChanged(part, "coordinate");
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(newCoordinate);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof CoordinateChangeMove) {
            CoordinateChangeMove other = (CoordinateChangeMove) o;
            return new EqualsBuilder()
                    .append(part, other.part)
                    .append(newCoordinate, other.newCoordinate)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(part)
                .append(newCoordinate)
                .toHashCode();
    }

    public String toString() {
        return "" + part + " -> " + newCoordinate + " on " + part.getPlatform();
    }
}
