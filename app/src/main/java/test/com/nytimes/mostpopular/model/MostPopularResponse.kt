package test.com.nytimes.mostpopular.model

import android.os.Parcel
import android.os.Parcelable

data class MostPopularResponse(
        val status: String?,
        val copyright: String?,
        val num_results: Long?,
        val results: List<MostPopularResult>
)

data class MostPopularResult(
        val url: String,
        val adx_keywords: String,
        val column: String?,
        val section: String,
        val byline: String,
        val type: String,
        val title: String,
        val abstract: String,
        val published_date: String,
        val source: String
 ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(url)
        dest.writeString(adx_keywords)
        dest.writeString(column)
        dest.writeString(section)
        dest.writeString(byline)
        dest.writeString(type)
        dest.writeString(title)
        dest.writeString(abstract)
        dest.writeString(published_date)
        dest.writeString(source)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MostPopularResult> {
        override fun createFromParcel(parcel: Parcel): MostPopularResult {
            return MostPopularResult(parcel)
        }

        override fun newArray(size: Int): Array<MostPopularResult?> {
            return arrayOfNulls(size)
        }
    }
}