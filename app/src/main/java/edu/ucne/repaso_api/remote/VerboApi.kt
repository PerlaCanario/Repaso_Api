package edu.ucne.repaso_api.remote

import edu.ucne.repaso_api.remote.dto.VerboDto
import retrofit2.Response
import retrofit2.http.GET

interface VerboApi {
    @GET("/verbos")
    suspend fun GetVerbos(): Response<List<VerboDto>>

}