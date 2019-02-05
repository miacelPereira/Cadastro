package br.senai.jandira.cadastro.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.senai.jandira.cadastro.*
import br.senai.jandira.cadastro.viewmodel.CadastroViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Pegando a class
    val viewModel by lazy {
        ViewModelProviders.of(this).get(CadastroViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            val nome = txtNome.text.toString()
            val email = txtEmail.text.toString()
            val senha = txtPass.text.toString()

            if(!minimoCaracter(nome, 3)){
                txtNome.error = R.string.sMinimoCaracterNome.toString()
            }
            if(!confirmEmail(email)){
                txtEmail.error = R.string.sEmailArroba.toString()
            }
            if(!minimoCaracter(senha, 4)){
                txtPass.error = R.string.sSenhaComQuatroCaracter.toString()
            }
            if(!textContemNumero(senha)){
                txtPass.error = R.string.sSenhaComNumeros.toString()
            }
            if(sequenciaNumericaSucesso(senha)){
                txtPass.error = R.string.sSenhaComSequencia.toString()
            }

            viewModel.cadastrarUsuario()
        }

        viewModel.loading.observe(this, Observer {
            updateLoading(it)
        })
    }

    fun updateLoading(loading:Boolean?){

        // Se a variável não for nula ela cai nesse bloco, como se fosse um if
        loading?.let{
            if(loading){
                btnSave.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }else{
                    btnSave.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
            }
        }
    }
}
