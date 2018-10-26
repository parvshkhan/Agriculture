package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class PropertyList(
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
            @SerializedName("roi") val roi: String,
            @SerializedName("address") val address: String,
            @SerializedName("ownerImg") val ownerImg: String,
            @SerializedName("galleryImg") val galleryImg: String,
            @SerializedName("diffDate") val diffDate: Int,
            @SerializedName("funded") val funded: Float,
            @SerializedName("target") val target: Float,
            @SerializedName("wishlistId") var wishlist: Int,

            var isSelect :Boolean

    )
}