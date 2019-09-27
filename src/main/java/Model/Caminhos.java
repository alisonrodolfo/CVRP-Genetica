/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.alisonbarreiro.cvrp.FXMLController;
import java.util.ArrayList;


/**
 *
 * @author Alison Rodolfo
 */
public class Caminhos {


    private ArrayList<Vertice> caminho = new ArrayList<Vertice>();
    // Cache
    private double melhorDosMelhores = 0;
    private int distancia = 0;


    public Caminhos() {
        for (int i = 1; i < CaminhosControl.numeroCidades(); i++) {
            caminho.add(null);
        }
    }

    public Caminhos(ArrayList caminho) {
        this.caminho = caminho;
    }

 
    public void generateIndividual() {
        // Percorra todas as cidades de destino e adicione-as ao nosso caminho
        this.toString();
        int caminhosAresta = 0;

        for (int cityIndex = 0; cityIndex < CaminhosControl.numeroCidades() - 1; cityIndex++) {
            setCidade(cityIndex, CaminhosControl.getCidade(cityIndex + 1));
        }

        //Collections.shuffle(tour);
    }
   
    public Vertice getCidade(int caminhoPosition) {
        return (Vertice) caminho.get(caminhoPosition);
    }

    public void setCidade(int caminhoPosition, Vertice cidade) {
        caminho.set(caminhoPosition, cidade);
        // Se os caminhos foram alterados, precisamos redefinir o melhorDosMelhores e a distância
        melhorDosMelhores = 0;
        distancia = 0;
    }
    public double getMelhordosMelhores() {
        if (melhorDosMelhores == 0) {
            melhorDosMelhores = 1 / (double) getDistance();
        }
        return melhorDosMelhores;
    }
    // Distancia Total
    public int getDistance() {
        if (distancia == 0) {
            int caminhoDistance = 0;
            int aux1, aux2;
           
            caminhoDistance += FXMLController.arestas.get(0).get(getCidade(0).getId()).getPeso();
            for (int j1 = 0, j2 = 1; j1 < caminhoSize() - 1; j1++, j2++) {
                aux1 = getCidade(j1).getId();
                aux2 = getCidade(j2).getId();
                caminhoDistance += FXMLController.arestas.get(aux1).get(aux2).getPeso();

            }

            distancia = caminhoDistance;
        }
        return distancia;
    }
    public int caminhoSize() {
        return caminho.size();
    }
    public boolean containsCity(Vertice cliente) {
        return caminho.contains(cliente);
    }
    @Override
    public String toString() {
        
        String geneString = "0";
        for (int i = 0; i < caminhoSize(); i++) {
            geneString += "," + getCidade(i);
        }
        //geneString += ";";
        return geneString;
    }

}
