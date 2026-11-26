package pt.ul.fc;

/**
 * Simulação de uma bola em queda, numa caixa vista de lado, sujeita à gravidade.
 * A bola ressalta no chão, perdendo parte da velocidade.
 * Unidades: metros e segundos. A origem é o canto inferior esquerdo da caixa
 * e o eixo y aponta para cima.
 */
public class Simulacao {
    /** Largura da caixa (m). */
    public static final double LARGURA = 6.0;
    /** Altura da caixa (m). */
    public static final double ALTURA = 10.0;
    /** Raio da bola (m). */
    public static final double RAIO = 0.3;
    /**
     * Aceleração da gravidade (m/s²): 9.8 m/s², a apontar para baixo
     * (por isso o valor é negativo).
     */
    public static final double GRAVIDADE = -9.8;
    /**
     * Coeficiente de restituição: fração da velocidade que a bola mantém
     * quando ressalta no chão (85% da velocidade).
     */
    public static final double COEFICIENTE_RESTITUICAO = 0.85;

    private final Bola bola;

    /**
     * Cria uma simulação com a bola dada.
     */
    public Simulacao(Bola bola) {
        this.bola = bola;
    }

    /**
     * Devolve a bola simulada.
     */
    public Bola getBola() {
        return bola;
    }

    /**
     * Avança a simulação dt segundos: a gravidade atua sobre a bola, a bola
     * desloca-se e, se tocar no chão, ressalta.
     *
     * @param dt duração do passo, em segundos
     */
    public void passo(double dt) {
        bola.acelerar(GRAVIDADE, dt);
        bola.mover(dt);
        ressaltar();
    }

    /**
     * Mantém a bola dentro da caixa. No chão, a bola ressalta para cima,
     * mantendo apenas uma fração da velocidade (COEFICIENTE_RESTITUICAO).
     */
    private void ressaltar() {
        if (bola.getX() < RAIO) {
            bola.setPosicao(RAIO, bola.getY());
            bola.setVelocidade(-bola.getVx(), bola.getVy());
        } else if (bola.getX() > LARGURA - RAIO) {
            bola.setPosicao(LARGURA - RAIO, bola.getY());
            bola.setVelocidade(-bola.getVx(), bola.getVy());
        }
        if (bola.getY() < RAIO) {
            bola.setPosicao(bola.getX(), RAIO);
            bola.setVelocidade(bola.getVx(), -bola.getVy() * COEFICIENTE_RESTITUICAO);
        } else if (bola.getY() > ALTURA - RAIO) {
            bola.setPosicao(bola.getX(), ALTURA - RAIO);
            bola.setVelocidade(bola.getVx(), -bola.getVy());
        }
    }
}
