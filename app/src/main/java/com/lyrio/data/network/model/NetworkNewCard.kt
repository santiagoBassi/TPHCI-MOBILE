package com.lyrio.data.network.model

import com.lyrio.data.model.Card
import com.lyrio.data.model.CardType
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkNewCard(
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var cvv: String,
    var type: String,

)
