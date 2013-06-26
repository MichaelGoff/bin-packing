package solver.move;

import domain.Part;
import domain.Platform;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;

public class PlatformChangeMove implements Move {
    private Part part;
    private Platform toPlatform;

    public PlatformChangeMove(Part part, Platform toPlatform) {
        this.part = part;
        this.toPlatform = toPlatform;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(part.getPlatform(), toPlatform);
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new PlatformChangeMove(part, part.getPlatform());
    }

    public void doMove(ScoreDirector scoreDirector) {
        scoreDirector.beforeVariableChanged(part, "platform");
        part.getPlatform().getPartList().remove(part);
        part.setPlatform(toPlatform);
        part.getPlatform().getPartList().add(part);
        scoreDirector.afterVariableChanged(part, "platform");
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(toPlatform);
    }

    public String toString() {
        return part + " -> " + toPlatform;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof PlatformChangeMove) {
            PlatformChangeMove other = (PlatformChangeMove) o;
            return new EqualsBuilder()
                    .append(part, other.part)
                    .append(toPlatform, other.toPlatform)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(part)
                .append(toPlatform)
                .toHashCode();
    }

}
