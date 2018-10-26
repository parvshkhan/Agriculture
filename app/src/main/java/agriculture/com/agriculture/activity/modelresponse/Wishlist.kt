package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class Wishlist(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") val payLoad: List<PayLoad>
) {

    data class PayLoad(
            @SerializedName("id") val id: Int,
            @SerializedName("name") val name: String,
            @SerializedName("owner") val owner: String,
            @SerializedName("total_fund") val totalFund: Float,
            @SerializedName("roi") val roi: Float,
            @SerializedName("address") val address: String,
            @SerializedName("target") val target: String,
            @SerializedName("wishlistId") val wishlistId: Int,
            @SerializedName("ownerImg") val ownerImg: String,
            @SerializedName("galleryImg") val galleryImg: String,
            @SerializedName("funded") val funded: Float,
            @SerializedName("total") val total: Float,
            @SerializedName("diffDate") val diffDate: Int
    )
}