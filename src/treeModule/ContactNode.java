package treeModule;

public class ScoreNode<E>  {

    private Integer score;
    private Float seconds;
    private String email;

    public ScoreNode<E> (Integer score, Float seconds, String email) {
        this.score = score;
        this.seconds = seconds;
        this.email = email;
    }

}
