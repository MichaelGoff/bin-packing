package solver.move;

import domain.Part;
import domain.Platform;
import org.optaplanner.core.impl.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddPlatformMove implements Move {
    Part part;
    List<Platform> platformList;
    Platform newPlatform;

    public AddPlatformMove(Part part, List<Platform> platformList, Platform newPlatform) {
        this.part = part;
        this.platformList = platformList;
        this.newPlatform = newPlatform;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return true;
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new AddPlatformMove(part, platformList, null);
    }

    public void doMove(ScoreDirector scoreDirector) {
        if(newPlatform != null) {
            part.getPlatform().getPartList().remove(part);
            newPlatform.addPart(part);
            part.setPlatform(newPlatform);
            platformList.add(newPlatform);
        }
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(part);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(null);
    }
}
