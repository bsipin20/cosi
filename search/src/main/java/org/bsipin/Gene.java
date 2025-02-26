package org.bsipin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {

    public enum Nucleotide { A, C, G, T
    }


    public static class Codon implements Comparable<Codon> {
        public final Nucleotide first, second, third;
        private final Comparator<Codon> comparator =
                Comparator.comparing((Codon c) -> c.first).thenComparing((Codon c) -> c.second).thenComparing((Codon c) -> c.third);


        public Codon(String codonStr) {
            first = Nucleotide.valueOf(codonStr.substring(0, 1));
            second = Nucleotide.valueOf(codonStr.substring(1, 2));
            third = Nucleotide.valueOf(codonStr.substring(2, 3));
        }

        @Override
        public int compareTo(Codon other) {
            return comparator.compare(this, other);
        }

    }


    private ArrayList<Codon> codons = new ArrayList<>();
    public Gene(String genStr) {
        for (int i = 0; i < genStr.length(); i++) {
            codons.add(new Codon(genStr.substring(i, i + 1)));
        }
    }

    public boolean linearContains(Codon key) {
        for (Codon codon : codons) {
            if (codon.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean binaryContains(Codon key) {
        ArrayList<Codon> sortedCodons = new ArrayList<>();
        Collections.sort(sortedCodons);
        int low = 0;
        int high = sortedCodons.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int comparison = sortedCodons.get(middle).compareTo(key);
            if (comparison == 0) {
                low = middle + 1;
            } else if (comparison < 0) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

