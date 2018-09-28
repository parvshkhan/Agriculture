package agriculture.com.agriculture.activity.modelresponse

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ProprtyListingSubListing(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") val payLoad: PayLoad
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class PayLoad(
            @SerializedName("id") val id: Int,
            @SerializedName("name") val name: String,
            @SerializedName("owner") val owner: String,
            @SerializedName("total_fund") val totalFund: Int,
            @SerializedName("roi") val roi: Int,
            @SerializedName("investment_care") val investmentCare: String,
            @SerializedName("farm_detail") val farmDetail: String,
            @SerializedName("address") val address: String,
            @SerializedName("ownerImg") val ownerImg: String,
            @SerializedName("galleryImg") val galleryImg: List<String>,
            @SerializedName("document_file") val documentFile: List<String>,
            @SerializedName("document_file_name") val documentFileName: List<String>,
            @SerializedName("diffDate") val diffDate: Int,
            @SerializedName("funded") val funded: Int
    ) : Parcelable
}