import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.io.*;

import java.util.ArrayList;

public class FireFighter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean[] valid = new boolean[20];
        int n = scanner.nextInt();
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
            System.out.println("Enter a a new Location");
            n = scanner.nextInt();
        }
    }
}