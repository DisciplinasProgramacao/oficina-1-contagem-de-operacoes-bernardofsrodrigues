import java.util.Random;
import java.util.function.Consumer;
import sorts.Bubblesort;
import java.util.Arrays;

public class App {
    static int[] tamanhosTesteGrande =  { 500_000, 1_000_000, 2_000_000, 3_000_000, 5_000_000, 10_000_000 };
    static int[] tamanhosTesteMedio =   { 12_500,  25_000,  50_000,   100_000,   200_000 };
    static int[] tamanhosTestePequeno = { 3, 6, 12, 24, 48 };
    static Random aleatorio = new Random(42);

    static int operacoesCodigo1 = 0;
    static int operacoesCodigo2 = 0;
    static int operacoesCodigo3 = 0;
    static int operacoesCodigo4 = 0;

    static long tempoCodigo1 = 0;
    static long tempoCodigo2 = 0;
    static long tempoCodigo3 = 0;
    static long tempoCodigo4 = 0;

    static int codigo1(int[] vetor) {
        int resposta = 0;
        operacoesCodigo1 = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i] % 2;
            operacoesCodigo1++;
        }
        tempoCodigo1 = System.nanoTime() - startTime;
        return resposta;
    }

    static int codigo2(int[] vetor) {
        int contador = 0;
        operacoesCodigo2 = 0;
        long startTime = System.nanoTime();
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
                operacoesCodigo2++;
            }
        }
        tempoCodigo2 = System.nanoTime() - startTime;
        return contador;
    }

    static int codigo3(int[] vetor) {
        operacoesCodigo3 = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[menor]) menor = j;
                operacoesCodigo3++;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
        tempoCodigo3 = System.nanoTime() - startTime;
        return 0;
    }

    static int codigo4(int n) {
        operacoesCodigo4 = 0;
        long startTime = System.nanoTime();
        if (n <= 2)
            return 1;
        else {
            operacoesCodigo4++;
            return codigo4(n - 1) + codigo4(n - 2);
        }
    }

    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho / 2);
        }
        return vetor;
    }

    public static void main(String[] args) {
        System.out.println("Testando algoritmos...");

        // Testar Código 1 (Teste Grande)
        for (int tamanho : tamanhosTesteGrande) {
            int[] vec = gerarVetor(tamanho);
            System.out.println("Executando código 1 para vetor de tamanho " + tamanho);
            codigo1(vec);
            System.out.println("Operações: " + operacoesCodigo1 + ", Tempo: " + tempoCodigo1 + " ns");
        }

        // Testar Código 2 (Teste Grande)
        for (int tamanho : tamanhosTesteGrande) {
            int[] vec = gerarVetor(tamanho);
            System.out.println("Executando código 2 para vetor de tamanho " + tamanho);
            codigo2(vec);
            System.out.println("Operações: " + operacoesCodigo2 + ", Tempo: " + tempoCodigo2 + " ns");
        }

        // Testar Código 3 (Teste Médio)
        for (int tamanho : tamanhosTesteMedio) {
            int[] vec = gerarVetor(tamanho);
            System.out.println("Executando código 3 para vetor de tamanho " + tamanho);
            codigo3(vec);
            System.out.println("Operações: " + operacoesCodigo3 + ", Tempo: " + tempoCodigo3 + " ns");
        }

        // Testar Código 4 (Teste Pequeno)
        for (int n : tamanhosTestePequeno) {
            System.out.println("Executando código 4 para n = " + n);
            codigo4(n);
            System.out.println("Operações: " + operacoesCodigo4 + ", Tempo: " + tempoCodigo4 + " ns");
        }
    }
}
