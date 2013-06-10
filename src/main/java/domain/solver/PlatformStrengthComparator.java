package domain.solver;

import domain.Platform;
import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.Comparator;

public class PlatformStrengthComparator implements Comparator<Platform> {

    public int compare(Platform a, Platform b) {
        return new CompareToBuilder()
                .append(a.getLeftoverArea(), b.getLeftoverArea())
                .append(a.getId(), b.getId())
                .toComparison();
    }
}
