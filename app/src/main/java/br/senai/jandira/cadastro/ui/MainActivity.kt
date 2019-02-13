package br.senai.jandira.cadastro.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import br.senai.jandira.cadastro.*
import br.senai.jandira.cadastro.model.ApiResult
import br.senai.jandira.cadastro.model.Usuario
import br.senai.jandira.cadastro.viewmodel.CadastroViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    // Pegando a class
    val viewModel by lazy {
        ViewModelProviders.of(this).get(CadastroViewModel::class.java)
    }

    private var errorSnack: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            val nome = txtNome.text.toString()
            val email = txtEmail.text.toString()
            val senha = txtPass.text.toString()

            if(validarFormulario(nome, email, senha)){
                viewModel.cadastrarUsuario(getUsuario())
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

    fun validarFormulario(nome:String, email:String, senha:String) : Boolean{

        var retorno = true
        if(!minimoCaracter(nome, 3)){
            txtNome.error = getString(R.string.sMinimoCaracterNome)
            retorno = false
        }
        if(!confirmEmail(email)){
            txtEmail.error = getString(R.string.sEmailArroba)
            retorno = false
        }
        if(!minimoCaracter(senha, 4)){
            txtPass.error = getString(R.string.sSenhaComQuatroCaracter)
            retorno = false
        }
        if(!textContemNumero(senha)){
            txtPass.error = getString(R.string.sSenhaComNumeros)
            retorno = false
        }
        if(sequenciaNumericaSucesso(senha)){
            txtPass.error = getString(R.string.sSenhaComSequencia)
            retorno = false
        }

        return retorno
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

    fun updateError(error:Boolean?){
        error?.let{
            if(error){
                // Colocando um SnackBAr
                errorSnack = Snackbar.make(rootView, "Você está conectado?", Snackbar.LENGTH_INDEFINITE).apply {
                    setAction("Reconectar", object: View.OnClickListener{
                        override fun onClick(v: View?) {
                            viewModel.cadastrarUsuario(getUsuario())
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

    fun updateSucesso(result:ApiResult?){
        result?.let {

            val titulo = if(it.sucesso) "Sucesso" else "Erro"
            alert (it.mensagem, titulo){
                okButton {
                    if(result.sucesso) {
                        startActivity<LoginActivity>()
                        finish()
                    }
                }
            }.show()
        }
    }

    fun getUsuario() : Usuario{
        val nome = txtNome.text.toString()
        val email = txtEmail.text.toString()
        val senha = txtPass.text.toString()

        return Usuario(nome, email, senha)
    }
}
