package Rating;

public class RatingPair {
    Integer score;
    String username;

    public void setData(int score, String username){
        this.score = score;
        this.username = username;
    }

    public Integer getScore(){
        return score;
    }

    public String getUsername(){
        return username;
    }
}
