package com.example.metroapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CairoLines {
    public static ArrayList<String> cairoLine1() {
        ArrayList<String> metroLine1 = new ArrayList<>();
        // Adding stations to Metro Line 1
        metroLine1.add("Helwan");
        metroLine1.add("Ain Helwan");
        metroLine1.add("Helwan University");
        metroLine1.add("Wadi Hof");
        metroLine1.add("Hadayek Helwan");
        metroLine1.add("El-Maasara");
        metroLine1.add("Tora El-Asmant");
        metroLine1.add("Kozzika");
        metroLine1.add("ToraEl-Balad");
        metroLine1.add("SakanatEl-Maadi");
        metroLine1.add("Maadi");
        metroLine1.add("HadayekEl-Maadi");
        metroLine1.add("DarEl-Salam");
        metroLine1.add("El-Zahraa");
        metroLine1.add("MarGirgis");
        metroLine1.add("El-MalekEl-Saleh");
        metroLine1.add("Al-SayedaZeinab");
        metroLine1.add("SaadZaghloul");
        metroLine1.add("Sadat");
        metroLine1.add("Nasser");
        metroLine1.add("Orabi");
        metroLine1.add("Al-Shohadaa");
        metroLine1.add("Ghamra");
        metroLine1.add("El-Demerdash");
        metroLine1.add("Manshiet El-Sadr");
        metroLine1.add("Kobri El-Qobba");
        metroLine1.add("Hammamat El-Qobba");
        metroLine1.add("SarayEl-Qobba");
        metroLine1.add("HadayeqEl-Zaitoun");
        metroLine1.add("HelmeyetEl-Zaitoun");
        metroLine1.add("El-Matareyya");
        metroLine1.add("AinShams");
        metroLine1.add("EzbetEl-Nakhl");
        metroLine1.add("El-Marg");
        metroLine1.add("NewEl-Marg");
        return metroLine1;
    }

    public static ArrayList<String> cairoLine2() {
        ArrayList<String> metroLine2 = new ArrayList<>();
        // Adding stations to Metro Line 2
        metroLine2.add("ShobraEl-Kheima");
        metroLine2.add("KolleyyetEl-Zeraa");
        metroLine2.add("Mezallat");
        metroLine2.add("Khalafawy");
        metroLine2.add("St.Teresa");
        metroLine2.add("RodEl-Farag");
        metroLine2.add("Massara");
        metroLine2.add("Al-Shohadaa");
        metroLine2.add("Attaba");
        metroLine2.add("MohamedNagiub");
        metroLine2.add("Sadat");
        metroLine2.add("Opera");
        metroLine2.add("Dokki");
        metroLine2.add("Bohooth");

        metroLine2.add("CairoUniversity");
        metroLine2.add("Faisal");
        metroLine2.add("Giza");
        metroLine2.add("OmmEl-Masryeen");
        metroLine2.add("SakiatMekki");
        metroLine2.add("El-Mounib");
        return metroLine2;
    }

    public static ArrayList<String> cairoLine3() {
        ArrayList<String> metroLine3 = new ArrayList<>();
        // Adding stations to Metro Line 3
        metroLine3.add("Road El FargCorr");//
        metroLine3.add("RingRoad");
        metroLine3.add("ElQumia");
        metroLine3.add("ElBohy");
        metroLine3.add("Imbaba");
        metroLine3.add("Sudan");
        metroLine3.add("KitKate");
        metroLine3.add("Safay Hegazy");
        metroLine3.add("Maspero");
        metroLine3.add("Nasser");
        metroLine3.add("Attaba");
        metroLine3.add("BabElShaaria");
        metroLine3.add("ElGeish");
        metroLine3.add("AbdouPasha");
        metroLine3.add("Abbasia");
        metroLine3.add("Cairo Fair");
        metroLine3.add("Stadium");
        metroLine3.add("KolleyetElBanat");
        metroLine3.add("Al Ahram");
        metroLine3.add("Haroun");
        metroLine3.add("Helioples");
        metroLine3.add("Alf Maskan");
        metroLine3.add("Nadi El Shams");
        metroLine3.add("Al Nozha");
        metroLine3.add("Hesham Barkat");
        metroLine3.add("Qubaa");
        metroLine3.add("OmarEbnElKhtabb");
        metroLine3.add("El Hayikstep");
        metroLine3.add("AdlyMansour");
        return metroLine3;
    }
   public static ArrayList<String> kitKatCairoUniversityLine = new ArrayList<>(
            Arrays.asList("KitKate", "Tawikfia", "WadiElNail", "GamatElDawel", "BolaqElDakror", "CairoUniversity")
    );
    public static Map<String, String> metroLine1Coordinates() {
        Map<String, String> metroLine1Coordinates = new LinkedHashMap<>();
        metroLine1Coordinates.put("Helwan", "29.84903736119006,31.33423752699516");
        metroLine1Coordinates.put("Ain Helwan", "29.86267458956478,31.325052634767143");
        metroLine1Coordinates.put("Helwan University", "29.869851393571782,31.319648635072106");
        metroLine1Coordinates.put("Wadi Hof", "29.879126538506355,31.313567241694653");
        metroLine1Coordinates.put("Hadayek Helwan", "29.897321402536715,31.304206737071468");
        metroLine1Coordinates.put("El-Maasara", "29.906392257121617,31.29954396827793");
        metroLine1Coordinates.put("Tora El-Balad", "29.946876646437012,31.27298910465672");
        metroLine1Coordinates.put("Kozzika", "29.936430585578005,31.281451661253538");
        metroLine1Coordinates.put("Tora El-Asmant", "29.925973501152576,31.28754625346665");
        metroLine1Coordinates.put("Sakanat El-Maadi", "29.953292118861153,31.26296199720143");
        metroLine1Coordinates.put("Maadi", "29.960342917786658,31.257637307857202");
        metroLine1Coordinates.put("Hadayek El-Maadi", "29.969992670385217,31.250811476627565");
        metroLine1Coordinates.put("Dar El-Salam", "29.982061686271564,31.24231317469022");
        metroLine1Coordinates.put("El-Zahraa", "29.995513640789056,31.231180465913802");
        metroLine1Coordinates.put("Mar Girgis", "30.00611532143133,31.229627777173285");
        metroLine1Coordinates.put("El-Malek El-Saleh", "30.017684059509136,31.231220248191676");
        metroLine1Coordinates.put("Sayeda Zeinab", "30.02929789246813,31.235438607042855");
        metroLine1Coordinates.put("Saad Zaghloul", "30.037029922797835,31.23833804620185");
        metroLine1Coordinates.put("Sadat", "30.044129052218047,31.234433850931318");
        metroLine1Coordinates.put("Nasser", "30.053497136289057,31.2387399486692");
        metroLine1Coordinates.put("Orabi", "30.05668037133046,31.2420699976144");
        metroLine1Coordinates.put("Shohada", "30.06106992046829,31.246057125172463");
        metroLine1Coordinates.put("Ghamra", "30.069054704852153,31.26463563213536");
        metroLine1Coordinates.put("El-Demerdash", "30.077316353366353,31.27781229124498");
        metroLine1Coordinates.put("Manshiet El-Sadr", "30.081997555984643,31.287537692810563");
        metroLine1Coordinates.put("Kobri El-Qobba", "30.08718414160959,31.29411744290364");
        metroLine1Coordinates.put("Hammamat El-Qobba", "30.091219081333755,31.2989307032774");
        metroLine1Coordinates.put("Saray El-Qobba", "30.097632695870367,31.304554147990988");
        metroLine1Coordinates.put("Hadayeq El-Zaitoun", "30.10585608498135,31.31048380409617");
        metroLine1Coordinates.put("Helmeyet El-Zaitoun", "30.113251031712153,31.31397014843506");
        metroLine1Coordinates.put("El-Matareyya", "30.12131861830261,31.313740489882196");
        metroLine1Coordinates.put("Ain Shams", "30.13104078468673,31.31909280990519");
        metroLine1Coordinates.put("Ezbet El-Nakhl", "30.13929806402633,31.32441992874097");
        metroLine1Coordinates.put("El-Marg", "30.152050394378318,31.335682068039823");
        metroLine1Coordinates.put("New El-Marg", "30.163618359624618,31.338345584019827");
        return metroLine1Coordinates;
    }

    public static Map<String, String> metroLine2Coordinates() {
        Map<String, String> metroLine2Coordinates = new LinkedHashMap<>();
        metroLine2Coordinates.put("Shobra El-Kheima", "30.12224225798257,31.24443716536811");
        metroLine2Coordinates.put("Kolleyyet El-Zeraa", "30.113714185018274,31.24868292989835");
        metroLine2Coordinates.put("Mezallat", "30.104182586810847,31.24562414920438");
        metroLine2Coordinates.put("Khalafawy", "30.09789607555925,31.24539415336153");
        metroLine2Coordinates.put("St. Teresa", "30.08795160733788,31.24548959931378");
        metroLine2Coordinates.put("Rod El-Farag", "30.080589343190677,31.245412872390176");
        metroLine2Coordinates.put("Massara", "30.070901990972583,31.245103301568424");
        metroLine2Coordinates.put("Al-Shohadaa", "30.06106992046829,31.246057125172463");
        metroLine2Coordinates.put("Attaba", "30.05233597308318,31.246795683320414");
        metroLine2Coordinates.put("Mohamed Naguib", "30.045325405514745,31.244159745399173");
        metroLine2Coordinates.put("Sadat", "30.044129052218047,31.234433850931318");
        metroLine2Coordinates.put("Opera", "30.041952643641874,31.22497455031182");
        metroLine2Coordinates.put("Dokki", "30.038432079810242,31.212233222386256");
        metroLine2Coordinates.put("Bohooth", "30.03580176297431,31.20016697820741");
        metroLine2Coordinates.put("Cairo University", "30.02600373947895,31.20116942825512");
        metroLine2Coordinates.put("Faisal", "30.017360945976815,31.203935185750414");
        metroLine2Coordinates.put("Giza", "30.01065584720426,31.207087935111314");
        metroLine2Coordinates.put("Omm El-Masryeen", "30.00564951534086,31.208124891006083");
        metroLine2Coordinates.put("Sakiat Mekki", "29.995491964138203,31.20864929094024");
        metroLine2Coordinates.put("El-Mounib", "29.98110225916312,31.212336612229258");
        return metroLine2Coordinates;
    }

    public static Map<String, String> metroLine3Coordinates() {
        Map<String, String> metroLine3Coordinates = new LinkedHashMap<>();
        metroLine3Coordinates.put("Road El Farg Corridor", "30.10190989339181,31.18443476887509");
        metroLine3Coordinates.put("Ring Road", "30.096436204240455,31.19957496154549");
        metroLine3Coordinates.put("El Qumia", "30.093243411138406,31.209015865290002");
        metroLine3Coordinates.put("El Bohy", "30.08212470598578,31.210551397385398");
        metroLine3Coordinates.put("Imbaba", "30.075849192590763,31.20745519619157");
        metroLine3Coordinates.put("Sudan", "30.070089263060304,31.204705593218478");
        metroLine3Coordinates.put("Kit Kat", "30.06655933608745,31.21301257656363");
        metroLine3Coordinates.put("Safay Hegazy", "30.062268041069913,31.223297859633735");
        metroLine3Coordinates.put("Maspero", "30.055701946915768,31.232115481245565");
        metroLine3Coordinates.put("Nasser", "30.053507874692166,31.238736914888786");
        metroLine3Coordinates.put("Attaba", "30.052359292738558,31.24678431031718");
        metroLine3Coordinates.put("Bab El Shaaria", "30.054132043308126,31.255909339812927");
        metroLine3Coordinates.put("El Geish", "30.06173781897152,31.266885049885264");
        metroLine3Coordinates.put("Abdou Pasha", "30.064796568469745,31.27470388360256");
        metroLine3Coordinates.put("Abbasia", "30.072005453041363,31.283362803691247");
        metroLine3Coordinates.put("Cairo Fair", "30.0733734683471,31.30098199011045");
        metroLine3Coordinates.put("Stadium", "30.072943184506745,31.317099714339914");
        metroLine3Coordinates.put("Kolleyet El Banat", "30.084063374530945,31.329004072861526");
        metroLine3Coordinates.put("Al Ahram", "30.091783963542465,31.3262925051496");
        metroLine3Coordinates.put("Haroun", "30.101537049663246,31.33300219159074");
        metroLine3Coordinates.put("Heliopolis", "30.108473252129592,31.338327106406293");
        metroLine3Coordinates.put("Alf Maskan", "30.119010620454404,31.340184365632673");
        metroLine3Coordinates.put("Nadi El Shams", "30.125513322722618,31.34891613259467");
        metroLine3Coordinates.put("Al Nozha", "30.127959919525487,31.360178589070863");
        metroLine3Coordinates.put("Hesham Barkat", "30.13085100901205,31.37289445119273");
        metroLine3Coordinates.put("Qubaa", "30.134836167768402,31.383742032938756");
        metroLine3Coordinates.put("Omar Ebn El Khtabb", "30.140362112263343,31.394360837128424");
        metroLine3Coordinates.put("El Hayikstep", "30.14387145849703,31.404690072211803");
        metroLine3Coordinates.put("Adly Mansour", "30.147068153833473,31.421197940368376");
        return metroLine3Coordinates;
    }




}
