package com.nevidimka655.lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.nevidimka655.lab5.databinding.ActivityMainBinding
import com.nevidimka655.lab5.views.GroupsListActivity

class MainActivity : AppCompatActivity() {

    object Keys {
        const val GROUP_NAME = "grpnum"
    }

    private val vm by viewModels<MainVM>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnShowGroups.setOnClickListener {
            startActivity(Intent(this, GroupsListActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        vm.runTimer()
    }

    override fun onStop() {
        super.onStop()
        vm.stopTimer()
    }
}