package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class SignUpResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") val payLoad: PayLoad
) {

    data class PayLoad(
            @SerializedName("first_name") val firstName: String,
            @SerializedName("email") val email: String,
            @SerializedName("mobile") val mobile: Int,
            @SerializedName("id") val id: Int
    )
}