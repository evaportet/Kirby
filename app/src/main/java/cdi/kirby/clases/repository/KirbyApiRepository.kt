package cdi.kirby.clases.repository

import cdi.kirby.clases.data.GameData

class KirbyApiRepository : KirbyRepository {
    override suspend fun GetGames(offset: Int, limit: Int): MutableList<GameData> {
        TODO("Not yet implemented")
    }
}