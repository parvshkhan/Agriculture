package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class InvestmentDetailResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payload") val payload: List<Payload>
) {

    data class Payload(
            @SerializedName("id") val id: Int,
            @SerializedName("title") val title: String,
            @SerializedName("description") val description: String
    )
}