package co.edu.udea.discretes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private int numVertex, type;
    private String representation;
    private List<List> mAdjacency;
    private List<List> mIncidence;

    public Graph(int numVertex, String representation, Integer type) {
        this.numVertex = numVertex;
        this.representation = representation;
        this.type = type;
        calculateAdjacency();
        calculateIncidence();
    }

    public int getNumVertex() {
        return numVertex;
    }

    public void setNumVertex(int numVertex) {
        this.numVertex = numVertex;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public void calculateAdjacency() {
        //Asigna la matriz de adyacencia, los indices cero se omiten aunque aparezcan por prácticidad.
        String[] characters = representation.split("");
        this.mAdjacency = new ArrayList<>(this.numVertex);//Rows
        for (int i = 0; i <= numVertex; i++) {
            mAdjacency.add(new ArrayList<Integer>(Collections.nCopies(numVertex + 1, 0)));     //columns

        }

        for (int k = 0; k < representation.length(); k += 2) {
            int h = k + 1;
            mAdjacency.get(Integer.parseInt(characters[k])).remove(Integer.parseInt(characters[h]));
            mAdjacency.get(Integer.parseInt(characters[k])).add(Integer.parseInt(characters[h]), 1);

        }

    }

    public void calculateIncidence() {
        String[] characters = representation.split("");
        int j;//aux. var.
        boolean bucle;

        this.mIncidence = new ArrayList<>(this.representation.length() / 2 + 1);//Rows
        for (int i = 0; i <= this.representation.length() / 2; i++) {
            mIncidence.add(new ArrayList<Integer>(Collections.nCopies(numVertex + 1, 0)));     //columns

        }

        for (int h = 1; h < mIncidence.size(); h++) {
            j = 0;
            for (int k = 2 * (h - 1); j < 2; k++) {
                j++;
                if ((k + 1) % 2 == 0) {
                    bucle = Integer.parseInt(characters[k]) == Integer.parseInt(characters[k - 1]);
                    mIncidence.get(h).remove(Integer.parseInt(characters[k]));
                    mIncidence.get(h).add(Integer.parseInt(characters[k]), bucle ? 2 : -1);

                } else {
                    mIncidence.get(h).remove(Integer.parseInt(characters[k]));
                    mIncidence.get(h).add(Integer.parseInt(characters[k]), 1);
                }
            }
        }
    }

    public Boolean isComplete() {
        return true;
    }

    public Boolean isRegular() {
        return true;
    }

    public Boolean isEulerian() {
        return true;
    }

}
