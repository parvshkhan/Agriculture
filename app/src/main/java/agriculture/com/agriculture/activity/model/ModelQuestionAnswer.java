package agriculture.com.agriculture.activity.model;

import java.util.LinkedHashMap;

public class ModelQuestionAnswer {


    public ModelQuestionAnswer(String questions, String answers) {
        this.questions = questions;
        this.answers = answers;
    }

    private String questions;

    public boolean isCollapsed = false;

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    private String answers;
}
