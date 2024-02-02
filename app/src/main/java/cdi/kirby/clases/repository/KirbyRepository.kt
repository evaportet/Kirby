package cdi.kirby.clases.repository

import cdi.kirby.clases.data.GameData

interface KirbyRepository {

    suspend fun GetGames(offset: Int, limit: Int) : MutableList<GameData>
}