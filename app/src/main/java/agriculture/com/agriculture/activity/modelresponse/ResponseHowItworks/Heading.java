
package agriculture.com.agriculture.activity.modelresponse.ResponseHowItworks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Heading {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page_id")
    @Expose
    private Integer pageId;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
