package com.example.hangman.data.model

// Room 내의 방 최대 인원을 설정해 주는 최대 인원 Entity
data class Participants(
	val current: Int? = null,
	val max: Int? = null
)
