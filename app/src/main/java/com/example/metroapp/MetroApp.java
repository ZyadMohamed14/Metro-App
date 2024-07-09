package com.example.metroapp;

import android.util.Log;

import java.util.*;

public class MetroApp {
    static ArrayList<String> metroLine1 = CairoLines.cairoLine1();
    static ArrayList<String> metroLine2 = CairoLines.cairoLine2();
    static ArrayList<String> metroLine3 = CairoLines.cairoLine3();
    //  static ArrayList<String> kitKatCairoUniversityLine = new ArrayList<>("CairoUniversity","Tawikfia", "WadiElNail", "GamatElDawel", "BolaqElDakror","KitKate")
    static ArrayList<String> kitKatCairoUniversityLine = new ArrayList<>(
            Arrays.asList("CairoUniversity", "Tawikfia", "WadiElNail", "GamatElDawel", "BolaqElDakror", "KitKate")
    );
    static ArrayList<String> kitKatCairoUniversityLine2 = new ArrayList<>(
            Arrays.asList("KitKate", "Tawikfia", "WadiElNail", "GamatElDawel", "BolaqElDakror", "CairoUniversity")
    );

    static List<ArrayList<String>> routs = new ArrayList<>();
    static boolean isRouthInTheFirstLine = false;
    static boolean isRouthInTheSecondLine = false;
    static boolean isRouthInTheThirdLine = false;
    static boolean isRouthInTheSameLine = false;
    static boolean isRouthInTheMultiLine = false;
    static boolean isRouthInTheFourthLine = false;

    static String transfersStation;

    static StringBuilder transBuilder = new StringBuilder();

    static String startDirection;
    static String endDirection;
    static String direction;


    static boolean isVaildData(String start, String end) {
        if (start.equals("") || end.equals("")) return false;
        if (start.equals(end)) return false;
        return isVaildStation(start) && isVaildStation(end);


    }

    static boolean isVaildStation(String station) {
        return metroLine1.contains(station) || metroLine2.contains(station) || metroLine3.contains(station)
                || kitKatCairoUniversityLine.contains(station);
    }

    public static List<String> searchStations(String start, String end) {
        if (isStationsInTheSameLine(start, end)) {
            if (isRouthInTheFirstLine) {
                isRouthInTheSameLine = true;
                return printRouteDetails(metroLine1, start, end);
            } else if (isRouthInTheSecondLine) {
                isRouthInTheSameLine = true;
                return printRouteDetails(metroLine2, start, end);
            } else if (isRouthInTheThirdLine) {
                isRouthInTheSameLine = true;
                return printRouteDetails(metroLine3, start, end);
            } else {
                isRouthInTheSameLine = true;
                return printRouteDetails(kitKatCairoUniversityLine, start, end);
            }
        } else {
            isRouthInTheMultiLine = true;
            return searchInManyLines(start, end);
        }


    }

    public static boolean isStationsInTheSameLine(String start, String end) {
        if (metroLine1.contains(start) && metroLine1.contains(end)) {
            isRouthInTheFirstLine = true;
            return true;
        } else if (metroLine2.contains(start) && metroLine2.contains(end)) {
            isRouthInTheSecondLine = true;
            return true;
        } else if (metroLine3.contains(start) && metroLine3.contains(end)) {
            isRouthInTheThirdLine = true;
            return true;
        } else if (kitKatCairoUniversityLine.contains(start) && kitKatCairoUniversityLine.contains(end)) {
            isRouthInTheFourthLine = true;
            return true;
        } else return false;

    }

    public static List<String> searchInManyLines(String start, String end) {
        List<String> line = new ArrayList<>();
        List<String> subline = new ArrayList<>();
        if (metroLine1.contains(start) && metroLine2.contains(end)) {

            int startIndex = metroLine1.indexOf(start);
            int transferIndex1 = metroLine1.indexOf("Sadat");
            if (startIndex >= transferIndex1) {
                startDirection = getFirst(metroLine1);
                transfersStation = "Al-Shohadaa";
            } else {
                startDirection = getLast(metroLine1);
                transfersStation = "Sadat";
            }

            int transIndexInLine2 = metroLine2.indexOf(transfersStation);
            int lasIndex = metroLine2.indexOf(end);
            if (transIndexInLine2 > lasIndex) endDirection = getFirst(metroLine2);
            else endDirection = getLast(metroLine2);

            //   printRoutDetails(line);
            line = getPath(metroLine1, metroLine2, start, end, transfersStation);

            return line;


        }
        else if (metroLine2.contains(start) && metroLine1.contains(end)) {
            transfersStation = "";
            int startIndex = metroLine2.indexOf(start);
            int lasIndex = metroLine1.indexOf(end);
            int sadatIndex = metroLine2.indexOf("Sadat");

            if (startIndex > sadatIndex) {
                transfersStation = "Sadat";
                startDirection = getFirst(metroLine2);
            } else if (startIndex == sadatIndex) {
                transfersStation = "Sadat";
                startDirection = "You are At Al Sadat Station";
            } else {
                transfersStation = "Al-Shohadaa";
                startDirection = getLast(metroLine2);
            }
            int transIndexInLine2 = metroLine1.indexOf(transfersStation);
            if (transIndexInLine2 < lasIndex) endDirection = getLast(metroLine1);
            else endDirection = getFirst(metroLine1);


            line = getPath(metroLine1, metroLine2, end, start, transfersStation);
            // line = getPath(metroLine2, metroLine1, start, end, transfersStation);

            Collections.reverse(line);
            //  printRoutDetails(line);
            return line;

        }
        else if (metroLine3.contains(start) && metroLine2.contains(end)) {
            transfersStation = "";
            int startIndex = metroLine3.indexOf(start);
            int attabaIndex = metroLine3.indexOf("Attaba");
            int endIndex = metroLine2.indexOf(end);
            if (startIndex >= attabaIndex) {
                if (startIndex == attabaIndex) startDirection = "You Are In Attaba";
                else transfersStation = "Attaba";
                startDirection = getFirst(metroLine3);
                int transIndex = metroLine2.indexOf(transfersStation);
                if (endIndex > transIndex) endDirection = getLast(metroLine2);
                else endDirection = getFirst(metroLine2);
                //   printRoutDetails(line);
                return getPath(metroLine3, metroLine2, start, end, transfersStation);
            } else {
                //TODO line3

                int kitkateIndex = metroLine3.indexOf("KitKate");
                if (startIndex >= kitkateIndex) {
                    if (startIndex == kitkateIndex) {
                        startDirection = "You Are Now in KitKate";
                        transBuilder.append(startDirection);
                    } else {
                        startDirection = getFirst(metroLine3);
                        transBuilder.append("Take direction to " + startDirection + " and then change direction at KitKate");
                    }
                    subline.addAll(metroLine3.subList(kitkateIndex + 1, startIndex + 1));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                } else {
                    startDirection = getLast(metroLine3);
                    transBuilder.append("Take direction to " + startDirection + " and then change direction at KitKate");
                    subline.addAll(metroLine3.subList(startIndex, kitkateIndex));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                }

                //TODO cairokitkate branch
                subline.addAll(kitKatCairoUniversityLine);
                Collections.reverse(subline);
                line.addAll(subline);
                subline.clear();
                transBuilder.append(" Then Change Direction At Cairo University");
                //TODO line2
                int cairoIndex = metroLine2.indexOf("CairoUniversity");
                if (endIndex > cairoIndex) {
                    endDirection = getLast(metroLine2);
                    line.addAll(metroLine2.subList(cairoIndex + 1, endIndex + 1));

                } else if (endIndex == cairoIndex) {
                    endDirection = "You Are At Cairo University";
                    line.add("CairoUniversity");
                } else {
                    endDirection = getFirst(metroLine2);
                    subline.addAll(metroLine2.subList(endIndex, cairoIndex));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                }
                return line;
            }


        }
        else if (metroLine2.contains(start) && metroLine3.contains(end)) {
            transfersStation = "";
            int startIndex = metroLine2.indexOf(start);
            int endIndex = metroLine3.indexOf(end);
            int attabaIndex = metroLine2.indexOf("Attaba");
            int cairoIndex = metroLine2.indexOf("CairoUniversity");

            if (startIndex <= attabaIndex) {
                startDirection = getLast(metroLine2);
                if (startIndex == attabaIndex) {
                    transBuilder.append("You are now at Attaba!");
                } else {
                    transBuilder.append("Take direction to " + startDirection + " and then change direction at Attaba");
                }
                transfersStation = "Attaba";
                int attabaIndexAtLine3 = metroLine3.indexOf("Attaba");

                subline.addAll(metroLine2.subList(startIndex, attabaIndex));
                line.addAll(subline);
                subline.clear();

                if (attabaIndexAtLine3 >= endIndex) {
                    // direction to Road El FargCorr
                    endDirection = getFirst(metroLine3);
                    subline.addAll(metroLine3.subList(endIndex, attabaIndexAtLine3 + 1));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                } else {
                    endDirection = getLast(metroLine3);
                    subline.addAll(metroLine3.subList(attabaIndexAtLine3, endIndex + 1));
                    line.addAll(subline);
                    subline.clear();
                }
            } else {


                transfersStation = "CairoUniversity";
                if (cairoIndex >= endIndex) {
                    startDirection = getLast(metroLine2);
                    subline.addAll(metroLine2.subList(startIndex, cairoIndex));
                    line.addAll(subline);
                    subline.clear();

                } else {
                    startDirection = getFirst(metroLine2);

                    subline.addAll(metroLine2.subList(cairoIndex + 1, startIndex + 1));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                }
                transBuilder.append("Take direction to " + startDirection + " and then change direction at Cairo University");
                line.addAll(kitKatCairoUniversityLine);
                int kitkatIndex = metroLine3.indexOf("KitKate");

                if (endIndex >= kitkatIndex) {
                    if (endIndex == kitkatIndex) endDirection = "KitKate";
                    else endDirection = getLast(metroLine3);

                    subline.addAll(metroLine3.subList(kitkatIndex + 1, endIndex + 1));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                } else {
                    endDirection = getFirst(metroLine3);
                    subline.addAll(metroLine3.subList(endIndex, kitkatIndex));
                    Collections.reverse(subline);
                    line.addAll(subline);
                    subline.clear();
                }
            }

            return line;
        }
        else if (metroLine1.contains(start) && metroLine3.contains(end)) {
            Log.d("benz", "metroLine1 metroLine3 : ");
            transfersStation = "Nasser";
            int transIndex = metroLine1.indexOf(transfersStation);
            int transIndex2 = metroLine3.indexOf(transfersStation);
            int startIndex = metroLine1.indexOf(start);
            int endIndex = metroLine3.indexOf(end);
            //  if (!start.equals(startDirection)) startDirection = getLast(metroLine1);
            if (transIndex > startIndex) startDirection = getLast(metroLine1);
            else startDirection = getFirst(metroLine1);
            if (transIndex2 < endIndex) endDirection = getLast(metroLine3);
            else endDirection = getFirst(metroLine3);


            return getPath(metroLine1, metroLine3, start, end, transfersStation);

        }
        else if (metroLine3.contains(start) && metroLine1.contains(end)) {
            transfersStation = "Nasser";
            int transIndex = metroLine1.indexOf(transfersStation);
            int transIndex2 = metroLine3.indexOf(transfersStation);
            int startIndex = metroLine3.indexOf(start);
            int endIndex = metroLine1.indexOf(end);
            //  if (!start.equals(startDirection)) startDirection = getLast(metroLine1);
            if (transIndex > startIndex) startDirection = getLast(metroLine3);
            else startDirection = getFirst(metroLine3);
            if (transIndex2 < endIndex) endDirection = getLast(metroLine1);
            else endDirection = getFirst(metroLine1);
            line = getPath(metroLine3, metroLine1, start, end, transfersStation);

            ArrayList<String> copy = (ArrayList<String>) line;
            routs.add(copy);

            return line;

        }
        else if (metroLine1.contains(start) && kitKatCairoUniversityLine.contains(end)) {
            Log.d("benz", "===========");
            transfersStation = "";
            int sadatIndex = metroLine1.indexOf("Sadat");
            int nasserIndex = metroLine1.indexOf("Nasser");
            int startIndex = metroLine1.indexOf(start);
            int endIndex = kitKatCairoUniversityLine.indexOf(end);
            if (startIndex <= sadatIndex) {
                startDirection = getLast(metroLine1);
                if (startIndex == sadatIndex)
                    transBuilder.append("You Now At Sadat Take Direction to " + getLast(metroLine2));
                else
                    transBuilder.append("Your Direction is to " + startDirection + "And Change Your Direction At Sadat " + "Take the Direction to " + getLast(metroLine2));

                line.addAll(metroLine1.subList(startIndex, sadatIndex + 1));
                transBuilder.append(" And Then Change Direction At Cairo University ");
                int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
                int cairoUniversityIndex = metroLine2.indexOf("CairoUniversity");
                line.addAll(metroLine2.subList(sadatIndexAtLine2, cairoUniversityIndex));
                line.addAll(kitKatCairoUniversityLine.subList(0, endIndex + 1));
                endDirection = getLast(kitKatCairoUniversityLine);
            } else {
                startDirection = getFirst(metroLine1);
                if (startIndex == nasserIndex)
                    transBuilder.append("You Now At Nasser ! Take Direction to " + getFirst(metroLine3));
                else
                    transBuilder.append("Your Direction is to " + startDirection + " and Change Your Direction At Nasser " + "Take the Direction to " + getFirst(metroLine3));

                line.addAll(metroLine1.subList(nasserIndex, startIndex + 1));
                Collections.reverse(line);
                int nasserIndexAtLine3 = metroLine3.indexOf("Nasser");
                int kitkatIndex = metroLine3.indexOf("KitKate");
                int kitkatIndexAtCairoBranch = metroLine3.indexOf("KitKate");
                subline = metroLine3.subList(kitkatIndex + 1, nasserIndexAtLine3);
                Collections.reverse(subline);
                line.addAll(subline);
                transBuilder.append(" Then Change Direction At KitKate ");
                List<String> subline2 = kitKatCairoUniversityLine.subList(endIndex, kitkatIndexAtCairoBranch);
                Collections.reverse(subline2);
                line.addAll(subline2);
                endDirection = getFirst(kitKatCairoUniversityLine);
            }

            return line;
        }
        else if (metroLine1.contains(end) && kitKatCairoUniversityLine.contains(start)) {
            transfersStation = "";
            int startIndex = kitKatCairoUniversityLine.indexOf(start);
            subline = new ArrayList<>(kitKatCairoUniversityLine.subList(1, startIndex + 1));
            Collections.reverse(subline);
            line.addAll(subline);
            subline.clear(); // a void meaking sevreal sub lines just create one and add , clear from it
            startDirection = "Cairo UniverSity";
            transBuilder.append("Take Direction to " + startDirection);
            int sadatIndexAtLine2 = metroLine2.indexOf("Sadat");
            int cairoIndexAtLine2 = metroLine2.indexOf("CairoUniversity");
            transBuilder.append(" and then take Direction to " + getFirst(metroLine2) + "and Change Direction At Sadat");
            subline.addAll(metroLine2.subList(sadatIndexAtLine2, cairoIndexAtLine2 + 1));
            Collections.reverse(subline);
            line.addAll(subline);
            subline.clear();
            int endIndex = metroLine1.indexOf(end);
            int sadatIndexAtLine1 = metroLine1.indexOf("Sadat");
            if (endIndex <= sadatIndexAtLine1) {
                if (endIndex == sadatIndexAtLine1) endDirection = "Sadat";
                else endDirection = getFirst(metroLine1);
                subline.addAll(metroLine1.subList(endIndex, sadatIndexAtLine1));
                Collections.reverse(subline);
                line.addAll(subline);
                subline.clear();

            } else {
                endDirection = getLast(metroLine1);
                subline.addAll(metroLine1.subList(sadatIndexAtLine1, endIndex));
                line.addAll(subline);
                subline.clear();
            }

            return line;
        }
        else if (metroLine2.contains(start) && kitKatCairoUniversityLine.contains(end)) {
            transfersStation = "";
            int startIndex = metroLine2.indexOf(start);
            int cairoIndex = metroLine2.indexOf("CairoUniversity");
            int endIndex = kitKatCairoUniversityLine.indexOf(end);
            if (startIndex <= cairoIndex) {
                startDirection = getLast(metroLine2);
                if (startIndex == cairoIndex) transBuilder.append("You Now At Cairo University !");
                else
                    transBuilder.append("Take Direction to " + startDirection + " and then change direction at  Cairo University");
                subline.addAll(metroLine2.subList(startIndex, cairoIndex + 1));
                line.addAll(subline);
                subline.clear();
            } else {
                startDirection = getFirst(metroLine2);
                transBuilder.append("Take Direction to " + startDirection + " and then change direction at  Cairo University");
                subline.addAll(metroLine2.subList(cairoIndex, startIndex + 1));
                Collections.reverse(subline);
                line.addAll(subline);
                subline.clear();
            }
            line.addAll(kitKatCairoUniversityLine.subList(1, endIndex + 1));
            endDirection = getLast(kitKatCairoUniversityLine);

            return line;
        }
        else if (metroLine2.contains(end) && kitKatCairoUniversityLine.contains(start)) {
            transfersStation = "";
            int startIndex = kitKatCairoUniversityLine.indexOf(start);
            int endIndex = metroLine2.indexOf(end);
            subline.addAll(kitKatCairoUniversityLine.subList(1, startIndex));
            Collections.reverse(subline);
            line.addAll(subline);
            subline.clear();
            startDirection = getFirst(kitKatCairoUniversityLine);
            int cairoIndex = metroLine2.indexOf("CairoUniversity");
            transBuilder.append("take The Direction to " + startDirection);
            if (cairoIndex >= endIndex) {
                endDirection = getFirst(metroLine2);
                if (cairoIndex == endIndex) transBuilder.append("You Now At Cairo University !");
                else transBuilder.append("then Take Direction to " + endDirection + " ");
                subline.addAll(metroLine2.subList(endIndex, cairoIndex + 1));
                Collections.reverse(subline);
                line.addAll(subline);
                subline.clear();
            } else {
                endDirection = getLast(metroLine2);
                subline.addAll(metroLine2.subList(cairoIndex, endIndex + 1));
                line.addAll(subline);
                subline.clear();
            }
            return line;
        }
        //ToDO ,line 3 and Ciro branch
        else if (metroLine3.contains(start) && kitKatCairoUniversityLine.contains(end)) {
            int kitKateIndexAtLine3 = metroLine3.indexOf("KitKate");
            int startIndex = metroLine3.indexOf(start);
            int endIndex = kitKatCairoUniversityLine2.indexOf(end);
            if (startIndex >= kitKateIndexAtLine3) {
                if (startIndex == kitKateIndexAtLine3) transBuilder.append("You Are At KitKate !");
                else
                    transBuilder.append("Take Direction to " + getFirst(metroLine3) + " and then change direction at KitKate");
                subline.addAll(metroLine3.subList(kitKateIndexAtLine3+1, startIndex));
                Collections.reverse(subline);
                line.addAll(subline);
                subline.clear();
            } else {
                transBuilder.append("Take Direction to " + getLast(metroLine3) + " and then change direction at KitKate");
                line.addAll(metroLine3.subList(startIndex, kitKateIndexAtLine3));
            }
           int kitkateIndexAtCairoBrench =kitKatCairoUniversityLine2.indexOf("KitKate");
            subline.addAll(kitKatCairoUniversityLine2.subList(kitkateIndexAtCairoBrench,endIndex+1));
            line.addAll(subline);
            subline.clear();
            endDirection = getLast(kitKatCairoUniversityLine2);
             return line;
        }


        return new ArrayList<>();
    }

    public static String getFirst(List<String> line) {
        return line.get(0);
    }

    public static String getLast(List<String> line) {
        return line.get(line.size() - 1);
    }

    public static List<String> getPath(List<String> line1, List<String> line2, String start, String end, String transferStation) {
        ArrayList<String> line = new ArrayList<>();
        int startIndex = line1.indexOf(start);
        int endIndex = line2.indexOf(end);
        List<String> path1;
        List<String> path2;

        int transferStationIndex = line1.indexOf(transferStation);

        int transferStationAtLine2 = line2.indexOf(transferStation);
        if (startIndex > transferStationIndex) {
            path1 = line1.subList(transferStationIndex + 1, startIndex + 1);
            Collections.reverse(path1);

        } else {
            transferStationIndex = line1.indexOf(transferStation);
            path1 = line1.subList(startIndex, transferStationIndex);
            if (transferStation.equals("KitKate")) {
                transfersStation = transferStation + " Then change Direction at CairoUniversity ";
                path1.addAll(kitKatCairoUniversityLine);
            } else if (transferStation.equals("CairoUniversity")) {
                //
                transfersStation = transferStation + " Then change Direction at KitKate ";
                path1.addAll(kitKatCairoUniversityLine);
            }

        }

        if (endIndex > transferStationAtLine2) {
            path2 = line2.subList(transferStationAtLine2, endIndex + 1);

        } else {
            path2 = line2.subList(endIndex, transferStationAtLine2 + 1);
            Collections.reverse(path2);


        }

        line.addAll(path1);
        line.addAll(path2);
        return line;
    }

    public static List<String> printRouteDetails(ArrayList<String> line, String startPoint, String endPoint) {
        direction = "";
        Log.d("zyad", "line " + line);
        int startIndex = line.indexOf(startPoint);
        int endIndex = line.indexOf(endPoint);

        if (startIndex == -1 || endIndex == -1) {

            return Collections.emptyList();
        }

        if (startIndex < endIndex) {
            direction = getLast(line);
        } else {
            direction = getFirst(line);
        }
        Log.d("zyad", direction);


        List<String> traveledStations;
        if (startIndex < endIndex) {
            return line.subList(startIndex, endIndex + 1);
        } else {
            ArrayList<String> reversedLine = new ArrayList<>(line);
            Collections.reverse(reversedLine);
            startIndex = reversedLine.indexOf(startPoint);
            endIndex = reversedLine.indexOf(endPoint);
            return reversedLine.subList(startIndex, endIndex + 1);
        }

        //  System.out.println("Stations traveled: " + traveledStations);
    }


}




