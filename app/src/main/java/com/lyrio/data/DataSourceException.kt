package com.lyrio.data

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message) {
    companion object {
        const val DATA_ERROR = 1
        const val UNAUTHORIZED_ERROR_CODE = 2
        const val NOT_FOUND_ERROR_CODE = 3
        const val INTERNAL_SERVER_ERROR_CODE = 4
        const val UNEXPECTED_ERROR_CODE = 98
        const val CONNECTION_ERROR_CODE = 99
    }
}