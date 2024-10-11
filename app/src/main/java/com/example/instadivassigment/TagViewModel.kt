import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instadivassigment.TagRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TagViewModel(private val repository: TagRepository) : ViewModel() {

    private val _allTags = MutableStateFlow<List<String>>(emptyList())
    val allTags: StateFlow<List<String>> = _allTags

    init {
        loadTags()
    }

    private fun loadTags() {
        viewModelScope.launch {
            _allTags.value = repository.fetchTags()
        }
    }
}
