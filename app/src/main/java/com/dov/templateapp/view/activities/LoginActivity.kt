package com.dov.templateapp.view.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.dov.templateapp.R
import com.dov.templateapp.view.activities.BaseActivity
import com.dov.templateapp.view.fragments.LoginFragment

import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.detail_movie_fragment.*

class LoginActivity : BaseActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fragment)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            addRootFragment(LoginFragment())
        }
    }

}
