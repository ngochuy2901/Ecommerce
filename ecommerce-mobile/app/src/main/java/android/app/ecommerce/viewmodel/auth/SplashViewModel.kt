package android.app.ecommerce.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    fun start(onDone: () -> Unit) {
        viewModelScope.launch {

            val minDelay = async {
                delay(2000)
            }

            val initTasks = async {
                runInitTasks()
            }

            // Đợi CẢ HAI xong
            minDelay.await()
            initTasks.await()

            onDone()
        }
    }

    private suspend fun runInitTasks() {
        // ví dụ:
        checkLogin()
        fetchUserProfile()
        preloadData()
    }

    private suspend fun checkLogin() {
        delay(500) // giả lập
    }

    private suspend fun fetchUserProfile() {
        delay(1200)
    }

    private suspend fun preloadData() {
        delay(300)
    }
}
