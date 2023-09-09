package ui.cat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import domain.AppRepository
import domain.Cat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatListScreenModel : ScreenModel {
    private val repository: AppRepository = AppRepository()

    val state: StateFlow<State> get() = _state
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Initial)

    init {
        fetch()
    }

    fun fetch() {
        coroutineScope.launch {
            _state.update { State.Loading }
            repository.fetchCats()
                .fold(
                    onSuccess = { response ->
                        _state.update { State.OnReady(response) }
                    },
                    onFailure = { throwable ->
                        _state.update { State.Error(throwable) }
                    }
                )
        }
    }

    sealed class State {
        object Initial : State()
        object Loading : State()
        data class OnReady(val cats: List<Cat>) : State()
        data class Error(val reason: Throwable) : State()
    }
}