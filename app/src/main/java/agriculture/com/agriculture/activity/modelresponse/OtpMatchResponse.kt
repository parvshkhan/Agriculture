package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class OtpMatchResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String
)