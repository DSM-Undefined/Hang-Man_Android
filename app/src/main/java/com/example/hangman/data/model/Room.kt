package com.example.hangman.data.model

// 방 정보를 가지고 있는 Room Entity
data class Room(
    val name: String? = null,
    val id: String? = null,
    val participants: Participants? = null
)
