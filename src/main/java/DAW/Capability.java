package DAW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pascal
 */
class Capability {

    public static int U = 991205981; //39916801;
    public static int seedA = 12345678;
    public static int seedB = 98765432;

    // maxn = 10 -> n = 10% of total size
    public static int maxn = 100;

    // idset for building a mip vector
    private ArrayList<Integer> idSet = new ArrayList<Integer>(10000000);
    private HashSet<Integer> subSet = new HashSet<Integer>(10000000);
    private HashSet<Integer> objSet = new HashSet<Integer>(10000000);
    private ArrayList<Long> mip = new ArrayList<Long>();
    private String property;

    Capability(String property) {
        this.property = property;
    }

    String getProperty() {
        return this.property;
    }

    void addId(int e) {
        idSet.add(new Integer(e));
    }

    public void buildMipVector() {

        int total = idSet.size();
        int n = total > maxn ? (int) (((double) total) / maxn) : total;

        System.err.println("Building mip for:" + property + "nb Id:" + idSet.size() + "nb perm:" + n);

        Random ra = new Random(seedA);
        Random rb = new Random(seedB);
        for (int i = 0; i < n; i++) {
            if (i % 100 == 0) {
                System.err.println("processing perm:" + i + " on " + n);
            }
            int ai = ra.nextInt();
            int bi = rb.nextInt();
            long mini = Long.MAX_VALUE;

            for (int x = 0; x < idSet.size(); x++) {
                long hi = (ai * idSet.get(x) + bi) % U;
                if (hi < mini) {
                    mini = hi;
                }
            }
            mip.add(mini);
        }
    }

    public int getTotal() {
        return idSet.size();
    }

    public String getMipsAsString() {
        if (mip.size() == 0) {
            buildMipVector();
        }
        return mip.toString();
    }

    void addObj(int integer) {
        objSet.add(new Integer(integer));
    }

    void addSubj(int integer) {
        subSet.add(new Integer(integer));
    }

    public double getSubjectNb() {
        return subSet.size();
    }

    public double getObjectNb() {
        return objSet.size();
    }

}
