package devandroid.ribolli.mvvmkotlin.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import devandroid.ribolli.mvvmkotlin.R
import devandroid.ribolli.mvvmkotlin.result.ResultActivity
import kotlinx.android.synthetic.main.activity_main.*

class FormActivity : AppCompatActivity() {

    //val = valor var = variavel

    private lateinit var viewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)

        setupInputChangeListener()
        setupButtonClickListener()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.onUserSentForm.observe(this, Observer{
            startActivity(ResultActivity.createIntent(this, it))
        })
    }

    private fun setupButtonClickListener() {
        button.setOnClickListener{
            viewModel.onUserRequestedToSendForm()
        }
    }

    private fun setupInputChangeListener() {
        formInput.addTextChangedListener {
            viewModel.onUserChangedInput(it.toString())
        }
    }
}