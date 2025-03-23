package com.example.petcare.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.petcare.database.AppDatabase
import com.example.petcare.databinding.ActivityRegisterBinding
import com.example.petcare.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    /**
     * memakain fitur binding
     */
    private lateinit var binding: ActivityRegisterBinding
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
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * menambah fungsi setOnClickListener untuk memberikan action pada saat melakukan klik
         */
        binding.button3.setOnClickListener {
            register()
        }

        /**
         * berpindah ke halaman login
         */
        binding.moveToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun register() {
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
                    db.appDao().insertUser(UserModel(email, password))
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Error ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


    }
}