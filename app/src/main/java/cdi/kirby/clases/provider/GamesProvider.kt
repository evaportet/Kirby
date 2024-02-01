package cdi.kirby.clases.provider

import cdi.kirby.clases.data.GameData
import cdi.kirby.clases.repository.KirbyRepository

class GamesProvider(val repository: KirbyRepository) {

    suspend fun GetGames(offset: Int, limit: Int): MutableList<GameData> {
        return repository.GetGames(offset, limit)
    }

}