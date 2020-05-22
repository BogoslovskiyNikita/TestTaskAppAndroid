package com.example.testtaskappandroid

import android.provider.ContactsContract
import java.util.*

class Users() {
    val list: LinkedList<User> = LinkedList()

    fun fillList() {
        list.add(User("Иван Иванов", "ivanivanov@mail.ru", "MyNameIsIvan1", R.drawable.av2));
        list.add(User("Иван Петров", "petrov1998@ya.ru", "aag4Yerg3g53g", R.drawable.av1));
        list.add(User("Петр Иванов", "ivanovpetr@gmail.com", "GHJKD333KJdfw", R.drawable.av3));
    }

    fun findUser(email: String, password: String): Boolean {
        list.forEach { if ((it.email.equals(email)) && (it.password.equals(password))) {
            return true
        } }
        return false
    }

    fun findUserByEmail(email: String) : User? {
        list.forEach { if (it.email.equals(email)) {
            return it;
        } }
        return null;
    }
}