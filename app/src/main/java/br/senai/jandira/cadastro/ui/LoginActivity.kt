package br.senai.jandira.cadastro.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.senai.jandira.cadastro.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            val email = txtEmailLogin.text.toString()
            val senha = txtPassLogin.text.toString()


        }


    }
}
