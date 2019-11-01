package com.example.hangman.data.model

data class Rooms(
	val maxPlayer: Int? = null,
	val name: String? = null,
	val admin: String? = null,
	val id: String? = null,
	val word: String? = null,
	val participants: List<String?>? = null,
	val status: String? = null
)
