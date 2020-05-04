package com.example.nutsnews.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutsnews.core.Constant
import com.google.gson.annotations.SerializedName


@Entity(tableName = Constant.tableName)
data class ArticlesEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name =Column.author)
    @SerializedName(Column.author)
    val author: String? = null,

    @ColumnInfo(name = Column.title)
    @SerializedName(Column.title)
    val title: String? = null,

    @ColumnInfo(name = Column.description)
    @SerializedName(Column.description)
    val description: String? = null,

    @ColumnInfo(name = Column.url)
    @SerializedName(Column.url)
    val url: String? = null,

    @ColumnInfo(name = Column.urlToImage)
    @SerializedName(Column.urlToImage)
    val urlToImage: String? = null,

    @ColumnInfo(name = Column.publishedAt)
    @SerializedName(Column.publishedAt)
    val publishedAt: String? = null,

    @ColumnInfo(name = Column.content)
    @SerializedName(Column.content)
    val content: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticlesEntity> {
        override fun createFromParcel(parcel: Parcel): ArticlesEntity = ArticlesEntity(parcel)

        override fun newArray(size: Int): Array<ArticlesEntity?> {
            return arrayOfNulls(size)
        }

        object Column {
            const val id = "id"
            const val author = "author"
            const val title = "title"
            const val description = "description"
            const val url = "url"
            const val urlToImage = "urlToImage"
            const val publishedAt = "publishedAt"
            const val content = "content"
        }
    }
}