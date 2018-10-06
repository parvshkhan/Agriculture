package agriculture.com.agriculture.activity.modelresponse

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class EventResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payload") val payload: List<Payload>
) {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Payload(
            @SerializedName("id") val id: Int,
            @SerializedName("name") val name: String,
            @SerializedName("description") val description: String,
            @SerializedName("start_date") val startDate: String,
            @SerializedName("end_date") val endDate: String,
            @SerializedName("start_time") val startTime: String,
            @SerializedName("end_time") val endTime: String,
            @SerializedName("location") val location: String,
            @SerializedName("post_by") val postBy: String,
            @SerializedName("mobile") val mobile: String,
            @SerializedName("organizer") val organizer: String,
            @SerializedName("organizer_image") val organizerImage: String,
            @SerializedName("organizer_email") val organizerEmail: String,
            @SerializedName("image") val image: String
    ): Parcelable
}