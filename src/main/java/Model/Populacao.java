/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Alison Rodolfo
 */
public class Populacao {
    
   
    Caminhos[] caminhos;

    // Construir uma população
    public Populacao(int populationSize, boolean initialise) {
        caminhos = new Caminhos[populationSize];
        // inicializar uma população de passeios, faça isso
        if (initialise) {
            //Faça um loop e crie indivíduos
            for (int i = 0; i < populacaoSize(); i++) {
                Caminhos newCaminho = new Caminhos();
                newCaminho.generateIndividual();
                saveCaminho(i, newCaminho);
            }
        }
    }
    
    // 
    public void saveCaminho(int index, Caminhos caminho) {
        caminhos[index] = caminho;
    }
    
    // 
    public Caminhos getCaminho(int index) {
        return caminhos[index];
    }

    // Obtém o melhor caminho da população
    public Caminhos getMelhorCaminho() {
        Caminhos caminho = caminhos[0];
        // Percorra as pessoas para encontrar a melhor
        for (int i = 1; i < populacaoSize(); i++) {
            if (caminho.getMelhordosMelhores() <= getCaminho(i).getMelhordosMelhores()) {
                caminho = getCaminho(i);
            }
        }
        return caminho;
    }

    public int populacaoSize() {
        return caminhos.length;
    }
    
}
