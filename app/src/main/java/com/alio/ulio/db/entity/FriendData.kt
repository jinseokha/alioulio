package com.alio.ulio.db.entity


data class FriendData(
    val message: String,
    val output: Output
) {
    data class Output(
        val createdAt: Long,
        val favorite: Boolean,
        val profileNickname: String,
        val profileThumbnailImage: String,
        val type: String,
        val updatedAt: Long,
        val uuid: String
    )
}