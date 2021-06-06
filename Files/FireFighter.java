import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class FireFighter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int n = scanner.nextInt(), z = 1;
        while (n != 0) {
            Map<Integer, ArrayList<Integer>> roads = new HashMap<Integer, ArrayList<Integer>>();
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            while (m1 >= 21 || m2 >= 21) {
                System.out.println("m1 and m2 should be less than 21");
                m1 = scanner.nextInt();
                m2 = scanner.nextInt();
            }
            while (m1 != 0 && m2 != 0) {
                if (roads.containsKey(m1)) {
                    roads.get(m1).add(m2);
                } else {
                    roads.put(m1, new ArrayList<Integer>());
                    roads.get(m1).add(m2);
                }
                if (roads.containsKey(m2)) {
                    roads.get(m2).add(m1);
                } else {
                    roads.put(m2, new ArrayList<Integer>());
                    roads.get(m2).add(m1);
                }
                m1 = scanner.nextInt();
                m2 = scanner.nextInt();
                while (m1 >= 21 || m2 >= 21) {
                    System.out.println("m1 and m2 should be less than 21");
                    m1 = scanner.nextInt();
                    m2 = scanner.nextInt();
                }
            }
            boolean[] visited = new boolean[20];
            ArrayList<Integer> roadsFor1 = roads.get(1);
            visited[0] = true;
            int roadsSize = 0;
            String road = "1 ";
            System.out.println("case#" + z);
            for (int i = 0; i < roadsFor1.size(); i++) {
                String x = road.concat(roadsFor1.get(i) + " ");
                visited[roadsFor1.get(i) - 1] = true;
                roadsSize = find(visited, roads, n, roadsSize, roadsFor1.get(i), x);
                visited[roadsFor1.get(i) - 1] = false;
            }
            visited[0] = false;
            System.out.println("There are " + roadsSize + " routes from the firestation to streetcorner " + n);
            long TC = 0;
            for (int i = 0; i < 15; i++) {
                int x = rand.nextInt(roadsFor1.size());
                visited[roadsFor1.get(x) - 1] = true;
                TC = TC + roadsFor1.size() + mountCarlo(visited, roads, n, roadsFor1.get(x), roadsFor1.size(), rand) + 1;
                visited[roadsFor1.get(x) - 1] = false;
            }
            System.out.println("Time Complexity : " + (TC / 15));
            System.out.println("Enter a a new Location");
            n = scanner.nextInt();
        }
    }

    static public int find(boolean[] visited, Map<Integer, ArrayList<Integer>> roads, int n, int roadsSize, int location, String road) {
        if (location == n) {
            System.out.print("road #" + (roadsSize + 1) + " : ");
            System.out.println(road);
            return roadsSize + 1;
        }
        ArrayList<Integer> roadsForx = roads.get(location);
        for (int i = 0; i < roadsForx.size(); i++) {
            if (!visited[roadsForx.get(i) - 1]) {
                String x = road.concat(roadsForx.get(i) + " ");
                visited[roadsForx.get(i) - 1] = true;
                roadsSize = find(visited, roads, n, roadsSize, roadsForx.get(i), x);
                visited[roadsForx.get(i) - 1] = false;
            }
        }
        return roadsSize;
    }

    static public long mountCarlo(boolean[] visited, Map<Integer, ArrayList<Integer>> roads, int n, int location, long m, Random rand) {
        if (location == n) {
            return 0;
        }
        ArrayList<Integer> roadsForx = roads.get(location);
        int x = rand.nextInt(roadsForx.size());
        int q = 0;
        while (visited[roadsForx.get(x) - 1]) {
            x = rand.nextInt(roadsForx.size());
            q++;
            if (q == roadsForx.size())
                return 0;
        }
        int z = 0;
        for (int i = 0; i < roadsForx.size(); i++) {
            if (!visited[roadsForx.get(i) - 1])
                z++;
        }
        visited[roadsForx.get(x) - 1] = true;
        long TC = (m * z) + mountCarlo(visited, roads, n, roadsForx.get(x), z * m, rand);
        visited[roadsForx.get(x) - 1] = false;
        return TC;
    }
}