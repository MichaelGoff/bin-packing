package solver.move;

import domain.Part;
import domain.Platform;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RemoveFactoryMove implements Move {
    Platform platform;
    Part part;
    List<Platform> platformList;

    public RemoveFactoryMove(Part part, List<Platform> platformList, Platform platform) {
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
        return Collections.singletonList(null);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(null);
    }

}
