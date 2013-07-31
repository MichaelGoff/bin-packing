package app;

import domain.Coordinate;
import domain.Packing;
import domain.Part;
import domain.Platform;
import org.optaplanner.core.impl.solution.ProblemIO;
import org.optaplanner.core.impl.solution.Solution;

import java.io.*;

public class SolutionFileWriter implements ProblemIO {

    public String getFileExtension() {
        return "xml";
    }

    public Solution read(File inputSolutionFile) {
        Solution solution = null;

        return solution;
    }

    public void write(Solution solution, File outputSolutionFile) {
        try {
            int idCount = 0;
            Packing packing = (Packing) solution;
            Writer writer = new BufferedWriter(new FileWriter(outputSolutionFile));

            writer.write("<Packing id=\"" + idCount + "\">\n");
            idCount++;
            writer.write("\t<id>0</id>\n");
            writer.write("\t<name>test_input</name>\n");

            //Writing part list to file.
            writer.write("\t<partList id=\"" + idCount + "\">\n");
            idCount++;
            for (Part part : packing.getPartList()) {
                writer.write("\t\t<Part id=\"" + idCount + "\">\n");
                idCount++;
                writer.write("\t\t\t<id>" + part.getId() + "</id>\n");
                writer.write("\t\t\t<width>" + part.getWidth() + "</width>\n");
                writer.write("\t\t\t<height>" + part.getHeight() + "</height>\n");
                writer.write("\t\t\t<coordinates>" + part.getCoordinates() + "</coordinates>\n");
                writer.write("\t\t\t<platform>" + part.getPlatform() + "</platform>\n");
                writer.write("\t\t</Part>\n");
            }
            writer.write("\t</partList>\n");

            //writing platform list
            writer.write("\t<platformList id=\"" + idCount + "\">\n");
            idCount++;
            for(Platform platform : packing.getPlatformList()) {
                writer.write("\t\t<Platform id=\"" + idCount + "\">\n");
                idCount++;
                writer.write("\t\t\t<id>" + platform.getId() + "</id>\n");
                writer.write("\t\t\t<width>" + platform.getWidth() + "</width>\n");
                writer.write("\t\t\t<height>" + platform.getHeight() + "</height>\n");
                writer.write("\t\t\t<partList>\n");
                for (Part part : platform.getPartList()) {
                    writer.write("\t\t\t\t<Part>\n");
                    writer.write("\t\t\t\t\t<id>" + part.getId() + "</id>\n");
                    writer.write("\t\t\t\t\t<width>" + part.getWidth() + "</width>\n");
                    writer.write("\t\t\t\t\t<height>" + part.getHeight() + "</height>\n");
                    writer.write("\t\t\t\t\t<coordinates>" + part.getCoordinates() + "</coordinates>\n");
                    writer.write("\t\t\t\t\t<platform>" + part.getPlatform() + "</platform>\n");
                    writer.write("\t\t\t\t</Part>\n");
                }
                writer.write("\t\t\t</partList>\n");
                writer.write("\t\t</Platform>\n");
            }
            writer.write("\t</platformList>\n");

            //writing coordinate list
            writer.write("\t<coordinateList id=\"" + idCount + "\">\n");
            idCount++;
            for(Coordinate coordinate : packing.getCoordinateList()) {
                writer.write("\t\t<Coordinate id=\"" + idCount + "\">\n");
                idCount++;
                writer.write("\t\t\t<x>" + coordinate.getX() + "</x>\n");
                writer.write("\t\t\t<y>" + coordinate.getY() + "</y>\n");
                writer.write("\t\t</Coordinate>\n");
            }
            writer.write("\t</coordinateList>\n");

            writer.write("\t<score>" + packing.getScore() + "</score>\n");
            writer.write("</Packing>");

        } catch (IOException e) {
            System.out.println("IOException in SolutionFileWriter");
            System.exit(1);
        }

    }
}
