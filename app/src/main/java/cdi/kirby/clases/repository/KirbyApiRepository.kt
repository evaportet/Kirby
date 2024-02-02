package cdi.kirby.clases.repository

import cdi.kirby.clases.data.GameData
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class KirbyApiRepository : KirbyRepository {

    /*API*/
    val clientID = "9n7njgxyixbs2e7dvdyg9yi7g9109t"
    val clientSecret = "72t47sdul3uyfah1d2rzt95crwfgs7"

    /*Array de juegos de Kirby con nombre, relese date, plataforma y descripción*/
    val client = OkHttpClient()
    val mediaType = "application/json".toMediaType()
    val body = "fields name, first_release_date, platforms, summary; search \"Kirby\";".toRequestBody(mediaType)
    val request = Request.Builder()
        .url("https://api.igdb.com/v4/games")
        .post(body)
        .addHeader("Client-ID", clientID)
        .addHeader("Authorization", "Bearer 6myxjepzvhmckdf4b715vpuyfgma4w")
        .addHeader("Content-Type", "application/json")
        .addHeader("Cookie", "__cf_bm=.c4J2fpxEXR6BVLqN.8B.dNcJGbLz4mooir.iW1i_Mo-1706802023-1-AcJjmyZ+XcMBdh3bPj5CfRgskUclrvt+h3bE15Pv//awlh5KXoFjSzeAzfudRPXXTjGvG8AzEM237KL/zLmTRXQ=")
        .build()

    /*Traduce de ID a nombre de la plataforma*/
    val clientPlatform = OkHttpClient()
    val mediaTypePlatform = "application/json".toMediaType()
    val bodyPlatform = "fields name,abbreviation; where id = 24;".toRequestBody(mediaType) /*la ID es la plataforma*/
    val requestPlatform = Request.Builder()
        .url("https://api.igdb.com/v4/platforms")
        .post(bodyPlatform)
        .addHeader("Client-ID", clientID)
        .addHeader("Authorization", "Bearer 6myxjepzvhmckdf4b715vpuyfgma4w")
        .addHeader("Content-Type", "application/json")
        .addHeader("Cookie", "__cf_bm=AKKJyxdoawa8In63GZmItasX_J.UH8pmrOrs1lowwEg-1706803313-1-AanizTHv8YL08saTC++S3BY2lSpxa2IoaVeZ6+8xLrd1TDlSI8kakR4EXBOX21kmMLI6chk7Y8dIqy3PX/Elipg=")
        .build()



    override suspend fun GetGames(offset: Int, limit: Int): MutableList<GameData> {

        /*client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    // Procesar la respuesta aquí (puede ser necesario analizar el JSON)
                    // responseBody contiene la respuesta en formato JSON o XML
                } else {
                    // Manejar el caso en el que la solicitud no fue exitosa
                    println("Error en la solicitud. Código de estado: ${response.code()}")
                    MyFirebase.crashlytics.logSimpleError("Call API Error"){
                        key("Code", "responde.code")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Manejar fallos de red o excepciones aquí
                e.printStackTrace()
            }
        })*/

        return mutableListOf()
    }
}