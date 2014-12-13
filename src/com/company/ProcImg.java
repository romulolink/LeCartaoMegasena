package com.company;


// import imglib.*;
import java.util.*;


public class ProcImg {


    public static boolean vermelho(int[] pix) {
        return (pix[0]==255) && (pix[1]==0) && (pix[2] == 0);
    }

    public static void main(String[] args) {

        cImagem img = new cImagem("c:/assets/bolas.png");
        int W = img.pegaLargura();
        int H = img.pegaAltura();
        int x;
        int y;
        int[] pix;
        int k,i,j;

        Lista Artefatos = new Lista();
        Ponto ptoVermelho;
        Artefato A;
        Artefato Base;
        int cont1=0;
        int cont2=0;

        for (y=0; y < H; y++) {
            for (x = 0; x < W; x++) {
                pix = img.Pixel(x, y);
                if (vermelho(pix)) {
                    cont1++;
                    ptoVermelho = new Ponto(x,y);
                    k = Artefatos.busca(ptoVermelho);
                    if (k >= 0) {
                        Base = Artefatos.pega(k);
                        Base.inserePonto(ptoVermelho);
                        i=k+1;
                        A = Artefatos.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptoVermelho)) {
                                for (j=0; j < A.tamanho(); j++) {
                                    Base.inserePonto(A.pega(j));
                                }
                                Artefatos.remove(i);
                                System.out.println("Agrupou");
                            }
                            i++;
                            A = Artefatos.pega(i);
                        }
                    } else {
                        A = new Artefato();
                        A.inserePonto(ptoVermelho);
                        Artefatos.insere(A);
                        System.out.println("Novo artefato " + Artefatos.tamanho());
                    }
                }
            }
        }
        Hashtable<Integer,Integer> C = new Hashtable<Integer,Integer>();
        int t;
        int z;
        for (i=0; i < Artefatos.tamanho(); i++) {
            A = Artefatos.pega(i);
            t = A.tamanho();
            if (C.get(t)== null) {
                C.put(t, 1);
            } else {
                z = C.get(t)+1;
                C.put(t,z);
            }
        }

        for (Enumeration e=C.keys(); e.hasMoreElements();) {
            // editar por romulo em 08/12/2014
            z = (Integer)e.nextElement();
            System.out.println(z + ": " + C.get(z));
        }

    }

}
