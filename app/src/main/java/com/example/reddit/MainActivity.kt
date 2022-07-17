package com.example.reddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.reddit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? = null
    private val mBinding get() = binding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)
            MAIN = this@MainActivity
            navController = Navigation.findNavController(MAIN,R.id.navHost)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}