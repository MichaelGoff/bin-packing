package domain;

import persistence.PackingGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the aspects of a platform.
 *
 * @author Michael Goff
 */
public class Platform {
    private double height;
    private double width;
    private List<Part> partList;
    private Long id;



    public Platform (Long id, double height, double width) {
        if (height <= 0.0 || width <= 0.0) {
            throw new IllegalArgumentException("Platform height and width must be > 0");
        }
        this.height = height;
        this.width = width;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public List<Coordinate> getPossibleCoordinatesList() {
        List<Coordinate> possibleCoordinatesList = new ArrayList<Coordinate>();

        List<Coordinate> coordinateList = new PackingGenerator().createCoordinateList();

        for(Coordinate coordinate : coordinateList) {
            for(Part part : partList) {
                if ((coordinate.getX() < part.getCoordinates().getX() ||
                    coordinate.getX() >= part.getCoordinates().getX() + part.getWidth()) &&
                        (coordinate.getY() < part.getCoordinates().getY() ||
                        coordinate.getY() >= part.getCoordinates().getY() + part.getHeight())) {
                    possibleCoordinatesList.add(coordinate);
                }
            }
        }
        return possibleCoordinatesList;
    }

    /**
     * Checks if the part will fit on the platform.
     * @param part The part to check if it will fit.
     * @return Returns true if the part will fit on the platform. False if it goes off an edge.
     */
    public boolean canHold(Part part) {
        return (part.getCoordinates().getX() + part.getWidth()) <= getWidth() &&
                (part.getCoordinates().getY() + part.getHeight()) <= getHeight();
    }

    /**
     * This method checks if the part intersects others and fits on the platform then adds it to the platform.
     * @param part The part to be added.
     * @return True if it successfully added, false if intersection or off edge.
     */
    public boolean addPart(Part part) {
        for(Part p : partList) {
            if(p.intersects(part)) {
                return false;
            }
        }
        if(!canHold(part)) {
            return false;
        }

        partList.add(part);
        return true;

    }

    public double getArea() {
        return width * height;
    }

    public double getLeftoverArea() {
        double sum = 0;
        for(Part p : partList) {
            sum += p.getArea();
        }
        return getArea() - sum;
    }

    public String getLabel() {
        return "Platform" + id;
    }




}
