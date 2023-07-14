package com.ariqbelajar.interntest.ui.first_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ariqbelajar.interntest.databinding.ActivityFirstScreenBinding
import com.ariqbelajar.interntest.ui.second_screen.SecondScreenActivity
import com.ariqbelajar.interntest.ui.second_screen.SecondScreenActivity.Companion.USER_NAME
import com.ariqbelajar.interntest.utils.checkPalindrome

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
    }

    private fun setupListener() {
        binding.btnCheck.setOnClickListener {
            val palindromeText = binding.edPalindrome.text.toString().trim()
            if (palindromeText.isEmpty()) {
                binding.edPalindrome.error = "Field cannot be empty"
                showToast("Failed to check palindrome")
            } else {
                val message =
                    if (checkPalindrome(palindromeText)) "isPalindrome" else "not palindrome"
                val alertDialog = AlertDialog.Builder(this)
                    .setMessage(message)
                    .create()
                alertDialog.show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            if (name.isEmpty()) {
                binding.edName.error = "Field cannot be empty"
                showToast("Failed to move to next activity")
            } else {
                val intent = Intent(this, SecondScreenActivity::class.java).apply {
                    putExtra(USER_NAME, binding.edName.text.toString())
                }
                startActivity(intent)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@FirstScreenActivity, message, Toast.LENGTH_SHORT).show()
    }

}