package com.example.hangman.data.model

data class User(
	val password: String? = null,
	val level: Int? = null,
	val ready: Boolean? = null,
	val id: String? = null,
	val winningRate: WinningRate? = null
)
