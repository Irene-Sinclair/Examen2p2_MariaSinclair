package Examen;

import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Jugar implements Runnable {

    private JLabel tiempo;
    private JProgressBar barra1;
    private JProgressBar barra2;
    private boolean empezo;
    private int velo1;
    private int velo2;
    private String jugador1;
    private String jugador2;

    public Jugar(JLabel tiempo, JProgressBar barra1, JProgressBar barra2, int velo1, int velo2, String jugador1, String jugador2) {
        this.tiempo = tiempo;
        this.barra1 = barra1;
        this.barra2 = barra2;
        this.empezo = empezo;
        this.velo1 = velo1;
        this.velo2 = velo2;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void run() {
        long tiempoInicio = System.currentTimeMillis();

        while (true) {

            long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;

            long minutos = tiempoTranscurrido / (1000 * 60);
            long segundos = (tiempoTranscurrido / 1000) % 60;

            tiempo.setText(String.format("%02d:%02d", minutos, segundos));

            barra1.setValue(barra1.getValue() + velo1 / 10);
            barra2.setValue(barra2.getValue() + velo2 / 10);

            if (barra1.getValue() >= barra1.getMaximum() || barra2.getValue() >= barra2.getMaximum()) {
                mostrarResultado(new long[]{minutos, segundos});
                empezo = false;
                break;
            }

            try {

                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void mostrarResultado(long[] tiempo) {
        String ganador;
        if (barra1.getValue() > barra2.getValue()) {
            ganador = jugador1;
        } else if (barra1.getValue() < barra2.getValue()) {
            ganador = jugador2;
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un empate" + ", con un tiempo de: " + tiempo[0] + " minutos y " + tiempo[1] + " segundos");
            return;
        }
        JOptionPane.showMessageDialog(null, ganador + " ha ganado, con un tiempo de: " + tiempo[0] + " minutos y " + tiempo[1] + " segundos");
    }

}
