package be.tim.fleettracker.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class LocationEntity(
    @SerializedName("id")
    val id: Long,

    @SerializedName(value = "title", alternate = ["header", "name"])
    val title: String?

) : Parcelable {

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LocationEntity> = object : Parcelable.Creator<LocationEntity> {
            override fun createFromParcel(source: Parcel): LocationEntity = LocationEntity(source)
            override fun newArray(size: Int): Array<LocationEntity?> = arrayOfNulls(size)
        }
    }
}
