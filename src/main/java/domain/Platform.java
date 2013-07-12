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
        partList = new ArrayList<Part>();
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

    public List<Part> getPartList() {
        return partList;
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
     * This method checks if the part intersects others and fits on the platform then adds it to the platform.
     * @param part The part to be added.
     */
    public void addPart(Part part) {
        partList.add(part);
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

    public boolean isEmpty() {
        return partList.isEmpty();
    }

    public String toString() {
        return "Platform " + id;
    }




}
