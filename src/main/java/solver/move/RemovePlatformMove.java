package solver.move;

import domain.Part;
import domain.Platform;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RemovePlatformMove implements Move {
    Platform platform;
    Part part;
    List<Platform> platformList;

    public RemovePlatformMove(Part part, List<Platform> platformList, Platform platform) {
        this.platform = platform;
        this.part = part;
        this.platformList = platformList;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return platform.isEmpty();
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new AddPlatformMove(null, platformList, platform);
    }

    public void doMove(ScoreDirector scoreDirector) {
        if(part != null) {
            platformList.get(1).addPart(part);
            part.setPlatform(platformList.get(1));
        }
        platformList.remove(platform);
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(platform);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof RemovePlatformMove) {
            RemovePlatformMove other = (RemovePlatformMove) o;
            return new EqualsBuilder()
                    .append(part, other.part)
                    .append(platformList, other.platformList)
                    .append(platform, other.platform)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(part)
                .append(platformList)
                .append(platform)
                .toHashCode();
    }
}
