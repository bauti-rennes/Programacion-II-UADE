package treeModule;

public class ScoreNode implements Comparable {

    private Float score;
    private Integer enemies_destroyed;
    private Float seconds;
    private String player;

    public ScoreNode (Integer enemies_destroyed, Float seconds, String player)  {

        /*Por la forma que tenemos de calcular score (ver más abajo),
         no se pueden tener segundos igual a cero,
         porque el resultado sería indeterminado.
         */
        if (seconds == 0)

        {
            throw new IllegalArgumentException("El tiempo no puede ser cero");
        }

        this.enemies_destroyed = enemies_destroyed;
        this.seconds = seconds;
        this.player = player;
        this.score = (enemies_destroyed/seconds) * 1000;
    }

    public Integer getEnemies_destroyed() {
        return enemies_destroyed;
    }

    public Float getScore() {
        return score;
    }

    public Float getSeconds() {
        return seconds;
    }

    public String getPlayer() {
        return player;
    }


    @Override
    public int compareTo(Object otherScoreNode) {
        return this.score > ((ScoreNode) otherScoreNode).score ? -1 : 1;
    }
}
