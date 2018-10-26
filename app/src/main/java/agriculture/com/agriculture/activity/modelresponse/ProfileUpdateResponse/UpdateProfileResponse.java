package agriculture.com.agriculture.activity.modelresponse.ProfileUpdateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("isError")
    @Expose
    private Boolean isError;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payLoad")
    @Expose
    private PayLoad payLoad;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PayLoad getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(PayLoad payLoad) {
        this.payLoad = payLoad;
    }

}