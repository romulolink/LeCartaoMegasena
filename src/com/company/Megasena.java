package com.company;

import java.util.Enumeration;
import java.util.Hashtable;

// import imglib.*;
// Quantidade de Marcacoes: 48
// Marcações: 19
// LinhasId: 29
// Na primeira fileira 25
// Na primeira coluna 5


public class Megasena {


    public static boolean cinza(int[] pix) {

        int intervalo = 40;
        /*
        As marcações estão em tonalidades de cinza logo:
        O Cinza é um Trio de números RGB que o modulo da diferença entre eles está em um intervalo curto.
         */
        return    pix[0] > 0 && // diferente de preto
                ((Math.abs(pix[0] - pix[1])) < intervalo) &&
                ((Math.abs(pix[0] - pix[2])) < intervalo) &&
                ((Math.abs(pix[1] - pix[2])) < intervalo) &&
                ( pix[0] + pix[1] + pix[2]) / 3 < 105  ; // seleciona tons de cinza mais escuros


    }

    public static boolean preto(int[] pix) {
        return pix[0] == 0 && pix[1] == 0 && pix[2] == 0;
    }

    public static  boolean isEquals(int x, int y){

        return x == y;
    }


    /*
    Encontra todas as ocorrencias na mesma linha entre os artefatos, deve haver pelo menos 100 ocorrencias entre os artefatos.
     */
    public static boolean comparaPontos(Artefato A, Artefato M) {

        int m = 0;
        int n = 0;
        int acc = 0;

        for (m = 0; m < A.tamanho(); m++) {

            for (n = 0; n < M.tamanho(); n++) {

                // verifica se estão na mesma linha
                if (A.pega(m).y() == M.pega(n).y()) {

                    // pelo menos cem ocorrencias no mesmo artefato
                    // Evitar marcação de Bola que perternce a outra linha
                    if(acc == 100){
                        System.out.printf("Ocorrencia: x= " + A.pega(m).y() + " y= " + M.pega(n).y() + " ");
                        return true;
                    }
                    acc++;


                }


            }

        }
        return false;


    }



    public static void main(String[] args) {

        cImagem img = new cImagem("c:/assets/megasena.png");
        // Quantidade de Marcacoes: 48
        // Marcações: 19
        // LinhasId: 29
        // Na primeira fileira 25
        // Na primeira coluna 5


        int W = img.pegaLargura();
        int H = img.pegaAltura();
        int x;
        int y;
        int[] pix;
        int k,i,j;

        Lista Marcacoes = new Lista();
        Lista Bolinhas = new Lista();
        Ponto ptocinza;
        Artefato A;
        Artefato M;
        Artefato Base;



        // Encontrar Distancia Colunas
        /*for (y=0; y < 150; y++) {
            for (x = 0; x < 250; x++) {
                pix = img.Pixel(x, y);
                if (cinza(pix)) {

                    System.out.printf("x = " + x + " y = " + y);
                    contCinza++;

                    ptocinza = new Ponto(x,y);
                    k = Marcacoes.busca(ptocinza);
                    if (k >= 0) {
                        Base = Marcacoes.pega(k);
                        Base.inserePonto(ptocinza);
                        i=k+1;
                        A = Marcacoes.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptocinza)) {
                                for (j=0; j < A.tamanho(); j++) {
                                    Base.inserePonto(A.pega(j));
                                }
                                Marcacoes.remove(i);
                                System.out.println("Agrupou");
                            }
                            i++;
                            A = Marcacoes.pega(i);
                        }
                    } else {
                        A = new Artefato();
                        A.inserePonto(ptocinza);
                        Marcacoes.insere(A);
                        System.out.println("Novo artefato " + Marcacoes.tamanho());
                    }
                }
            }
        }*/


        // Encontrar Marcações
        for (y=140; y < H; y++) {
            for (x = 0; x < 24; x++) {
                pix = img.Pixel(x, y);
                if (cinza(pix)) {
                    System.out.printf("r = " + pix[0] + " g= " + pix[1] + " b = " + pix[2] + "\n");
                    contCinza++;

                    ptocinza = new Ponto(x,y);
                    k = Marcacoes.busca(ptocinza);
                    if (k >= 0) {
                        Base = Marcacoes.pega(k);
                        Base.inserePonto(ptocinza);
                        i=k+1;
                        A = Marcacoes.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptocinza)) {
                                for (j=0; j < A.tamanho(); j++) {
                                    Base.inserePonto(A.pega(j));
                                }
                                Marcacoes.remove(i);
                                System.out.println("Agrupou");
                            }
                            i++;
                            A = Marcacoes.pega(i);
                        }
                    } else {
                        A = new Artefato();
                        A.inserePonto(ptocinza);
                        Marcacoes.insere(A);
                        System.out.println("Novo artefato " + Marcacoes.tamanho() + " x= " + A.pega(0).x() + " y=" +  A.pega(0).y() );
                    }
                }
            }
        }


        // Encontra Bolinhas
        for (y=0; y < H; y++) {
            for (x = 0; x < W; x++) {
                pix = img.Pixel(x, y);
                if (preto(pix)) {
                    cont2++;

                    ptocinza = new Ponto(x,y);
                    k = Bolinhas.busca(ptocinza);
                    if (k >= 0) {
                        Base = Bolinhas.pega(k);
                        Base.inserePonto(ptocinza);
                        i=k+1;
                        A = Bolinhas.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptocinza)) {
                                for (j=0; j < A.tamanho(); j++) {
                                    Base.inserePonto(A.pega(j));
                                }
                                Bolinhas.remove(i);
                                System.out.println("Agrupou");
                            }
                            i++;
                            A = Bolinhas.pega(i);
                        }
                    } else {
                        A = new Artefato();
                        A.inserePonto(ptocinza);
                        Bolinhas.insere(A);
                        System.out.println("Novo artefato " + Bolinhas.tamanho());
                    }
                }
            }
        }




        Hashtable<Integer,Integer> C = new Hashtable<Integer,Integer>();
        int t;
        int z;

        System.out.printf("Lista de Marcacoes: \n");
        for (i=0; i < Marcacoes.tamanho(); i++) {
            A = Marcacoes.pega(i);
            t = A.tamanho();
            if (C.get(t)== null) {
                C.put(t, 1);
            } else {
                z = C.get(t)+1;
                C.put(t,z);
            }

            A.calcCentro();
            System.out.printf("Linha " + (i+1)  + " em x=" + A.getCentro().x() + " y=" + A.getCentro().y() + "\n" ) ;

        }


        System.out.printf("Lista de Marcações: \n");
        for (i=0; i < Bolinhas.tamanho(); i++) {
            M = Bolinhas.pega(i);
            t = M.tamanho();
            if (C.get(t)== null) {
                C.put(t, 1);
            } else {
                z = C.get(t)+1;
                C.put(t,z);
            }

            M.calcCentro();
            System.out.printf("Marcação " + (i+1)  + " em x=" + M.getCentro().x() + " y=" + M.getCentro().y() + "\n" ) ;

        }


        int numOcorrencias =0;


        System.out.printf("Comparações: \n");

        // Percorre todos as Marcadores e Bolinhas
        for (i=0; i < Marcacoes.tamanho(); i++) {

            for (j=0; j < Bolinhas.tamanho(); j++){

                A = Marcacoes.pega(i);
                M = Bolinhas.pega(j);



                // Compara todos os Pontos dos Marcacoes, caso encontre pelo menos cem ocorrencias na mesma linha,
                // para que não se contabilize a marcação de outra linha, retorna true;
                if(comparaPontos(A,M)){
                    
                    //System.out.printf("Houve uma occorencia de Linha x Marcação em \n");

        
                    System.out.printf("\nO jogador marcou na dezena " + Math.round(M.pega(0).x() / 50) + " Na linha "+ (i) + "\n");


                }





            }


        }





        /*// Exibe Marcacoes iguais agrupados
        for (Enumeration e=C.keys(); e.hasMoreElements();) {
            // editar por romulo em 08/12/2014
            z = (Integer)e.nextElement();
           //  System.out.println("Index: " + z + ": Marcacoes " + C.get(z));

        }*/
        System.out.printf("Total Linhas:" + Marcacoes.tamanho());

        System.out.printf(" \n");

        System.out.printf("Total Marcações:" + Bolinhas.tamanho());

        System.out.printf(" \n");

        System.out.printf("Total Ocorrencias:" + numOcorrencias);

    }





}

