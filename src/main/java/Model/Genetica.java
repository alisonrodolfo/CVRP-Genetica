/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.alisonbarreiro.cvrp.FXMLController;

/**
 *
 * @author Alison Rodolfo
 */
public class Genetica {
    
    


    // Evolui uma população ao longo de uma geração
    public static Populacao evolvePopulacao(Populacao pop) {
        Populacao newPopulacao = new Populacao(pop.populacaoSize(), false);

        // Mantenha nosso melhor indivíduo se o elite estiver ativado
        int eliteOffset = 0;
        if (FXMLController.elite) {
            newPopulacao.saveCaminho(0, pop.getMelhorCaminho());
            eliteOffset = 1;
        }

        // Crossover população
        // Faça um loop sobre o tamanho da nova população e crie indivíduos da
        // População atual
        for (int i = eliteOffset; i < newPopulacao.populacaoSize(); i++) {
            // Selecionar pais
            Caminhos pai = tournamentSelection(pop);
            Caminhos mae = tournamentSelection(pop);
            // Crossover 
            Caminhos guri = crossover(pai, mae);
            // Adicionar filho à nova população
            newPopulacao.saveCaminho(i, guri);
        }

        // Mude um pouco a nova população para adicionar algum novo material genético
        for (int i = eliteOffset; i < newPopulacao.populacaoSize(); i++) {
            mutacao(newPopulacao.getCaminho(i));
        }

        return newPopulacao;
    }

    // Aplica crossover a um conjunto de pais e cria filhos
    public static Caminhos crossover(Caminhos pai, Caminhos mae) {
        // Criar novo caminho
        Caminhos guri = new Caminhos();

        // Obter posições de sub-caminho inicial e final do tour dos pais
        int startPos = (int) (Math.random() * pai.caminhoSize());
        int endPos = (int) (Math.random() * pai.caminhoSize());

        // Faça um loop e adicione o sub caminho de parente e filho
        for (int i = 0; i < guri.caminhoSize(); i++) {
            // Se nossa posição inicial for menor que a posição final
            if (startPos < endPos && i > startPos && i < endPos) {
                guri.setCidade(i, pai.getCidade(i));
            } // Se nossa posição inicial for maior
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    guri.setCidade(i, pai.getCidade(i));
                }
            }
        }

        // Passeie pela caminho 
        for (int i = 0; i < mae.caminhoSize(); i++) {
            //Se a criança não tiver a cidade, adicione-a
            if (!guri.containsCity(mae.getCidade(i))) {
                // Loop para encontrar uma posição livre no passeio da criança
                for (int ii = 0; ii < guri.caminhoSize(); ii++) {
                    // Posição de reposição encontrada, adicionar cidade
                    if (guri.getCidade(ii) == null) {
                        guri.setCidade(ii, mae.getCidade(i));
                        break;
                    }
                }
            }
        }
        
        
        
        
        return guri;
    }

    //  mutação de swap
    private static void mutacao(Caminhos caminho) {
        // Percorra cidades 
        for(int tourPos1=0; tourPos1 < caminho.caminhoSize(); tourPos1++){
            // Aplicar taxa de mutação
            if(Math.random() < FXMLController.TAXA_MUTACAO){
                System.out.println(Math.random());
                // Obter uma segunda posição aleatória no passeio
                int tourPos2 = (int) (caminho.caminhoSize() * Math.random());

                // Obter as cidades na posição desejada no tour
                Vertice city1 = caminho.getCidade(tourPos1);
                Vertice city2 = caminho.getCidade(tourPos2);

                // Troque-os
                caminho.setCidade(tourPos2, city1);
                caminho.setCidade(tourPos1, city2);
            }
        }
    }

    // Seleciona o passeio candidato ao crossover
    private static Caminhos tournamentSelection(Populacao pop) {
        // Crie uma população de torneios
        Populacao tournament = new Populacao(FXMLController.tournamentSize, false);
        // Para cada lugar no caminho, faça um tour aleatório por candidatos e adicione
        for (int i = 0; i < FXMLController.tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populacaoSize());
            tournament.saveCaminho(i, pop.getCaminho(randomId));
        }
        // Pega o melhor caminho
        Caminhos melhor = tournament.getMelhorCaminho();
        return melhor;
    }
    
}
