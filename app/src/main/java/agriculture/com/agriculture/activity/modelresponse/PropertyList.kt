package agriculture.com.agriculture.activity.modelresponse

import com.google.gson.annotations.SerializedName


data class PropertyList(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") public val payLoad: List<PayLoad>
) {

    data class PayLoad(
            @SerializedName("id")  val id: Int,
            @SerializedName("name") val name: String,  // propert name
            @SerializedName("owner") val owner: String,
            @SerializedName("owner_image_id") val ownerImageId: Int,
            @SerializedName("total_fund") val totalFund: Int,
            @SerializedName("roi") val roi: Int,
            @SerializedName("start_date") val startDate: String,
            @SerializedName("end_date") val endDate: String,
            @SerializedName("investment_care") val investmentCare: String,
            @SerializedName("farm_detail") val farmDetail: String,
            @SerializedName("slug") val slug: String,
            @SerializedName("address") val address: String,  // property address
            @SerializedName("country_id") val countryId: Int,
            @SerializedName("city_id") val cityId: Int,
            @SerializedName("state_id") val stateId: Int,
            @SerializedName("zipcode_id") val zipcodeId: Int,
            @SerializedName("created_at") val createdAt: String,
            @SerializedName("updated_at") val updatedAt: String,
            @SerializedName("ownerImg") val ownerImg: String,
            @SerializedName("galleryImg") val galleryImg: String,
            @SerializedName("diffDate") val diffDate: Int
    )
}