package com.example.hangman.data.model

data class Room(
	val maxPlayer: Int? = null,
	val name: String? = null,
	val admin: String? = null,
	val id: String? = null,
	val word: String? = null,
	val participants: List<String?>? = null,
	val status: String? = null
)
