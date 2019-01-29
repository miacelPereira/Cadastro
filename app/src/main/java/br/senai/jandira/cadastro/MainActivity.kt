package br.senai.jandira.cadastro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnSave.setOnClickListener {
            val nome = txtNome.text.toString()
            val email = txtEmail.text.toString()
            val senha = txtPass.text.toString()

            if(!minimoCaracter(nome, 3)){
                txtNome.error = "O nome deve ter no mínimo 3 caracteres "
            }
            if(!confirmEmail(email)){
                txtEmail.error = "O e-mail deve ter @ "
            }
            if(!minimoCaracter(senha, 4)){
                txtPass.error = "A senha deve conter no mínimo 4 caracteres "
            }
            if(!textContemNumero(senha)){
                txtPass.error = "A senha deve conter números"
            }
            if(sequenciaNumericaSucesso(senha)){
                txtPass.error = "A senha não deve conter sequência númerica "
            }

        }

    }
}
