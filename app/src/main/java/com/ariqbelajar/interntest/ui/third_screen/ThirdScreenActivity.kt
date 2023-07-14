package com.ariqbelajar.interntest.ui.third_screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariqbelajar.interntest.adapter.LoadingStateAdapter
import com.ariqbelajar.interntest.adapter.UserListAdapter
import com.ariqbelajar.interntest.databinding.ActivityThirdScreenBinding
import com.ariqbelajar.interntest.network.DataItem
import com.ariqbelajar.interntest.ui.second_screen.SecondScreenActivity.Companion.SELECTED_USER_NAME

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory(this)
    }
    private val userAdapter: UserListAdapter by lazy {
        UserListAdapter(::onUserClick)
    }
    private var userNamePassed: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra(SELECTED_USER_NAME, userNamePassed)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()

        super.onBackPressed()
    }

    private fun onUserClick(data: DataItem) {
        val fullName = "${data.firstName} ${data.lastName}"
        userNamePassed = fullName
        showToast("Selected username changed to $fullName")
    }

    private fun getData() {

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = userAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                userAdapter.retry()
            }
        )

        userAdapter.addLoadStateListener { loadState ->
            showLoading(loadState.refresh is LoadState.Loading && userAdapter.itemCount <= 0)
        }

        viewModel.user.observe(this) {
            userAdapter.submitData(lifecycle, it)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this@ThirdScreenActivity, message, Toast.LENGTH_SHORT).show()
    }
}