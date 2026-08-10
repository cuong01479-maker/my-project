package vn.edu.eaut.lab3;

public class Student {
    private String id;
    private String fullName;
    private double averageScore;

    public Student(String id, String fullName, double averageScore) {
        this.id = id;
        this.fullName = fullName;
        this.averageScore = averageScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getClassification() {
        return classify(averageScore);
    }

    public static String classify(double score) {
        if (score >= 8.5) {
            return "Giỏi";
        } else if (score >= 7) {
            return "Khá";
        } else if (score >= 5) {
            return "Trung bình";
        }
        return "Yếu";
    }
}
