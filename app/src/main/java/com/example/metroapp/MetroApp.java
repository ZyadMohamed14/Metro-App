package com.example.metroapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetroApp {
    private ArrayList<String> metroLine1;
    private ArrayList<String> metroLine2;
    private ArrayList<String> metroLine3;
    private ArrayList<String> cairoKitKateBranch;
    private ArrayList<ArrayList<String>> routes;
    private StringBuilder directionForFirstRoute;
    private StringBuilder directionForSecondRoute;
    private String direction; //this for single rouht
    private String start, end;



    public String getDirection() {
        return direction;
    }

    public MetroApp(String start, String end) {
        this.start = start;
        this.end = end;
        metroLine1 = CairoLines.cairoLine1();
        metroLine2 = CairoLines.cairoLine2();
        metroLine3 = CairoLines.cairoLine3();
        cairoKitKateBranch = CairoLines.kitKatCairoUniversityLine;
        routes = new ArrayList<>();
        directionForFirstRoute = new StringBuilder();
        directionForSecondRoute = new StringBuilder();
        direction = "";
    }

    public ArrayList<ArrayList<String>> getRoutes() {
        return routes;
    }

    public StringBuilder getDirectionForFirstRoute() {
        return directionForFirstRoute;
    }

    public StringBuilder getDirectionForSecondRoute() {
        return directionForSecondRoute;
    }

    public void searchPath() {
        if (isSameLine(start, end)) {
            getPathIfSameLine();
        } else {
            searchInManyLines();
        }
    }

    boolean isVaildData() {
        if (start.equals("") || end.equals("")) return false;
        if (start.equals(end)) return false;
        return isVaildStation(start) && isVaildStation(end);


    }

    boolean isVaildStation(String station) {
        return metroLine1.contains(station) || metroLine2.contains(station) || metroLine3.contains(station)
                || cairoKitKateBranch.contains(station);
    }

    private boolean isSameLine(String start, String end) {
        return isOnSameLine(metroLine1, start, end) ||
                isOnSameLine(metroLine2, start, end) ||
                isOnSameLine(metroLine3, start, end) ||
                isOnSameLine(cairoKitKateBranch, start, end);

    }

    private boolean isOnSameLine(ArrayList<String> line, String start, String end) {
        return line.contains(start) && line.contains(end);
    }

    private void getPathIfSameLine() {
        ArrayList<String> path = new ArrayList<>();

        if (metroLine1.contains(start) && metroLine1.contains(end)) {
            path = getSingleRouht(metroLine1, start, end);
        } else if (metroLine2.contains(start) && metroLine2.contains(end)) {
            path = getSingleRouht(metroLine2, start, end);
        } else if (metroLine3.contains(start) && metroLine3.contains(end)) {
            path = getSingleRouht(metroLine3, start, end);
        } else if (cairoKitKateBranch.contains(start) && cairoKitKateBranch.contains(end)) {
            path = getSingleRouht(cairoKitKateBranch, start, end);
        }

        routes.add(path);
    }

    private ArrayList<String> getSingleRouht(ArrayList<String> line, String start, String end) {
        int startIndex = line.indexOf(start);
        int endIndex = line.indexOf(end);
        ArrayList<String> subListPath;

        if (startIndex < endIndex) {
            subListPath = new ArrayList<>(line.subList(startIndex, endIndex + 1));
            direction = "Take Direction to " + getLast(line);
        } else {
            direction = "Take Direction to " + getFirst(line);
            subListPath = new ArrayList<>(line.subList(endIndex, startIndex + 1));
            Collections.reverse(subListPath);
        }

        return subListPath;
    }

    private void searchInManyLines() {

        if (metroLine1.contains(start) && metroLine2.contains(end)) {
            searchRoutesFromLine1ToLine2(start, end);
        } else if (metroLine2.contains(start) && metroLine1.contains(end)) {
            searchRoutesFromLine2ToLine1(start, end);
        } else if (metroLine2.contains(start) && metroLine3.contains(end)) {
            searchRoutesFromLine2ToLine3(start, end);
        } else if (metroLine3.contains(start) && metroLine2.contains(end)) {
            searchRoutesFromLine3ToLine2(start, end);
        } else if (metroLine1.contains(start) && metroLine3.contains(end)) {
            searchRoutesFromLine1ToLine3(start, end);
        } else if (metroLine3.contains(start) && metroLine1.contains(end)) {
            searchRoutesFromLine3ToLine1(start, end);
        } else if (metroLine1.contains(start) && cairoKitKateBranch.contains(end)) {
            searchRoutesFromLine1ToCairoKitKateBranch(start, end);
        } else if (cairoKitKateBranch.contains(start) && metroLine1.contains(end)) {
            searchRoutesFromCairoKitKateBranchToLine1(start, end);
        } else if (metroLine2.contains(start) && cairoKitKateBranch.contains(end)) {
            searchRoutesFromLine2ToCairoKitKateBranch(start, end);
        } else if (cairoKitKateBranch.contains(start) && metroLine2.contains(end)) {
            searchRoutesFromCairoKitKateBranchToLine2(start, end);
        }
    }

    private void searchRoutesFromCairoKitKateBranchToLine2(String start, String end) {
        int startIndex = cairoKitKateBranch.indexOf(start);
        int endIndex = metroLine2.indexOf(end);
        int attabaIndexAtLine2 = metroLine2.indexOf("Attaba");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        int kitKateIndexAtCairoBranch = cairoKitKateBranch.indexOf("KitKate");
        int cairoUniversityIndexAtCairoBranch = cairoKitKateBranch.indexOf("CairoUniversity");
        int attabaIndexAtLine3 = metroLine3.indexOf("Attaba");
        int kitKateIndexAtLine3 = metroLine3.indexOf("KitKate");
        ArrayList<String> attabaLine = new ArrayList<>();
        ArrayList<String> cairoLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();

        // Option 1: Change at Attaba
        directionForFirstRoute.append("Change Direction to KitKate ");

        subLine.addAll(cairoKitKateBranch.subList(kitKateIndexAtCairoBranch, startIndex + 1));
        Collections.reverse(subLine);
        attabaLine.addAll(subLine);
        subLine.clear();


        attabaLine.addAll(metroLine3.subList(kitKateIndexAtLine3 + 1, attabaIndexAtLine3));
        directionForFirstRoute.append("Then Take Direction to Adly Mansour and Change Direction at Attaba ");
        if (endIndex < attabaIndexAtLine2) {
            directionForFirstRoute.append("Then Take Direction to ShobraEl-Kheimar  ");
            subLine.addAll(metroLine2.subList(endIndex, attabaIndexAtLine2 + 1));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
        } else {
            directionForFirstRoute.append("Then Take Direction to El-Mounib  ");
            attabaLine.addAll(metroLine2.subList(attabaIndexAtLine2, endIndex + 1));

        }
        // Option 2: Change at Cairo University
        directionForSecondRoute.append("change Direction to Cairo University ");
        cairoLine.addAll(cairoKitKateBranch.subList(startIndex, cairoUniversityIndexAtCairoBranch + 1));
        if (endIndex < cairoIndexAtLine2) {
            directionForSecondRoute.append("Then Take Direction to ShobraEl-Kheimaa");
            subLine.addAll(metroLine2.subList(endIndex, cairoIndexAtLine2));
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();
        } else {
            directionForSecondRoute.append("Then Take Direction to El-Mounib");
            cairoLine.addAll(metroLine2.subList(cairoIndexAtLine2 + 1, endIndex + 1));

        }
        routes.add(attabaLine);
        routes.add(cairoLine);
    }

    private void searchRoutesFromLine2ToCairoKitKateBranch(String start, String end) {
        int startIndex = metroLine2.indexOf(start);
        int endIndex = cairoKitKateBranch.indexOf(end);
        int attabaIndexAtLine2 = metroLine2.indexOf("Attaba");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        ArrayList<String> attabaLine = new ArrayList<>();
        ArrayList<String> cairoLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();
        // ToDO operation on Line 2
        if (startIndex > attabaIndexAtLine2 && startIndex > cairoIndexAtLine2) {
            directionForFirstRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at El-Attaba ");
            directionForSecondRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at CairoUniversity ");
            subLine.addAll(metroLine2.subList(attabaIndexAtLine2 + 1, startIndex + 1));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine2.subList(cairoIndexAtLine2 + 1, startIndex + 1));
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();
        } else if (startIndex < attabaIndexAtLine2 && startIndex < cairoIndexAtLine2) {
            directionForFirstRoute.append("Take Direction to El-Mounib and Change Direction at El-Attaba ");
            directionForSecondRoute.append("Take Direction to El-Mounib and Change Direction at CairoUniversity ");
            attabaLine.addAll(metroLine2.subList(startIndex, attabaIndexAtLine2));
            cairoLine.addAll(metroLine2.subList(startIndex, cairoIndexAtLine2));
        } else if (startIndex > attabaIndexAtLine2 && startIndex < cairoIndexAtLine2) {
            directionForFirstRoute.append("Take Direction to  ShobraEl-Kheima and Change Direction at El-Attaba ");
            directionForSecondRoute.append("Take Direction to El-Mounib and Change Direction at CairoUniversity ");
            subLine.addAll(metroLine2.subList(attabaIndexAtLine2 + 1, startIndex));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
            cairoLine.addAll(metroLine2.subList(startIndex, cairoIndexAtLine2));
        } else {
            directionForFirstRoute.append("You are At Attaba");
            directionForSecondRoute.append("You are At CairoUniversity");
        }

        //ToDO operations on line 3 with attab line
        directionForFirstRoute.append("Then Take Direction to Road El FargCorr and Change Direction at KitKate ");
        int attabaIndexAtLine3 = metroLine3.indexOf("Attaba");
        int kitKateIndexAtLine3 = metroLine3.indexOf("KitKate");
        int kitkateIndexAtCairoBranch = cairoKitKateBranch.indexOf("KitKate");
        int cairoIndexAtCairoBranch = cairoKitKateBranch.indexOf("CairoUniversity");
        subLine.addAll(metroLine3.subList(kitKateIndexAtLine3 + 1, attabaIndexAtLine3 + 1));
        Collections.reverse(subLine);
        attabaLine.addAll(subLine);
        subLine.clear();
        attabaLine.addAll(cairoKitKateBranch.subList(kitkateIndexAtCairoBranch, endIndex + 1));

        //TODO Operations on cairoBranch with cairoLine
        directionForSecondRoute.append("Then Take Direction to KitKate ");
        subLine.addAll(cairoKitKateBranch.subList(endIndex, cairoIndexAtCairoBranch + 1));
        Collections.reverse(subLine);
        cairoLine.addAll(subLine);
        subLine.clear();

        routes.add(attabaLine);
        routes.add(cairoLine);
    }

    private void searchRoutesFromCairoKitKateBranchToLine1(String start, String end) {
        int startIndex = cairoKitKateBranch.indexOf(start);
        int cairoIndexAtKitKatCairoUniversityBranch = cairoKitKateBranch.indexOf("CairoUniversity");
        int kitKateIndexAtCairoBranch = cairoKitKateBranch.indexOf("KitKate");

        ArrayList<String> nasserLine = new ArrayList<>();
        ArrayList<String> sadatLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();

        // Operations at KitKat Cairo University line for Sadat route
        if (startIndex < cairoIndexAtKitKatCairoUniversityBranch) {
            subLine.addAll(cairoKitKateBranch.subList(startIndex, cairoIndexAtKitKatCairoUniversityBranch + 1));
            sadatLine.addAll(subLine);
            subLine.clear();
        } else if (startIndex > cairoIndexAtKitKatCairoUniversityBranch) {
            subLine.addAll(cairoKitKateBranch.subList(cairoIndexAtKitKatCairoUniversityBranch, startIndex + 1));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
        }
        directionForSecondRoute.append("Change Direction at CairoUniversity ");

        // Operations at Line 2 for Sadat route
        int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        if (cairoIndexAtLine2 < sadatIndexAtLine2) {
            sadatLine.addAll(metroLine2.subList(cairoIndexAtLine2 + 1, sadatIndexAtLine2 + 1));
        } else {
            subLine.addAll(metroLine2.subList(sadatIndexAtLine2, cairoIndexAtLine2));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
        }
        directionForSecondRoute.append("then Take Direction to ShobraEl-Kheima and Change Direction at Sadat   ");

        // Operations at Line 1 for Sadat route
        int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");
        int endIndexAtLine1 = metroLine1.indexOf(end);
        if (endIndexAtLine1 > sadatIndexAtLine1) {
            sadatLine.addAll(metroLine1.subList(sadatIndexAtLine1, endIndexAtLine1 + 1));
            directionForSecondRoute.append("then Take Direction to El-Marg ");
        } else if (endIndexAtLine1 < sadatIndexAtLine1) {
            directionForSecondRoute.append("then Take Direction to Helwan ");
            subLine.addAll(metroLine1.subList(endIndexAtLine1, sadatIndexAtLine1 + 1));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
        }

        // Operations at KitKat Cairo University line for Nasser route
        directionForFirstRoute.append("Change Direction at KitKate ");
        if (startIndex < kitKateIndexAtCairoBranch) {
            subLine.addAll(cairoKitKateBranch.subList(startIndex, kitKateIndexAtCairoBranch + 1));
            nasserLine.addAll(subLine);
            subLine.clear();
        } else if (startIndex > kitKateIndexAtCairoBranch) {
            subLine.addAll(cairoKitKateBranch.subList(kitKateIndexAtCairoBranch, startIndex + 1));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
            subLine.clear();
        }

        // Operations at Line 3 for Nasser route
        directionForFirstRoute.append("then Take Direction to Adly Mansour and Change Direction at Nasser   ");
        int nasserIndexAtLine3 = metroLine3.indexOf("Nasser");
        int kitKateIndexAtLine3 = metroLine3.indexOf("KitKate");

        nasserLine.addAll(metroLine3.subList(kitKateIndexAtLine3 + 1, nasserIndexAtLine3 + 1));


        // Operations at Line 1 for Nasser route
        int nasserIndexAtLine1 = metroLine1.indexOf("Nasser");

        if (endIndexAtLine1 > nasserIndexAtLine1) {
            directionForFirstRoute.append("then Take Direction to El-Marg ");
            nasserLine.addAll(metroLine1.subList(nasserIndexAtLine1, endIndexAtLine1 + 1));
        } else if (endIndexAtLine1 < nasserIndexAtLine1) {
            directionForFirstRoute.append("then Take Direction to Helwan ");
            subLine.addAll(metroLine1.subList(endIndexAtLine1, nasserIndexAtLine1 + 1));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
            subLine.clear();
        }

        routes.add(nasserLine);
        routes.add(sadatLine);
    }

    private void searchRoutesFromLine1ToCairoKitKateBranch(String start, String end) {
        int startIndex = metroLine1.indexOf(start);
        int nasserIndexAtLine1 = metroLine1.indexOf("Nasser");
        int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");

        ArrayList<String> nasserLine = new ArrayList<>();
        ArrayList<String> sadatLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();

        // Operations at Line 1
        if (startIndex < nasserIndexAtLine1 && startIndex < sadatIndexAtLine1) {
            directionForFirstRoute.append("Take Direction to El-Marg and Change Direction at El-Nasser ");
            directionForSecondRoute.append("Take Direction to El-Marg and Change Direction at El-Sadat ");
            nasserLine.addAll(metroLine1.subList(startIndex, nasserIndexAtLine1));
            sadatLine.addAll(metroLine1.subList(startIndex, sadatIndexAtLine1));
        } else if (startIndex > nasserIndexAtLine1 && startIndex > sadatIndexAtLine1) {
            directionForFirstRoute.append("Take Direction to Helwan and Change Direction at El-Nasser ");
            directionForSecondRoute.append("Take Direction to Helwan and Change Direction at El-Sadat ");
            subLine.addAll(metroLine1.subList(sadatIndexAtLine1 + 1, startIndex));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine1.subList(nasserIndexAtLine1 + 1, startIndex));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
        } else {
            if (startIndex == nasserIndexAtLine1) {
                directionForFirstRoute.append("You are At Nasser");
                directionForSecondRoute.append("You are At Sadat");
            } else {
                directionForFirstRoute.append("You are At " + start);
                nasserLine.addAll(metroLine1.subList(sadatIndexAtLine1, nasserIndexAtLine1));
                directionForSecondRoute.append("You are At Sadat");
            }

        }

        // Operations at Line 2 for Sadat route
        int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        directionForSecondRoute.append("Then Take Direction to EL-Mounib and Change Direction at CairoUniversity ");
        sadatLine.addAll(metroLine2.subList(sadatIndexAtLine2, cairoIndexAtLine2));

        // Operations at Cairo KitKate branch for Sadat route
        int endIndexAtCairoKitKateBranch = cairoKitKateBranch.indexOf(end);
        int cairoIndexAtCairoKitKateBranch = cairoKitKateBranch.indexOf("CairoUniversity");
        subLine.addAll(cairoKitKateBranch.subList(endIndexAtCairoKitKateBranch, cairoIndexAtCairoKitKateBranch + 1));
        Collections.reverse(subLine);
        sadatLine.addAll(subLine);
        subLine.clear();

        // Operations at Line 3 for Nasser route
        directionForFirstRoute.append("Take Direction to Road El FargCorr and Change Direction at KitKate ");
        int nasserIndexAtLine3 = metroLine3.indexOf("Nasser");
        int kitKateIndexAtLine3 = metroLine3.indexOf("KitKate");
        subLine.addAll(metroLine3.subList(kitKateIndexAtLine3 + 1, nasserIndexAtLine3 + 1));
        Collections.reverse(subLine);
        nasserLine.addAll(subLine);
        subLine.clear();
        int kitKateIndexAtCairoKitKateBranch = cairoKitKateBranch.indexOf("KitKate");

        // Operations at Cairo KitKate branch for Nasser route
        nasserLine.addAll(cairoKitKateBranch.subList(kitKateIndexAtCairoKitKateBranch, endIndexAtCairoKitKateBranch + 1));

        routes.add(nasserLine);
        routes.add(sadatLine);
    }

    private void searchRoutesFromLine1ToLine3(String start, String end) {
        int startIndex = metroLine1.indexOf(start);
        int nasserIndexAtLine1 = metroLine1.indexOf("Nasser");
        int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");
        ArrayList<String> nasserLine = new ArrayList<>();
        ArrayList<String> sadatLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();
        // ToDo operations in Line 1
        if (startIndex < nasserIndexAtLine1 && startIndex < sadatIndexAtLine1) {
            directionForFirstRoute.append("Take Direction to El-Marg and Change Direction at El-Nasser ");
            directionForSecondRoute.append("Take Direction to El-Marg and Change Direction at El-Sadat Then Take Direction to El Mounib  And Change Direction at CairoUniversity");
            nasserLine.addAll(metroLine1.subList(startIndex, nasserIndexAtLine1));
            sadatLine.addAll(metroLine1.subList(startIndex, sadatIndexAtLine1));
        } else if (startIndex > nasserIndexAtLine1 && startIndex > sadatIndexAtLine1) {
            directionForFirstRoute.append("Take Direction to Helwan and Change Direction at El-Nasser ");
            directionForSecondRoute.append("Take Direction to Helwan and Change Direction at El-Sadat  Then Take Direction to El Mounib  And Change Direction at CairoUniversity");
            subLine.addAll(metroLine1.subList(sadatIndexAtLine1 + 1, startIndex));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine1.subList(nasserIndexAtLine1 + 1, startIndex));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
        } else {
            // you are at sadat or shohadaa
            directionForFirstRoute.append("You are At Nasser");
            directionForSecondRoute.append("You are At Sadat");

        }
        // ToDo operations in Line 3
        int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        sadatLine.addAll(metroLine2.subList(sadatIndexAtLine2, cairoIndexAtLine2));
        subLine.addAll(cairoKitKateBranch);
        Collections.reverse(subLine);
        sadatLine.addAll(subLine);
        subLine.clear();
        int endIndex = metroLine3.indexOf(end);
        int nasserIndexAtLine3 = metroLine3.indexOf("Nasser");
        int kitKateIndex = metroLine3.indexOf("KitKate");
        if (endIndex > nasserIndexAtLine3 && endIndex > kitKateIndex) {
            directionForFirstRoute.append("And then Take Direction to Adly Mansour");
            directionForSecondRoute.append("And then Take Direction to Adly Mansour");
            nasserLine.addAll(metroLine3.subList(nasserIndexAtLine3, endIndex + 1));
            sadatLine.addAll(metroLine3.subList(kitKateIndex + 1, endIndex + 1));
        } else if (endIndex < nasserIndexAtLine3 && endIndex < kitKateIndex) {
            directionForFirstRoute.append("And then Take Direction to Road El FargCorr");
            directionForSecondRoute.append("And then Take Direction to Road El FargCorr");
            subLine.addAll(metroLine3.subList(endIndex, nasserIndexAtLine3 + 1));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine3.subList(endIndex, kitKateIndex));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
        } else if (endIndex > kitKateIndex && endIndex < nasserIndexAtLine3) {
            directionForFirstRoute.append("And then Take Direction to Adly Mansour");
            directionForSecondRoute.append("And then Take Direction to Road El FargCorr");
            nasserLine.addAll(metroLine3.subList(endIndex, nasserIndexAtLine3 + 1));
            subLine.addAll(metroLine3.subList(kitKateIndex + 1, endIndex + 1));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
        } else {
            nasserLine.add("Nasser");
            sadatLine.add("KitKate");
        }
        routes.add(nasserLine);
        routes.add(sadatLine);
    }

    private void searchRoutesFromLine3ToLine1(String start, String end) {
        int startIndex = metroLine3.indexOf(start);
        int nasserIndexAtLine3 = metroLine3.indexOf("Nasser");
        int kitKateIndex = metroLine3.indexOf("KitKate");
        ArrayList<String> nasserLine = new ArrayList<>();
        ArrayList<String> kitKateLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();

        // Operations at Line 3
        if (startIndex < nasserIndexAtLine3 && startIndex < kitKateIndex) {
            directionForFirstRoute.append("Take Direction to Adly Mansour and Change Direction at El-Nasser ");
            directionForSecondRoute.append("Take Direction to Adly Mansour and Change Direction at KitKate And Change Direction at CairoUniversity And Take Direction to ShobraEl-Kheima");
            nasserLine.addAll(metroLine3.subList(startIndex, nasserIndexAtLine3));
            kitKateLine.addAll(metroLine3.subList(startIndex, kitKateIndex));
        } else if (startIndex > nasserIndexAtLine3 && startIndex > kitKateIndex) {
            directionForFirstRoute.append("Take Direction to Road El FargCorr and Change Direction at El-Nasser ");
            directionForSecondRoute.append("Take Direction to Road El FargCorr and Change Direction at KitKate And Change Direction at CairoUniversity And Take Direction to ShobraEl-Kheima");
            subLine.addAll(metroLine3.subList(kitKateIndex + 1, startIndex + 1));
            Collections.reverse(subLine);
            kitKateLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine3.subList(nasserIndexAtLine3 + 1, startIndex + 1));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
        } else {
            // You are at Nasser or KitKate
            directionForFirstRoute.append("You are At Nasser");
            directionForSecondRoute.append("You are At KitKate");
        }

        // Operations at Line 2
        int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        kitKateLine.addAll(metroLine2.subList(sadatIndexAtLine2, cairoIndexAtLine2));
        subLine.addAll(cairoKitKateBranch);
        Collections.reverse(subLine);
        kitKateLine.addAll(subLine);
        subLine.clear();

        // Operations at Line 1
        int endIndex = metroLine1.indexOf(end);
        int nasserIndexAtLine1 = metroLine1.indexOf("Nasser");
        int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");
        if (endIndex > nasserIndexAtLine1 && endIndex > sadatIndexAtLine1) {
            directionForFirstRoute.append("And then Take Direction to El-Marg");
            directionForSecondRoute.append("And then Take Direction to El-Marg");
            nasserLine.addAll(metroLine1.subList(nasserIndexAtLine1, endIndex + 1));
            kitKateLine.addAll(metroLine1.subList(sadatIndexAtLine1 + 1, endIndex + 1));
        } else if (endIndex < nasserIndexAtLine1 && endIndex < sadatIndexAtLine1) {
            directionForFirstRoute.append("And then Take Direction to Helwan");
            directionForSecondRoute.append("And then Take Direction to Helwan ");
            subLine.addAll(metroLine1.subList(endIndex, nasserIndexAtLine1 + 1));
            Collections.reverse(subLine);
            nasserLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine1.subList(endIndex, sadatIndexAtLine1));
            Collections.reverse(subLine);
            kitKateLine.addAll(subLine);
            subLine.clear();
        } else {
            nasserLine.add("Nasser");
            kitKateLine.add("KitKate");
        }

        routes.add(nasserLine);
        routes.add(kitKateLine);
    }

    private void searchRoutesFromLine2ToLine3(String start, String end) {
        int attabaIndexAtLine2 = metroLine2.indexOf("Attaba");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        int attabaIndexAtLine3 = metroLine3.indexOf("Attaba");
        int kitKateIndex = metroLine3.indexOf("KitKate");
        int startIndex = metroLine2.indexOf(start);
        int endIndex = metroLine3.indexOf(end);
        ArrayList<String> attabaLine = new ArrayList<>();
        ArrayList<String> cairoLine = new ArrayList<>();
        ArrayList<String> subLine = new ArrayList<>();
        //ToDo  do Operations at line 2
        if (startIndex < attabaIndexAtLine2 && startIndex < cairoIndexAtLine2) {
            directionForFirstRoute.append("Take Direction to El-Mounib and Change Direction at El-Attaba \n ");
            directionForSecondRoute.append("Take Direction to El-Mounib and Change Direction at CairoUniversity \n And Change Direction at KitKate");
            attabaLine.addAll(metroLine2.subList(startIndex, attabaIndexAtLine2));
            cairoLine.addAll(metroLine2.subList(startIndex, cairoIndexAtLine2));
            subLine.addAll(cairoKitKateBranch);
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();
        } else if (startIndex > attabaIndexAtLine2 && startIndex > cairoIndexAtLine2) {
            directionForFirstRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at El-Attaba \n ");
            directionForSecondRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at CairoUniversity \n And Change Direction at KitKate");
            subLine.addAll(metroLine2.subList(attabaIndexAtLine2 + 1, startIndex + 1));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine2.subList(cairoIndexAtLine2 + 1, startIndex + 1));
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.addAll(cairoKitKateBranch);
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();
        } else if (startIndex > attabaIndexAtLine2 && startIndex < cairoIndexAtLine2) {
            directionForFirstRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at El-Attaba \n ");
            directionForSecondRoute.append("Take Direction to El-Mounib and Change Direction at CairoUniversity \n And Change Direction at KitKate");
            subLine.addAll(metroLine2.subList(attabaIndexAtLine2 + 1, startIndex + 1));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
            cairoLine.addAll(metroLine2.subList(startIndex, cairoIndexAtLine2));
            subLine.addAll(cairoKitKateBranch);
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();

        } else {
            // you are at sadat or shohadaa
            directionForFirstRoute.append("You are At Attaba");
            directionForSecondRoute.append("You are At CairoUniversity");
        }
        //ToDo  do Operations at line 3
        if (endIndex > attabaIndexAtLine3 && endIndex > kitKateIndex) {
            directionForFirstRoute.append("And then Take Direction to Adly Mansour");
            directionForSecondRoute.append("And then Take Direction to Adly Mansour");
            attabaLine.addAll(metroLine3.subList(attabaIndexAtLine3, endIndex + 1));
            cairoLine.addAll(metroLine3.subList(kitKateIndex + 1, endIndex + 1));
        } else if (endIndex < attabaIndexAtLine3 && endIndex < kitKateIndex) {
            directionForFirstRoute.append("And then Take Direction to Road El FargCorr");
            directionForSecondRoute.append("And then Take Direction to Road El FargCorr");
            subLine.addAll(metroLine3.subList(endIndex, attabaIndexAtLine3 + 1));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine3.subList(endIndex, kitKateIndex));
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();
        } else if (endIndex > kitKateIndex && endIndex < attabaIndexAtLine3) {
            directionForFirstRoute.append("And then Take Direction to Adly Mansour");
            directionForSecondRoute.append("And then Take Direction to Road El FargCorr");
            attabaLine.addAll(metroLine3.subList(endIndex, attabaIndexAtLine3 + 1));
            subLine.addAll(metroLine3.subList(kitKateIndex + 1, endIndex + 1));
            Collections.reverse(subLine);
            cairoLine.addAll(subLine);
            subLine.clear();
        } else {
            attabaLine.add("Attaba");
            cairoLine.add("KitKate");
        }
        routes.add(attabaLine);
        routes.add(cairoLine);

    }

    private void searchRoutesFromLine3ToLine2(String start, String end) {
        ArrayList<String> attabaLine = new ArrayList<>();
        ArrayList<String> kitKateLine = new ArrayList<>();
        List<String> subLine = new ArrayList<>();
        directionForFirstRoute = new StringBuilder();
        directionForSecondRoute = new StringBuilder();

        int startIndex = metroLine3.indexOf(start);
        int attabaIndexAtLine3 = metroLine3.indexOf("Attaba");
        int kitKateIndex = metroLine3.indexOf("KitKate");
        int attabaIndexAtLine2 = metroLine2.indexOf("Attaba");
        int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
        int endIndex = metroLine2.indexOf(end);

        // Operations at Line 3
        if (startIndex < attabaIndexAtLine3 && startIndex < kitKateIndex) {
            attabaLine.addAll(metroLine3.subList(startIndex, attabaIndexAtLine3 + 1));
            kitKateLine.addAll(metroLine3.subList(startIndex, kitKateIndex + 1));
            directionForFirstRoute.append("Take Direction to Adly Mansour and Change Direction at Attaba \n");
            directionForSecondRoute.append("Take Direction to Adly Mansour and Change Direction at KitKate \n");
        } else if (startIndex > attabaIndexAtLine3 && startIndex > kitKateIndex) {
            attabaLine.addAll(metroLine3.subList(attabaIndexAtLine3 + 1, startIndex + 1));
            kitKateLine.addAll(metroLine3.subList(kitKateIndex + 1, startIndex + 1));
            Collections.reverse(attabaLine);
            Collections.reverse(kitKateLine);
            directionForFirstRoute.append("Take Direction to Road El FargCorr and Change Direction at Attaba \n");
            directionForSecondRoute.append("Take Direction to Road El FargCorr and Change Direction at KitKate \n");
        } else if (startIndex > kitKateIndex && startIndex < attabaIndexAtLine3) {
            attabaLine.addAll(metroLine3.subList(startIndex, attabaIndexAtLine3 + 1));
            kitKateLine.addAll(metroLine3.subList(kitKateIndex + 1, startIndex + 1));
            Collections.reverse(kitKateLine);
            directionForFirstRoute.append("Take Direction to Adly Mansour and Change Direction at Attaba \n");
            directionForSecondRoute.append("Take Direction to Road El FargCorr and Change Direction at KitKate \n");
        } else {
            // You are at Attaba or KitKate
            if (startIndex == attabaIndexAtLine3) {
                directionForFirstRoute.append("you are At Attaba ");
                directionForSecondRoute.append("Take Direction to Road El FargCorr and Change Direction at KitKate ");
                subLine.addAll(metroLine3.subList(kitKateIndex + 1, startIndex + 1));
                Collections.reverse(subLine);
                kitKateLine.addAll(subLine);
                subLine.clear();
                kitKateLine.addAll(cairoKitKateBranch);
                //   attabaLine.add("Attaba");  // attab will be added at line 2

            } else {
                directionForFirstRoute.append("take Direction to Adly Mansour and change Direction at Attaba ");
                directionForSecondRoute.append("You are At KitKate ,Then Change Direction At CairoUniversity\n");
                attabaLine.addAll(metroLine3.subList(kitKateIndex, attabaIndexAtLine3));
                // kitkate will be added at caireKitake Branch
                kitKateLine.addAll(cairoKitKateBranch);

            }
        }

        //ToDo Operations at Line 2
        if (endIndex < attabaIndexAtLine2 && endIndex < cairoIndexAtLine2) {
            subLine.addAll(metroLine2.subList(endIndex, attabaIndexAtLine2 + 1));
            Collections.reverse(subLine);
            attabaLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine2.subList(endIndex, cairoIndexAtLine2 + 1));
            Collections.reverse(subLine);
            kitKateLine.addAll(subLine);
            subLine.clear();
            directionForFirstRoute.append("And Then Take Direction to ShobraEl-Kheima ");
            directionForSecondRoute.append("And Then Take Direction to ShobraEl-Kheima ");
        } else if (endIndex > attabaIndexAtLine2 && endIndex > cairoIndexAtLine2) {
            attabaLine.addAll(metroLine2.subList(attabaIndexAtLine2, endIndex + 1));
            kitKateLine.addAll(metroLine2.subList(cairoIndexAtLine2 + 1, endIndex + 1));
            directionForFirstRoute.append("And Then Take Direction to El-Mounib ");
            directionForSecondRoute.append("And Then Take Direction to El-Mounib ");
        } else if (endIndex > cairoIndexAtLine2 && endIndex < attabaIndexAtLine2) {
            subLine.addAll(metroLine2.subList(cairoIndexAtLine2 + 1, endIndex + 1));
            Collections.reverse(subLine);
            kitKateLine.addAll(subLine);
            subLine.clear();
            attabaLine.addAll(metroLine2.subList(endIndex, attabaIndexAtLine2 + 1));
            Collections.reverse(attabaLine);
            directionForFirstRoute.append("And Then Take Direction to ShobraEl-Kheima ");
            directionForSecondRoute.append("And Then Take Direction to El-Mounib ");
        } else {
            // You are at Attaba or CairoUniversity
            //   directionForFirstRoute.append("You are At Attaba");
            //    directionForSecondRoute.append("You are At CairoUniversity");
            attabaLine.add("Attaba");
            kitKateLine.add("CairoUniversity");
        }

        routes.add(attabaLine);
        routes.add(kitKateLine);
    }

    private void searchRoutesFromLine1ToLine2(String start, String end) {
        ArrayList<String> sadatLine = new ArrayList<>();
        ArrayList<String> shohadaaLine = new ArrayList<>();
        List<String> subLine = new ArrayList<>();
        directionForFirstRoute = new StringBuilder();
        directionForSecondRoute = new StringBuilder();
        int startIndex = metroLine1.indexOf(start);
        int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");
        int ShohadaaIndexAtLine1 = metroLine1.indexOf("Al-Shohadaa");
        int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
        int shohadaaIndexAtLine2 = metroLine2.indexOf("Al-Shohadaa");
        int endIndex = metroLine2.indexOf(end);
        //ToDo if do Operations at line 1
        if (startIndex < sadatIndexAtLine1 && startIndex < ShohadaaIndexAtLine1) {
            sadatLine.addAll(metroLine1.subList(startIndex, sadatIndexAtLine1));
            shohadaaLine.addAll(metroLine1.subList(startIndex, ShohadaaIndexAtLine1));
            directionForFirstRoute.append("Take Direction to El-Marg and Change Direction at El-Sadat \n");
            directionForSecondRoute.append("Take Direction to El-Marg and Change Direction at El-Shohadaa \n");
        } else if (startIndex > ShohadaaIndexAtLine1 && startIndex > sadatIndexAtLine1) {
            sadatLine.addAll(metroLine1.subList(sadatIndexAtLine1 + 1, startIndex));
            shohadaaLine.addAll(metroLine1.subList(ShohadaaIndexAtLine1 + 1, startIndex));
            Collections.reverse(shohadaaLine);
            Collections.reverse(sadatLine);
            directionForFirstRoute.append("Take Direction to Helwan and Change Direction at El-Sadat \n");
            directionForSecondRoute.append("Take Direction to Helwan and Change Direction at El-Shohadaa \n");
        } else if (startIndex > sadatIndexAtLine1 && startIndex < ShohadaaIndexAtLine1) {
            shohadaaLine.addAll(metroLine1.subList(startIndex, ShohadaaIndexAtLine1));
            sadatLine.addAll(metroLine1.subList(sadatIndexAtLine1 + 1, startIndex));
            Collections.reverse(sadatLine);
        } else {
            // you are at sadat or shohadaa
            directionForFirstRoute.append("You are At sadat");
            directionForSecondRoute.append("You are At shohadaa");
        }

        //ToDo  do Operations at line 2
        if (endIndex < sadatIndexAtLine2 && endIndex < shohadaaIndexAtLine2) {
            subLine.addAll(metroLine2.subList(endIndex, shohadaaIndexAtLine2 + 1));
            Collections.reverse(subLine);
            shohadaaLine.addAll(subLine);
            subLine.clear();
            subLine.addAll(metroLine2.subList(endIndex, sadatIndexAtLine2 + 1));
            Collections.reverse(subLine);
            sadatLine.addAll(subLine);
            subLine.clear();
            directionForFirstRoute.append("And Then Take Direction to ShobraEl-Kheima ");
            directionForSecondRoute.append("And Then Take Direction to ShobraEl-Kheima ");
        } else if (endIndex > sadatIndexAtLine2 && endIndex > shohadaaIndexAtLine2) {
            sadatLine.addAll(metroLine2.subList(sadatIndexAtLine2, endIndex + 1));
            shohadaaLine.addAll(metroLine2.subList(shohadaaIndexAtLine2, endIndex + 1));
            directionForFirstRoute.append("And Then Take Direction to El-Mounib ");
            directionForSecondRoute.append("And Then Take Direction to El-Mounib ");
        } else if (endIndex > shohadaaIndexAtLine2 && endIndex < sadatIndexAtLine2) {
            sadatLine.addAll(metroLine2.subList(endIndex, sadatIndexAtLine2 + 1));
            subLine.addAll(metroLine2.subList(shohadaaIndexAtLine2, endIndex + 1));
            Collections.reverse(subLine);
            shohadaaLine.addAll(subLine);
            subLine.clear();
            directionForFirstRoute.append("And Then Take Direction to El-Mounib ");
            directionForSecondRoute.append("And Then Take Direction to ShobraEl-Kheima ");
        } else {
            // you are at sadat or shohadaa
            //endIndex== sadat or shohadaa
            directionForFirstRoute.append("You are At sadat");
            directionForSecondRoute.append("You are At shohadaa");

            sadatLine.add("Sadat");
            shohadaaLine.add("Al-Shohadaa");
        }
        routes.add(sadatLine);
        routes.add(shohadaaLine);

    }

    private void searchRoutesFromLine2ToLine1(String start, String end) {
        ArrayList<String> sadatLine = new ArrayList<>();
        ArrayList<String> shohadaaLine = new ArrayList<>();
        List<String> subLine = new ArrayList<>();
        directionForFirstRoute = new StringBuilder();
        directionForSecondRoute = new StringBuilder();

        if (metroLine2.contains(start) && metroLine1.contains(end)) {
            int startIndex = metroLine2.indexOf(start);
            int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
            int shohadaaIndexAtLine2 = metroLine2.indexOf("Al-Shohadaa");
            int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");
            int shohadaaIndexAtLine1 = metroLine1.indexOf("Al-Shohadaa");
            int endIndex = metroLine1.indexOf(end);

            // Operations at Line 2
            if (startIndex < sadatIndexAtLine2 && startIndex < shohadaaIndexAtLine2) {
                sadatLine.addAll(metroLine2.subList(startIndex, sadatIndexAtLine2));
                shohadaaLine.addAll(metroLine2.subList(startIndex, shohadaaIndexAtLine2));
                directionForFirstRoute.append("Take Direction to El-Mounib and Change Direction at El-Sadat \n");
                directionForSecondRoute.append("Take Direction to El-Mounib and Change Direction at El-Shohadaa \n");
            } else if (startIndex > sadatIndexAtLine2 && startIndex > shohadaaIndexAtLine2) {
                sadatLine.addAll(metroLine2.subList(sadatIndexAtLine2 + 1, startIndex + 1));
                shohadaaLine.addAll(metroLine2.subList(shohadaaIndexAtLine2 + 1, startIndex + 1));
                Collections.reverse(sadatLine);
                Collections.reverse(shohadaaLine);
                directionForFirstRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at El-Sadat \n");
                directionForSecondRoute.append("Take Direction to ShobraEl-Kheima and Change Direction at El-Shohadaa \n");
            } else if (startIndex > shohadaaIndexAtLine2 && startIndex < sadatIndexAtLine2) {
                sadatLine.addAll(metroLine2.subList(sadatIndexAtLine2 + 1, startIndex + 1));
                shohadaaLine.addAll(metroLine2.subList(startIndex, shohadaaIndexAtLine2));
                Collections.reverse(sadatLine);
            } else {
                directionForFirstRoute.append("You are At sadat");
                directionForSecondRoute.append("You are At shohadaa");
            }

            // Operations at Line 1
            if (endIndex < sadatIndexAtLine1 && endIndex < shohadaaIndexAtLine1) {
                subLine.addAll(metroLine1.subList(endIndex, sadatIndexAtLine1 + 1));
                Collections.reverse(subLine);
                sadatLine.addAll(subLine);
                subLine.clear();
                subLine.addAll(metroLine1.subList(endIndex, shohadaaIndexAtLine1 + 1));
                Collections.reverse(subLine);
                shohadaaLine.addAll(subLine);
                subLine.clear();
                directionForFirstRoute.append("And Then Take Direction to El-Marg ");
                directionForSecondRoute.append("And Then Take Direction to El-Marg ");
            } else if (endIndex > sadatIndexAtLine1 && endIndex > shohadaaIndexAtLine1) {
                sadatLine.addAll(metroLine1.subList(sadatIndexAtLine1, endIndex + 1));
                shohadaaLine.addAll(metroLine1.subList(shohadaaIndexAtLine1, endIndex + 1));
                directionForFirstRoute.append("And Then Take Direction to Helwan ");
                directionForSecondRoute.append("And Then Take Direction to Helwan ");
            } else if (endIndex > shohadaaIndexAtLine1 && endIndex < sadatIndexAtLine1) {
                sadatLine.addAll(metroLine1.subList(endIndex, sadatIndexAtLine1 + 1));
                subLine.addAll(metroLine1.subList(shohadaaIndexAtLine1, endIndex + 1));
                Collections.reverse(subLine);
                shohadaaLine.addAll(subLine);
                subLine.clear();
                directionForFirstRoute.append("And Then Take Direction to Helwan ");
                directionForSecondRoute.append("And Then Take Direction to El-Marg ");
            } else {
                directionForFirstRoute.append("You are At sadat");
                directionForSecondRoute.append("You are At shohadaa");
                sadatLine.add("Sadat");
                shohadaaLine.add("Al-Shohadaa");
            }

            routes.add(sadatLine);
            routes.add(shohadaaLine);
        }
    }

    private static String getFirst(List<String> line) {
        return line.get(0);
    }

    private static String getLast(List<String> line) {
        return line.get(line.size() - 1);
    }


}




