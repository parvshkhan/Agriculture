package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class AddRemoveWishlist(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") val payLoad: PayLoad
) {

    data class PayLoad(
            @SerializedName("id") val id: Int
    )
}