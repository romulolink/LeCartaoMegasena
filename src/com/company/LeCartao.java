package com.company;

public class LeCartao {


    public static boolean branco(int[] pix) {
        return (pix[0]==255) && (pix[1]==255) && (pix[2] == 255);
    }
    public static void main(String[] args) {
        cImagem img = new cImagem("c:/assets/frutas.png");
        int W = img.pegaLargura();
        int H = img.pegaAltura();
        int x;
        int y;
        int[] pix;
        int k,i,j;

        Lista Artefatos = new Lista();
        Ponto ptoNaoBranco;
        Artefato A;
        Artefato Base;
        int cont1=0;
        int cont2=0;

        for (y=0; y < H; y++) {
            for (x = 0; x < W; x++) {
                pix = img.Pixel(x, y);
                if (!branco(pix)) {
                    cont1++;
                    ptoNaoBranco = new Ponto(x,y);
                    k = Artefatos.busca(ptoNaoBranco);
                    if (k >= 0) {
                        Base = Artefatos.pega(k);
                        Base.inserePonto(ptoNaoBranco);
                        i=k+1;
                        A = Artefatos.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptoNaoBranco)) {
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
                        A.inserePonto(ptoNaoBranco);
                        Artefatos.insere(A);

                        System.out.println("Novo artefato " + Artefatos.tamanho() + A.getCentro());
                    }
                }
            }
        }
        int t;
        int n=0;
        for (i=0; i < Artefatos.tamanho(); i++) {
            A = Artefatos.pega(i);
            t = A.tamanho();
            if (t > 100) {
                System.out.println(i + ": " + t);
                n++;
            }
        }
        System.out.println(n + " frutas");
    }
}