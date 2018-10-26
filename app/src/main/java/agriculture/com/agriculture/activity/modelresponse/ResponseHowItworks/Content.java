
package agriculture.com.agriculture.activity.modelresponse.ResponseHowItworks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page_headings_id")
    @Expose
    private Integer pageHeadingsId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageHeadingsId() {
        return pageHeadingsId;
    }

    public void setPageHeadingsId(Integer pageHeadingsId) {
        this.pageHeadingsId = pageHeadingsId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
