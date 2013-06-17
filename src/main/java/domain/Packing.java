package domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.impl.solution.Solution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;

/**
 * domain.Packing class.
 *
 * @author Michael Goff
 */
@PlanningSolution
public class Packing implements Solution<HardSoftDoubleScore> {
    //private List<Platform> platformList;
    private List<Part> partList;
    private List<Coordinate> coordinateList;
    private HardSoftDoubleScore score;
    private Long id;

    public HardSoftDoubleScore getScore() {
        return score;
    }

    public void setScore(HardSoftDoubleScore score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    /*public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }*/

    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }

    /*public List<Platform> getPlatformList() {
        return platformList;
    } */

    @PlanningEntityCollectionProperty
    public List<Part> getPartList() {
        return partList;
    }

    /**
     * Adds a part to the part list
     * @param part The part to add to the list.
     */
    public void addPart(Part part) {
        partList.add(part);
    }

    /*
     * Adds a platform to the list.

    public void addPlatform(int id) {
        platformList.add(new Platform(id, PLATFORM_SIDE_LENGTH, PLATFORM_SIDE_LENGTH));
    }

    public void placePart() {
        boolean added = false;
        int numPlatforms = 0;
        addPlatform(numPlatforms);


        for(Part part : partList) {
            for(Platform platform : platformList) {
                if(platform.addPart(part)) {
                    added = true;
                    break;
                }
            }

            if(!added) {
                numPlatforms++;
                addPlatform(numPlatforms);
                platformList.get(numPlatforms).addPart(part);

            }
        }
    } */

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        //facts.addAll(platformList);        //TODO implement platforms
        facts.addAll(coordinateList);

        return facts;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Packing)) {     //removed id == null
            return false;

        } else {
            Packing other = (Packing) o;
            if(partList.size() != other.partList.size()) {
                return false;
            }
            for(Iterator<Part> it = partList.iterator(), otherIt = other.partList.iterator(); it.hasNext();) {
                Part part = it.next();
                Part otherPart = otherIt.next();
                if(!part.solutionEquals(otherPart)) {
                    return false;
                }
            }
            return true;
        }
    }
}
