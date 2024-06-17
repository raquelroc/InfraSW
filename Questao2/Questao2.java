import java.util.Scanner;
import java.util.concurrent.Semaphore;

class Carro extends Thread{
    private String direcao;
    private Boolean sincronia;
    private static final int TEMPO_TRAVESSIA = 1000;
    private static final Semaphore semaphoreDireita = new Semaphore(1);
    private static final Semaphore semaphoreEsquerda = new Semaphore(1);

    public Carro(String direcao, Boolean sincronia) {
        this.direcao = direcao;
        this.sincronia = sincronia;
    }

    @Override
    public void run() {
        try {
            if(sincronia) {
                if(direcao.equals("Direita")) {
                    semaphoreDireita.acquire();
                    System.out.println("Carro vindo da " + direcao + " está entrando na ponte.");
                    Thread.sleep(TEMPO_TRAVESSIA);
                    System.out.println("Carro vindo da " + direcao + " atravessou a ponte.");
                    semaphoreDireita.release();
                }
                if(direcao.equals("Esquerda")) {
                    semaphoreEsquerda.acquire();
                    System.out.println("Carro vindo da " + direcao + " está entrando na ponte.");
                    Thread.sleep(TEMPO_TRAVESSIA);
                    System.out.println("Carro vindo da " + direcao + " atravessou a ponte.");
                    semaphoreEsquerda.release();
                }
            } else {
                System.out.println("Carro sem sicronia vindo da " + direcao + " está entrando na ponte.");
                Thread.sleep(TEMPO_TRAVESSIA);
                System.out.println("Carro sem sicronia vindo da " + direcao + " atravessou a ponte.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Questao2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número 1 para carro sincronizado ou o número 2 para carro sem sincronia: ");
        String entrada = scanner.nextLine();
        scanner.close();

        if(entrada.equals("1")){
            Carro carro1 = new Carro("Direita", true);
            Carro carro2 = new Carro("Direita", true);
            Carro carro3 = new Carro("Esquerda", true);
            Carro carro4 = new Carro("Direita", true);

            carro1.start();
            carro2.start();
            carro3.start();
            carro4.start();

            try {
                carro1.join();
                carro2.join();
                carro3.join();
                carro4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (entrada.equals("2")){
            Carro carro1 = new Carro("Direita", false);
            Carro carro2 = new Carro("Direita", false);
            Carro carro3 = new Carro("Esquerda", false);
            Carro carro4 = new Carro("Direita", false);

            carro1.start();
            carro2.start();
            carro3.start();
            carro4.start();

            try {
                carro1.join();
                carro2.join();
                carro3.join();
                carro4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

