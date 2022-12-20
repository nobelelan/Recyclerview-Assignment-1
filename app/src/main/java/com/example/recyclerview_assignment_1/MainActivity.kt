package com.example.recyclerview_assignment_1

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.recyclerview_assignment_1.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userViewModel: UserViewModel
    private val mainAdapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao = UserDatabase.getDatabase(applicationContext).userDao()
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userDao))[UserViewModel::class.java]

        val recyclerView = binding.mainRecyclerView
        recyclerView.adapter = mainAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch{
                try {
                    settingUpData()
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.txtNoInternet.visibility = View.INVISIBLE
                    binding.mainRecyclerView.visibility = View.VISIBLE
                }catch (exception: Exception){
                    binding.txtNoInternet.visibility = View.VISIBLE
                    binding.mainRecyclerView.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@MainActivity, "User data fetching failed!", Toast.LENGTH_SHORT).show()
                }
        }

        userViewModel.getAllUser.observe(this, Observer {
            mainAdapter.setData(it)
        })

    }

    private suspend fun getBitMap(url: String): Bitmap{
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data(url)
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }


    suspend fun settingUpData() = coroutineScope{
        binding.progressBar.visibility = View.VISIBLE

        val testUser1 = User("Rakib Ahmed", getBitMap(getString(R.string.user_1_image_url)),
            "+8801745869356", "Shashaiya", "Shashaiya",
            "Cumilla", "Chattagram","Bangladesh")
        val testUser2 = User("Md Saiful Islam", getBitMap(getString(R.string.user_2_image_url)),
            "+8801745866598", "Chalakchar", "Chalakchar",
            "Narshingdi", "Dhaka","Bangladesh")
        val testUser3 = User("Mahtabur Rahman", getBitMap(getString(R.string.user_3_image_url)),
            "+8801745861254", "Bhuiyan Bari", "Rahimanagar",
            "Chandpur", "Chattagram","Bangladesh")

        val testUserList = listOf(testUser1, testUser2, testUser3)

        val userList = arrayListOf<User>()

        for (i in 1..20){
            testUserList.forEach {
                userList.add(it)
            }
        }
        mainAdapter.setData(userList)
    }
}