package woowacourse.shopping.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzang.di.DIContainer
import com.zzang.di.annotation.Inject
import com.zzang.di.annotation.QualifierType
import kotlinx.coroutines.launch
import woowacourse.shopping.domain.CartRepository
import woowacourse.shopping.domain.ProductRepository
import woowacourse.shopping.model.Product

class MainViewModel : ViewModel() {
    @Inject(qualifier = QualifierType.IN_MEMORY)
    lateinit var productRepository: ProductRepository

    @Inject(qualifier = QualifierType.DATABASE)
    lateinit var cartRepository: CartRepository

    private var _products: MutableLiveData<List<Product>> = MutableLiveData(emptyList())
    val products: LiveData<List<Product>> get() = _products

    private val _onProductAdded: MutableLiveData<Boolean> = MutableLiveData(false)
    val onProductAdded: LiveData<Boolean> get() = _onProductAdded

    fun addCartProduct(product: Product) {
        viewModelScope.launch {
            cartRepository.addCartProduct(product)
            _onProductAdded.value = true
        }
    }

    fun getAllProducts() {
        _products.value = productRepository.getAllProducts()
    }

    override fun onCleared() {
        super.onCleared()
        DIContainer.clearViewModelScopedInstances(this)
    }
}
