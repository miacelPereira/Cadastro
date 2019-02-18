package br.senai.jandira.cadastro.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import br.senai.jandira.cadastro.R
import br.senai.jandira.cadastro.model.LoginResult
import br.senai.jandira.cadastro.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {
    val viewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private var errorSnack: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        btnLogin.setOnClickListener{
            val email = txtEmailLogin.text.toString()
            val senha = txtPassLogin.text.toString()

            if(validarEmail(email)){
                viewModel.loginUsuario(email, senha)
            }
        }
        viewModel.loading.observe(this, Observer {
            updateLoading(it)
        })
        viewModel.error.observe(this, Observer {
            updateError(it)
        })
        viewModel.sucesso.observe(this, Observer {
            updateSucesso(it)
        })
    }
    fun validarEmail(email:String):Boolean{
        var retorno = true
        if(!confirmEmail(email)){
            txtEmailLogin.error = "Este e-mail não está dentro dos padrões"
            retorno = false
        }
        return retorno
    }

    fun updateLoading(loading:Boolean?){

        // Se a variável não for nula ela cai nesse bloco, como se fosse um if
        loading?.let{
            if(loading){
                btnLogin.visibility = View.GONE
                progressBarLogin.visibility = View.VISIBLE
            }else{
                btnLogin.visibility = View.VISIBLE
                progressBarLogin.visibility = View.GONE
            }
        }
    }

    fun updateError(error:Boolean?){
        error?.let{
            if(error){
                // Colocando um SnackBAr
                errorSnack = Snackbar.make(rootView, "Você está conectado?", Snackbar.LENGTH_INDEFINITE).apply {
                    setAction("Reconectar", object: View.OnClickListener{
                        override fun onClick(v: View?) {
                            viewModel.loginUsuario(getEmail(), getSenha())
                        }
                    })
                    show()
                }
                progressBar.visibility = View.GONE
                btnSave.visibility = View.VISIBLE
            }else{
                errorSnack?.dismiss()
            }
        }
    }

    fun updateSucesso(result:LoginResult?){
        result?.let {

            val titulo = if(it.sucesso) "Sucesso" else "Erro"

            val funcaoSucesso: (Boolean)->Unit = {// essa função vai receber um Boolean e não irá retornar nada
                if(it) {
                    startActivity<SucessoCadastroActivity>()
                    finish()
                }
            }

            alert ("teste", titulo){
                okButton {
                    funcaoSucesso(result.sucesso)
                }
            }.show()
        }
    }
    fun getEmail(): String{
        val email = txtEmailLogin.text.toString()
        return email
    }
   fun getSenha() : String{
        val senha = txtPassLogin.text.toString()
        return senha
    }

}
