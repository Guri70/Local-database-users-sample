package com.app.localdatabase.authenication

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.localdatabase.authenication.adapter.UsersAdapter
import com.app.localdatabase.databinding.ActivityMainBinding
import com.app.localdatabase.listener.OnItemClickListener
import com.app.localdatabase.repository.AuthenticationRepository
import com.app.localdatabase.roomDatabase.UserModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UsersAdapter
    private lateinit var list: MutableList<UserModel>
    private lateinit var onOnItemClickListener: OnItemClickListener<UserModel>

    private var repository: AuthenticationRepository? = null

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        repository = AuthenticationRepository((applicationContext as Application?)!!)

        list = ArrayList()

        initListener()
    }

    private fun initListener() {
        onOnItemClickListener = object : OnItemClickListener<UserModel> {
            override fun onClickNotify(model: UserModel, pos: Int) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("User!")
                    setMessage("If you want to you user update or delete?")
                    setPositiveButton("Update") { _, _ ->
                        SignUpActivity.start(this@MainActivity, model)
                    }
                    setNegativeButton("Delete") { _, _ ->
                        AlertDialog.Builder(this@MainActivity).apply {
                            setTitle("Delete User!")
                            setMessage("If you want you user delete?")
                            setPositiveButton("Yes") { _, _ ->
                                list.removeAt(pos)
                                adapter.notifyItemRemoved(pos)
                                repository?.delete(model)
                            }

                            setNegativeButton("No") { _, _ ->
                            }
                        }.create().show()
                    }
                }.create().show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        repository!!.getAllData().observe(
            this,
            Observer<List<UserModel>?> { t -> setData(t!!) })
    }

    private fun setData(it: List<UserModel>) {
        list = it as MutableList<UserModel>
        adapter = UsersAdapter(list, onOnItemClickListener)
        binding!!.recyclerUsers.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, true
        )
        binding!!.recyclerUsers.adapter = adapter

        binding!!.btnAddUser.setOnClickListener {
            SignUpActivity.start(this, null)
        }
    }

}