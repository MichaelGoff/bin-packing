package persistence;

import domain.Coordinate;
import domain.Packing;
import domain.Part;
import domain.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PackingGenerator {

    private static final double PLATFORM_SIDE_LENGTH = 10.0;
    List<BigDecimal> xCoordinateList;
    List<BigDecimal> yCoordinateList;


    public Packing createPacking() {
        Packing packing = new Packing();

        //createPlatformList(packing);
        createPartList(packing);
        packing.setCoordinateList(createCoordinateList());

        return packing;
    }

    public void createPartList(Packing packing) {
        List<Part> partList = new ArrayList<Part>();
        List<Platform> platformList = new ArrayList<Platform>();
        int id = 0;
        //Scanner to read parts from a provided text file.
        Scanner input = null;
        try {
            input = new Scanner(new File("src/main/resources/order_geometries.csv"));
        } catch(FileNotFoundException e) {
            System.out.println("Parts List Text File not found in PackingGenerator.java");
            System.exit(1);
        }

        //adding parts to the packing list.
        input.nextLine();
        while(input.hasNext()) {
            String line = input.nextLine();
            StringTokenizer st =  new StringTokenizer(line, ",");
            st.nextToken(); //build ID
            st.nextToken(); //end time
            String orderId = st.nextToken(); //order Id
            st.nextToken(); //name
            int buildQuantity = Integer.parseInt(st.nextToken());
            st.nextToken(); //volume
            st.nextToken(); //sa
            st.nextToken(); //build direction
            st.nextToken(); //finish level
            double width = Double.parseDouble(st.nextToken());
            double height = Double.parseDouble(st.nextToken());
            st.nextToken(); //z value

            if(orderId.equals("109076")) {
                for(int i = 0; i < buildQuantity; i++) {
                    Part temp = new Part((long) id, width, height);
                    id++;
                    temp.setCoordinates(new Coordinate());
                    partList.add(temp);
                }
            }
        }

        for (Part part : partList) {         //getting first platform on the list as a default
            part.setPlatform(new Platform(part.getId(), PLATFORM_SIDE_LENGTH, PLATFORM_SIDE_LENGTH));
            part.getPlatform().addPart(part);
            platformList.add(part.getPlatform());
        }

        packing.setPartList(partList);
        packing.setPlatformList(platformList);
    }

    public void createPlatformList(Packing packing) {
        List<Platform> platformList = new ArrayList<Platform>();

        platformList.add(new Platform(0L, PLATFORM_SIDE_LENGTH, PLATFORM_SIDE_LENGTH));
        platformList.add(new Platform(1L, PLATFORM_SIDE_LENGTH, PLATFORM_SIDE_LENGTH));

        packing.setPlatformList(platformList);
    }

    public void createXCoordinateList() {
        xCoordinateList = new ArrayList<BigDecimal>();
        for(int i = 0; i < PLATFORM_SIDE_LENGTH; i++) {
            for (int j = 0; j <= 9; j++) {
                BigDecimal bd = new BigDecimal("" + i + "." + j);
                //rounding to the hundredths place
//                if(j <= 9) {
//                    bd = new BigDecimal("" + i + ".0" + j);
//                } else {
//                    bd = new BigDecimal("" + i + "." + j);
//                }

                bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                xCoordinateList.add(bd);
            }
        }
    }

    public void createYCoordinateList() {
        yCoordinateList = new ArrayList<BigDecimal>();
        for (int i = 0; i < PLATFORM_SIDE_LENGTH; i++) {
            for (int j = 0; j <= 9; j++) {
                BigDecimal bd = new BigDecimal("" + i + "." + j);
                    //rounding to the hundredths place
//                if (j <= 9) {
//                    bd = new BigDecimal("" + i + ".0" + j);
//
//                } else {
//                    bd = new BigDecimal("" + i + "." + j);
//                }

                bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                yCoordinateList.add(bd);
            }
        }
    }

    public List<Coordinate> createCoordinateList() {
        createXCoordinateList();
        createYCoordinateList();
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();

        for (BigDecimal x : xCoordinateList) {
            for(BigDecimal y : yCoordinateList) {
                coordinateList.add(new Coordinate(x, y));
            }
        }

        return coordinateList;

    }
}
