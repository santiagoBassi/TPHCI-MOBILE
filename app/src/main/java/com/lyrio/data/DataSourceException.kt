package com.lyrio.data

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)