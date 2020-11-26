package be.tim.fleettracker.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @SerializedName(value = "provider")
    val provider: String?,

    @SerializedName(value = "accuracy")
    val accuracy: Float,

    @SerializedName(value = "latitude")
    val latitude: Double,

    @SerializedName(value = "longitude")
    val longitude: Double,

    @SerializedName(value = "speed")
    val speed: Float,

    @SerializedName(value = "time")
    val time: Long,

    @SerializedName(value = "bearing")
    val bearing: Float

) : Parcelable {

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readFloat(),
        source.readDouble(),
        source.readDouble(),
        source.readFloat(),
        source.readLong(),
        source.readFloat()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(provider)
        writeFloat(accuracy)
        writeDouble(latitude)
        writeDouble(longitude)
        writeFloat(speed)
        writeLong(time)
        writeFloat(bearing)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LocationEntity> = object : Parcelable.Creator<LocationEntity> {
            override fun createFromParcel(source: Parcel): LocationEntity = LocationEntity(source)
            override fun newArray(size: Int): Array<LocationEntity?> = arrayOfNulls(size)
        }
    }
}
