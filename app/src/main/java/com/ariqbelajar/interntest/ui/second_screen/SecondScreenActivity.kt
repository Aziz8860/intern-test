package com.ariqbelajar.interntest.ui.second_screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ariqbelajar.interntest.databinding.ActivitySecondScreenBinding
import com.ariqbelajar.interntest.ui.third_screen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUsername()
        setupListener()
    }

    private fun setupUsername() {
        val userName = intent.getStringExtra(USER_NAME)
        binding.tvName.text = userName
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_THIRD_SCREEN && resultCode == Activity.RESULT_OK) {
            val updatedData = data?.getStringExtra(SELECTED_USER_NAME)

            // Use the updated data as needed
            binding.tvSelectedUserName.text = updatedData
        }
    }

    private fun setupListener() {
        binding.btnChooseAUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivityForResult(intent, REQUEST_THIRD_SCREEN)
        }
    }

    companion object {
        const val USER_NAME = "username"
        const val SELECTED_USER_NAME = "Selected User Name"
        const val REQUEST_THIRD_SCREEN = 1
    }
}