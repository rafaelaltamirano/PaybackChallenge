package com.example.paybackchallenge.domain.response

import com.example.paybackchallenge.domain.Response
import com.example.paybackchallenge.domain.entities.Image
import com.google.gson.annotations.SerializedName

class ImageResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("pageURL") val pageURL: String,
    @SerializedName("type") val type: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewURL: String,
    @SerializedName("previewWidth") val previewWidth: Int,
    @SerializedName("previewHeight") val previewHeight: Int,
    @SerializedName("webformatURL") val webformatURL: String,
    @SerializedName("webformatWidth") val webformatWidth: Int,
    @SerializedName("webformatHeight") val webformatHeight: Int,
    @SerializedName("largeImageURL") val largeImageURL: String,
    @SerializedName("fullHDURL") val fullHDURL: String?,
    @SerializedName("imageURL") val imageURL: String?,
    @SerializedName("imageWidth") val imageWidth: Int,
    @SerializedName("imageHeight") val imageHeight: Int,
    @SerializedName("imageSize") val imageSize: Int,
    @SerializedName("views") val views: Int,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("comments") val comments: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("user") val user: String,
    @SerializedName("userImageURL") val userImageURL: String
) : Response<Image> {
    override fun toEntity() = Image(
        id = id,
        pageURL = pageURL,
        type = type,
        tags = tags,
        previewURL = previewURL,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatURL = webformatURL,
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageURL = largeImageURL,
        fullHDURL = fullHDURL,
        imageURL = imageURL,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        user_id = user_id,
        user = user,
        userImageURL = userImageURL
    )
}