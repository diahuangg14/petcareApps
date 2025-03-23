package com.example.petcare.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.petcare.database.AppDatabase
import com.example.petcare.databinding.ActivityScheduleBinding
import com.example.petcare.model.ScheduleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class AddScheduleActivity : AppCompatActivity() {
    /**
     * memakain fitur binding
     */
    private lateinit var binding: ActivityScheduleBinding
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
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * menambah fungsi setOnClickListener untuk memberikan action pada saat melakukan klik
         */
        binding.etDate.setOnClickListener {
            /**
             * membuat popup date picker
             */
            val cal = Calendar.getInstance()
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(
                this@AddScheduleActivity,
                { _: DatePicker?, year1: Int, month1: Int, dayOfMonth: Int ->
                    // Format tanggal ke dd/MM/yyyy
                    val calendar = Calendar.getInstance()
                    calendar.set(year1,month1,dayOfMonth)

                    val format = SimpleDateFormat("dd/MM/yyyy")
                    val strDate: String = format.format(calendar.time)

                    binding.etDate.setText(strDate)
                }, year, month, day
            )
            datePickerDialog.show()
        }
        binding.btnSave.setOnClickListener {
            saveSchedule()
        }
    }

    private fun saveSchedule() {
        /**
         * mendapatkan value dari edittext
         */
        val title = binding.etTitle.text.toString()
        val date = binding.etDate.text.toString()
        val desc = binding.etDescription.text.toString()

        /**
         * pengecekan jika ada variabel yang kosong maka akan memunculkan pesan
         */
        if (title.isEmpty() || date.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
        } else {
            /**
             * coroutine scope digunakan untuk menjalankan suspended function
             */
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    /**
                     * melakukan operasi database yakni insert schedule
                     */
                    db.appDao().insertSchedule(
                        ScheduleModel(
                            title = title,
                            date = date,
                            desc = desc
                        )
                    )
                    /**
                     * memunculkan pesan berhasil jika sukses menyimpan data
                     */
                    runOnUiThread {
                        Toast.makeText(
                            this@AddScheduleActivity,
                            "Berhasil menambah",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    finish()
                } catch (e: Exception) {
                    /**
                     * memunculkan pesan error jika gagal menyimpan data
                     */
                    runOnUiThread {
                        Toast.makeText(
                            this@AddScheduleActivity,
                            "Error : ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}