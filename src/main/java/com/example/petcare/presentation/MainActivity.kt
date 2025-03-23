package com.example.petcare.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.petcare.database.AppDatabase
import com.example.petcare.databinding.ActivityHomeBinding
import com.example.petcare.model.ScheduleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    /**
     * memakain fitur binding
     */
    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: AppDatabase

    private lateinit var upcomingAdapter: ScheduleAdapter
    private lateinit var todayAdapter : ScheduleAdapter

    private var upcomingSchedule : ArrayList<ScheduleModel> = arrayListOf()
    private var todaySchedule : ArrayList<ScheduleModel> = arrayListOf()

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
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * inisialisasi recycler view adapter pada upcoming schedule
         */
        upcomingAdapter = ScheduleAdapter(upcomingSchedule)
        binding.rvUpcomingSchedule.adapter = upcomingAdapter
        binding.rvUpcomingSchedule.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)


        /**
         * inisialisasi recycler view adapter pada today schedule
         */
        todayAdapter = ScheduleAdapter(todaySchedule)
        binding.rvTodaySchedule.adapter = todayAdapter
        binding.rvTodaySchedule.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)


        /**
         * menambah action berpindah ke halaman add schedule
         */
        binding.button.setOnClickListener {
            val intent = Intent(this, AddScheduleActivity::class.java)
            startActivity(intent)
        }

        /**
         * menambah action ketika recycler view icon hapus di klik
         */
        upcomingAdapter.setOnDeleteClick(object : RVClickListener {
            override fun onDeleteClick(data: ScheduleModel) {
                deleteSchedule(data)
            }
        })

        /**
         * sama aja cuma ini di adapternya today
         */
        todayAdapter.setOnDeleteClick(object :RVClickListener{
            override fun onDeleteClick(data: ScheduleModel) {
                deleteSchedule(data)
            }

        })

    }

    /**
     * membuat fungsi delete
     */
    private fun deleteSchedule(data :ScheduleModel){

        /**
         * coroutine scope digunakan untuk menjalankan suspended function
         */
        CoroutineScope(Dispatchers.IO).launch{


            /**
             * melakukan operasi database yakni delete schedule
             */
            db.appDao().deleteSchedule(data)


            getAllSchedule()
        }
    }

    /**
     * membuat fungsi get all schedule
     */
    private fun getAllSchedule() {
        /**
         * coroutine scope digunakan untuk menjalankan suspended function
         */
        CoroutineScope(Dispatchers.IO).launch {
            /**
             * menghapus list sebelum di isi
             */
            upcomingSchedule.clear()
            todaySchedule.clear()

            /**
             * melakukan operasi database yakni get all schedule
             */
            val schedule = db.appDao().getAllSchedule()

            /**
             * mendapatkan tanggal hari ini untuk komparasi
             */
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val localDate  = LocalDate.now()
            val formated = localDate.format(formatter)
            val today = LocalDate.parse(formated, formatter)

            /**
             * foreach untuk looping data schedule, sama seperti for(...)
             * jika tanggal is after today == true maka akan masuk ke upcoming
             * jika tanggal is equal today == true maka akan masuk ke upcoming
             * selain itu data tidak diapa apakan :)
             */
            schedule.forEach { data ->
                if (LocalDate.parse(data.date, formatter).isAfter(today)){
                    upcomingSchedule.add(data)
                }else if (LocalDate.parse(data.date, formatter).isEqual(today)){
                    todaySchedule.add(data)
                }else{
                    //tidak melakukan apapun
                }
            }

            /**
             * untuk refresh adapter ketika ada perubahan data
             */
            runOnUiThread {
                upcomingAdapter.notifyDataSetChanged()
                todayAdapter.notifyDataSetChanged()
            }

        }
    }

    /**
     * mendapatkan data ulang setelah pindah dari halaman yang lain
     */
    override fun onResume() {
        super.onResume()
        getAllSchedule()
    }
}