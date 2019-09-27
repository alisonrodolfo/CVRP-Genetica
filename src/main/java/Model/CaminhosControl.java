/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Alison Rodolfo
 */
public class CaminhosControl {
    
        // Detém nossas cidades
    private static ArrayList destinationCities = new ArrayList<Vertice>();

    public static void addCliente(Vertice cliente) {
        destinationCities.add(cliente);
    }
    
    public static void clear() {
        destinationCities.clear();
    }
    
    public static Vertice getCidade(int index){
        return (Vertice)destinationCities.get(index);
    }
    
    public static int numeroCidades(){
        return destinationCities.size();
    }
    
}
