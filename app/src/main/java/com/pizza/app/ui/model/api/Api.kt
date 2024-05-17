package com.pizza.app.ui.model.api

data class UserData(
    val login: String,
    val pass: String,
    val name: String,
    val post: String,

)

class Api {

    private val list = listOf(
        UserData("pizzaTop@gmail.com", "qwerty", "Ирина", "Директор"),
        UserData("irina@mail.com", "povar", "Ирина", "Повар"),
        UserData("123", "123", "Максим", "Разработчик")
    )

    fun login(login: String, pass: String): UserData? {
        list.forEach {
            if (it.login == login && it.pass == pass)
                return it
        }
        return null
    }

}