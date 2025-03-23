package com.example.petcare.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.petcare.database.AppDatabase
import com.example.petcare.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    /**
     * memakain fitur binding
     */
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * inisialisasi room database
         */
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "petDatabase")
            .allowMainThreadQueries().build()

        /**
         * inisialisasi binding
         */
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         * menambah fungsi setOnClickListener untuk memberikan action pada saat melakukan klik
         */
        binding.moveToRegister.setOnClickListener {
            /**
             * berpindah ke halaman register
             */
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        /**
         * menambah fungsi setOnClickListener untuk memberikan action pada saat melakukan klik
         */
        binding.button3.setOnClickListener {
            login()
        }

    }

    private fun login() {
        /**
         * mendapatkan value dari edittext
         */
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        /**
         * pengecekan jika ada variabel yang kosong maka akan memunculkan pesan
         */
        if (email.isEmpty()) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            /**
             * coroutine scope digunakan untuk menjalankan suspended function
             */
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    /**
                     * melakukan operasi database yakni get user by email
                     */
                    val user = db.appDao().getUserByEmail(email)
                    Log.d("LOGIN TAG", "login: ${user.email}")

                    /**
                     * pencocokan jika usernya null maka memunculkan pesan email not found
                     */
                    if (user == null) {
                        runOnUiThread {
                            Toast.makeText(
                                this@LoginActivity,
                                "Email not found",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        /**
                         * pencocokan jika email dan password cocok maka berpindah ke Main activity (home)
                         */
                    } else if (user.email == email && user.password == password) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        /**
                         * selain kondisi diatas maka akan memunculkan pesan
                         */
                    } else {
                        runOnUiThread {
                            Toast.makeText(
                                this@LoginActivity,
                                "Please check your email and password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    /**
                     * jika terdeteksi exception maka akan menampilkan pesan
                     */
                    runOnUiThread {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error : ${e.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }


    }
}