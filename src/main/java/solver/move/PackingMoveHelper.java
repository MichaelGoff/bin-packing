package solver.move;

import domain.Part;
import domain.Platform;
import org.optaplanner.core.impl.score.director.ScoreDirector;

public class PackingMoveHelper {
    public static void movePlatform(ScoreDirector scoreDirector, Part part, Platform toPlatform) {
        scoreDirector.beforeVariableChanged(part, "platform");
        part.setPlatform(toPlatform);
        scoreDirector.afterVariableChanged(part, "platform");
    }

    private PackingMoveHelper () {

    }
}
