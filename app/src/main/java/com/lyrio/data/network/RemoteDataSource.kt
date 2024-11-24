package com.lyrio.data.network

import android.util.Log
import com.lyrio.data.DataSourceException
import com.lyrio.data.network.model.NetworkError
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.Response

abstract class RemoteDataSource {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun <T: Any> handleApiResponse(
        execute: suspend () -> Response<T>
    ): T {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return body
            }
            response.errorBody()?.let {
                response.code()
                val error = json.decodeFromString<NetworkError>(it.string())
                throwDataSourceException(response.code(), error.message)
            }
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Missing error")
        } catch (e: DataSourceException) {
            throw e
        } catch (e: IOException) {
            throw DataSourceException(CONNECTION_ERROR_CODE, "Connection error")
        } catch (e: Exception) {
             Log.e(TAG, "Unexpected error handling API response", e)
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Unexpected error")
        }
    }

    private fun throwDataSourceException(statusCode: Int, message: String) : DataSourceException {
        when (statusCode) {
            400 -> throw DataSourceException(DATA_ERROR, message)
            401 -> throw DataSourceException(UNAUTHORIZED_ERROR_CODE, message)
            404 -> throw DataSourceException(NOT_FOUND_ERROR_CODE, message)
            500 -> throw DataSourceException(INTERNAL_SERVER_ERROR_CODE, message)
            else -> throw DataSourceException(UNEXPECTED_ERROR_CODE, message)
        }
    }

    companion object {
        const val TAG = "Data Layer"

        const val DATA_ERROR = 1
        const val UNAUTHORIZED_ERROR_CODE = 2
        const val NOT_FOUND_ERROR_CODE = 3
        const val INTERNAL_SERVER_ERROR_CODE = 4
        const val UNEXPECTED_ERROR_CODE = 98
        const val CONNECTION_ERROR_CODE = 99
    }

    }