package co.edu.udea.discrete.main;

import co.edu.udea.discretes.model.Graph;
import jdk.nashorn.internal.runtime.ParserException;

import javax.swing.*;
import java.text.ParseException;

public class Program {
    public static void main(String[] args) {
        Graph graph;
        String userEntry;
        int entryVertexConnections, opt = 0;
        int vertexNum = 0;
        int sides;

        try {
            opt = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el tipo de grafo: \n1. Dirigido.\n2.No dirigido.\n0.Salir", "0"));
            if (opt != 1 || opt != 2) {
                System.exit(0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Entrada no válida.");
        }


        userEntry = JOptionPane.showInputDialog(null, "Ingrese por favor las aristas de su Grafo separado por " +
                "','. Ej. 14,25,13,32,... empleando números enteros para identificar los vertices y sin dejar espacios.");
        userEntry = userEntry.replace(",", "");

        //User Validations.
        try {
            int integer = 0;
            for (char c : userEntry.toCharArray()) {
                integer = Integer.parseInt(c + "");
                if (integer < 1) {
                    throw new Exception("Identificador de Vertices no puede, ser menor a 1");
                }
                if (integer > vertexNum) {
                    vertexNum = integer;
                }
                System.out.println(integer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        entryVertexConnections = userEntry.length();
        if (entryVertexConnections % 2 != 0) {
            JOptionPane.showMessageDialog(null, "Formato de ingreso del grafo no válido");
        } else {
            //JOptionPane.showMessageDialog(null,"Formato de ingreso del grafo válido");
        }

        //instantiate Graph class.
        graph = new Graph(vertexNum, userEntry, opt);

    }
}
