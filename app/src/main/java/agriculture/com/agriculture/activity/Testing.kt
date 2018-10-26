package agriculture.com.agriculture.activity

import com.google.gson.annotations.SerializedName


data class Testing(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("isError") val isError: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("payLoad") val payLoad: PayLoad
) {

    data class PayLoad(
            @SerializedName("page") val page: Page,
            @SerializedName("heading") val heading: List<Heading>
    ) {

        data class Page(
                @SerializedName("id") val id: Int,
                @SerializedName("title") val title: String,
                @SerializedName("description") val description: String,
                @SerializedName("image") val image: String
        )


        data class Heading(
                @SerializedName("title") val title: String,
                @SerializedName("content") val content: List<Content>
        ) {

            data class Content(
                    @SerializedName("question") val question: String,
                    @SerializedName("answer") val answer: String
            )
        }
    }
}