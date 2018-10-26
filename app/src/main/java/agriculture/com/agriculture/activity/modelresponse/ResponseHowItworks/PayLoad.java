
package agriculture.com.agriculture.activity.modelresponse.ResponseHowItworks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayLoad {

    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("heading")
    @Expose
    private List<Heading> heading = null;
    @SerializedName("contents")
    @Expose
    private List<List<Content>> contents = null;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Heading> getHeading() {
        return heading;
    }

    public void setHeading(List<Heading> heading) {
        this.heading = heading;
    }

    public List<List<Content>> getContents() {
        return contents;
    }

    public void setContents(List<List<Content>> contents) {
        this.contents = contents;
    }

}
