package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class ResponseShowProfile(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") val payLoad: PayLoad
) {

    data class PayLoad(
            @SerializedName("id") val id: Int,
            @SerializedName("first_name") val firstName: String,
            @SerializedName("last_name") val lastName: String?,
            @SerializedName("email") val email: String,
            @SerializedName("mobile") val mobile: String,
            @SerializedName("image") val image: String?
    )
}