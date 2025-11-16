package Core;
public class EventResult {
    private final String storyText;
    private boolean gameOver = false;
    public EventResult(String storyText) {
        this.storyText = storyText;
    }
    public EventResult(String storyText, boolean gameOver) {
        this.storyText = storyText;
        this.gameOver = gameOver;
    }
    public String getStoryText() {
        return storyText;
    }
    public boolean isGameOver() {
        return gameOver;
    }
}
