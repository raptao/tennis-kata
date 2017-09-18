package fr.raptao.tennis;


public class Score {

    private int score;

    /**
     * This method increments the score
     * @return true if the score has been incremented, false otherwise
     */
    public boolean increment() {
        if( score >= 40 ){
            return false;
        }
        if (score == 0 || score == 15) {
            score += 15;
        } else if (score == 30) {
            score = 40;
        }
        return true;
    }

    /**
     *
     * @return the current value of the score
     */
    public int currentScore() {
        return score;
    }
}
