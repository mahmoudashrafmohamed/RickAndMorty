package com.orcas.entities.home
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CharacterResponse(
  @SerializedName("info")
  val info: Info,
  @SerializedName("results")
  val data: List<Character>
)

data class Info(
  @SerializedName("count")
  val count: Int,
  @SerializedName("pages")
  val pages: Int,
  @SerializedName("next")
  val next: String,
  @SerializedName("prev")
  val prev: Any?
)
@Parcelize
data class Character(
  @SerializedName("id")
  val id: Int,
  @SerializedName("name")
  val name: String,
  @SerializedName("status")
  val status: String,
  @SerializedName("species")
  val species: String,
  @SerializedName("type")
  val type: String,
  @SerializedName("gender")
  val gender: String,
  @SerializedName("origin")
  val origin: Origin,
  @SerializedName("location")
  val location: Location,
  @SerializedName("image")
  val image: String,
  @SerializedName("episode")
  val episode: List<String>,
  @SerializedName("url")
  val url: String,
  @SerializedName("created")
  val created: String
) : Parcelable

@Parcelize
data class Origin(
  @SerializedName("name")
  val name: String,
  @SerializedName("url")
  val url: String
) : Parcelable

@Parcelize
data class Location(
  @SerializedName("name")
  val name: String,
  @SerializedName("url")
  val url: String
) : Parcelable
