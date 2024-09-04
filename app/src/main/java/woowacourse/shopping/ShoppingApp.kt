package woowacourse.shopping

import android.app.Application
import woowacourse.shopping.data.AppContainer
import woowacourse.shopping.data.DefaultAppContainer

class ShoppingApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
