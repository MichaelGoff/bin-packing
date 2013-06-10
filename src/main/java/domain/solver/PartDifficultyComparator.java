package domain.solver;

import domain.Part;
import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: mag
 * Date: 6/3/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class PartDifficultyComparator implements Comparator<Part> {
    public int compare(Part a, Part b) {
        return new CompareToBuilder()
                .append(a.getArea(), b.getArea())
                .append(a.getId(), b.getId())
                .toComparison();
    }
}
