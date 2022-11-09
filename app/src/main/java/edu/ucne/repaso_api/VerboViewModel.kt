package edu.ucne.repaso_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.repaso_api.remote.dto.VerboDto
import edu.ucne.repaso_api.repository.VerboRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class VerbosListUiState(
    val verbos : List<VerboDto> = emptyList()
)

@HiltViewModel
class VerboViewModel @Inject constructor(
    private val api: VerboRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(VerbosListUiState())
    val uiState : StateFlow<VerbosListUiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(verbos = api.getVerbos())
                }catch (ioe: IOException){
                    it.copy(verbos = emptyList())
                }
            }
        }
    }

}
