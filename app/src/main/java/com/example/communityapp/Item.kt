package com.example.communityapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val profileImage: Int = 0,
    val heartImage: Int = 0,
    val answerImage: Int = 0,
    val idText: String = "",
    val content: String = "",
    val heartCount: String = "0",
    val answerCount: String = "0"
): Parcelable