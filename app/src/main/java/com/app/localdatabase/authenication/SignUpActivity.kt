package com.app.localdatabase.authenication

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.localdatabase.databinding.ActivitySignUpBinding
import com.app.localdatabase.repository.AuthenticationRepository
import com.app.localdatabase.roomDatabase.UserModel

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null

    private var repository: AuthenticationRepository? = null

    private var model: UserModel? = null

    companion object {
        fun start(context: Context, model: UserModel?) {
            val intent = Intent(context, SignUpActivity::class.java)
            intent.putExtra("userModel", model)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        model = intent.getSerializableExtra("userModel") as UserModel?

        repository = AuthenticationRepository((applicationContext as Application?)!!)

        initListener()

        setUIData();

    }

    private fun setUIData() {
        binding!!.apply {
            if (model != null) {
                etPassword.visibility = View.GONE
                etName.setText(model?.name)
                etEmail.setText(model?.email)
                etPhone.setText(model?.phone)
                etGender.setText(model?.gender)
                etDob.setText(model?.dob)
                btnAddUser.text = "Update User"
            }else{
                btnAddUser.text = "Add User"
            }
        }
    }

    private fun initListener() {
        binding!!.apply {

            btnAddUser.setOnClickListener {
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val phone = etPhone.text.toString().trim()
                val gender = etGender.text.toString().trim()
                val dob = etDob.text.toString().trim()
                val password = etPassword.text.toString().trim()

                if (name.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter name", Toast.LENGTH_SHORT)
                        .show()
                    etName.requestFocus()
                    return@setOnClickListener
                }

                if (email.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter an email", Toast.LENGTH_SHORT)
                        .show()
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (phone.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter phone", Toast.LENGTH_SHORT)
                        .show()
                    etPhone.requestFocus()
                    return@setOnClickListener
                }

                if (gender.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter gender", Toast.LENGTH_SHORT)
                        .show()
                    etGender.requestFocus()
                    return@setOnClickListener
                }

                if (dob.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter DOB", Toast.LENGTH_SHORT)
                        .show()
                    etDob.requestFocus()
                    return@setOnClickListener
                }

                if (model == null) {
                    if (password.isEmpty()) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Please enter password",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        etPassword.requestFocus()
                        return@setOnClickListener
                    }
                }

                var userModel: UserModel? = null

                if (model != null)
                    userModel = model
                else
                    userModel = UserModel()


                userModel?.name = name
                userModel?.email = email
                userModel?.phone = phone
                userModel?.gender = gender
                userModel?.dob = dob
                userModel?.password = password
//                authenticationViewModel?.insert(userModel)

                if (model != null)
                    repository?.update(userModel)
                else
                    repository?.insert(userModel)

                Toast.makeText(
                    this@SignUpActivity,
                    "User Registered Successfully",
                    Toast.LENGTH_LONG
                ).show()

                finish()

            }

        }
    }
}