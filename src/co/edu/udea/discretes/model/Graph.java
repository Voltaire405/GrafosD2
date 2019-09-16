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

    public boolean isComplete() {
    	boolean completo=false;
    	int aristas=0;
    	
    	for(int i=1;i<mIncidence.size();i++) {
    		for(int j=1; j<mIncidence.get(i).size(); j++) {
    			if(mIncidence.get(i).get(j)!=(Object)0) {
    				aristas++;
    			}
    		}
    	}
    	aristas=aristas/2;
    	
    	if(type==1) {//dirigido
    		if(aristas==(numVertex*(numVertex-1)))completo=true;
    	}
    	else {//no dirigido
    		if(aristas==(numVertex*(numVertex-1)/2))completo=true;
    	}
        return completo;
    }

    public Boolean isRegular() {
    	int cont1=0, cont2=0; //contadores
    	boolean regular=true;
    	
    	for(int i=1; i<mIncidence.get(1).size(); i++) {//indicador para avanzar por las columnas
    		for(int j=1; j<mIncidence.size(); j++) {//filas
    			if(mIncidence.get(j).get(i)!=(Object)0) {
    				if(i==1) {
    					cont1++;
    				}
    				else {
    					cont2++;
    				}
    			}
    		}
    		if(i>1) {
    			if(cont1!=cont2) {
    				regular=false;
    				break;
    			}
    		}
    		cont2=0;
    	}
        return regular;
    }

    public Boolean isEulerian() {
    	boolean euleriano=true;
    	int cont=0;//contador
    	for(int i=1; i<mIncidence.get(1).size(); i++) {//indicador para avanzar por las columnas
    		for(int j=1; j<mIncidence.size(); j++) {//filas
    			if(mIncidence.get(j).get(i)!=(Object)0) {
    				cont++;
    			}
    		}
    		if(cont%2!=0) {
    			euleriano=false;
    			break;
    		}
    	}
    	return euleriano;
    }
    
    public void escribe() {
    	for(int i=0;i<mIncidence.size();i++) {
    		System.out.println(mIncidence.get(i));
    	}
    }

}