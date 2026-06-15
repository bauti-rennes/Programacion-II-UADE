package treeModule;

//Tiene que implementar comparable para ser compatible con un BST
public class ScoreNode implements Comparable<ScoreNode> {

    private Float score;
    private Integer enemies_destroyed;
    private Float seconds;
    private String player;

    public ScoreNode (Integer enemies_destroyed, Float seconds, String player)  {

        /*
        Por la forma que tenemos de calcular score (enemigos/segundos),
         no se pueden tener segundos igual a cero,
         porque el resultado daría error.
         */
        if (seconds == 0)
        {
            throw new IllegalArgumentException("El tiempo no puede ser cero");
        }

        this.enemies_destroyed = enemies_destroyed;
        this.seconds = seconds;
        this.player = player;

        //El score se calcula y asigna automáticamente al crear un nodo
        this.score = (enemies_destroyed/seconds) * 1000;
    }

    //Hacemos los getters para cada elemento del nodo

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


    //Definimos la forma de comparar para poder ordenar
    @Override
    public int compareTo(ScoreNode otherScoreNode) {

        /*
         Tomamos una decisión arriesgada y decidimos comparar por score descendente:
         mayor score va a la izquierda.
         Esto es para que el mayor score aparezca primero en inOrder.
         */

        //Si los scores no son iguales, comparamos por score
        if (!this.score.equals(otherScoreNode.score)) {
            return this.score > otherScoreNode.score ? -1 : 1;
            //Si mi score es mayor, -1
            //Si mi score es menor, 1
        }
        // Si el score es igual, desempatamos por nombre de jugador
        // Esto es necesario para que remove() pueda encontrar el nodo exacto (compareTo == 0)
        return this.player.compareTo(otherScoreNode.player);
    }
}
