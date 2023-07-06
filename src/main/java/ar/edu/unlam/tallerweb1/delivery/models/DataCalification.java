package ar.edu.unlam.tallerweb1.delivery.models;

public class DataCalification {
    private Long calificationId;
    private String description;
    private int score;
    private Long lessonId;


    public Long getCalificationId() {
        return calificationId;
    }

    public void setCalificationId(Long calificationId) {
        this.calificationId = calificationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }
}
