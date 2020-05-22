package com.example.testtaskappandroid

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class AuthorizationActivity : AppCompatActivity() {
    val APP_PREFERENCES = "mysettings"
    val USER_EMAIL = "email"
    val USER_PASSWORD = "password"
    lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var users = Users()
        users.fillList()
        button.setOnClickListener() {
            pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            if (pref.contains(USER_EMAIL)) {
                var email = pref.getString(USER_EMAIL, "");
                var password = pref.getString(USER_PASSWORD, "")
            }
            var email = emailField.text.toString().toLowerCase();
            var password = passwordField.text.toString()
            if (users.findUser(email, password) && checkEmail(email)
                && checkPassword(password)
            ) {
                val editor = pref.edit()
                editor.putString(USER_EMAIL, email)
                editor.putString(USER_PASSWORD, password)
                editor.apply()
                val intent = Intent(this, ProfileActivity::class.java)
                var autorizatedUser = users.findUserByEmail(email);
                if (autorizatedUser != null) {
                    intent.putExtra("email", autorizatedUser.email)
                    intent.putExtra("name", autorizatedUser.nameAndLastName)
                    intent.putExtra("picture", autorizatedUser.picture.toString())
                }
                startActivity(intent)

            } else if (!checkEmail(email)) {
                Toast.makeText(this, "Введите корректный e-mail", Toast.LENGTH_LONG).show()
            } else if (!checkPassword(password)) {
                var warning: String = "Пароль долежн быть длинее 6-ти символов, " +
                        "содержать хотя бы одну заглавную букву, строчную букву и цифру";
                Toast.makeText(this, warning, Toast.LENGTH_LONG).show()
            } else {
                var warning: String = "Неверный логин или пароль"
                Toast.makeText(this, warning, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checkEmail(email: String): Boolean {
        val regex = Regex(".+@.+\\.(.)+")
        return regex.matches(email)
    }

    fun checkPassword(password: String): Boolean {
        val regex1 = Regex(".*[a-z]+.*")
        val regex2 = Regex(".*[A-Z]+.*")
        val regex3 = Regex(".*\\d+.*")
        return regex1.matches(password) && regex2.matches(password)
                && regex3.matches(password) && password.length > 5;
    }
}
