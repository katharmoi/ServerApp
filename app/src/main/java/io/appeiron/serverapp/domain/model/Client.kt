package io.appeiron.serverapp.domain.model

data class Client(
    val clientPackageName: String,
    val clientProcessId:String,
    var clientData:String
)
