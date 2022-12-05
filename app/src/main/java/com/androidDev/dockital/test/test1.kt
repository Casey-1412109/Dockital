package com.androidDev.dockital.test

//import java.sql.Connection
//
//fun sign()
//{
//    val projectId = "c57ca95b47569778a828d19178114f4db188b89b763c899ba0be274e97267d96" // Get Project ID at https://cloud.walletconnect.com/
//    val relayUrl = "relay.walletconnect.com"
//    val serverUrl = "wss://$relayUrl?projectId=$projectId"
//    val connectionType = ConnectionType.AUTOMATIC
//    val appMetaData = Core.Model.AppMetaData(
//        name = "Dapp Name",
//        description = "Dapp Description",
//        url = "Dapp Url",
//        icons = /*list of icon url strings*/,
//        redirect = "kotlin-dapp-wc:/request" // Custom Redirect URI
//    )
//
//    CoreClient.initialize(relayServerUrl = serverUrl, connectionType = connectionType, application = this, metaData = appMetaData)
//
//    val init = Sign.Params.Init(coreClient = CoreClient)
//
//    SignClient.initalize(init) { error ->
//        // Error will be thrown if there's an isssue during initalization
//    }
//}