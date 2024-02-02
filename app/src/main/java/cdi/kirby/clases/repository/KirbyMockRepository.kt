package cdi.kirby.clases.repository

import cdi.kirby.clases.data.GameData

class KirbyMockRepository : KirbyRepository{

    companion object{

        private val kirbyGames = mutableListOf<GameData>(
            GameData("kirbySlideGame.png", "Kirby Slide", 2003, "Game Boy Advance", "Kirby Slide (called Kirby Puzzle on the e-Reader menu) is a simple sliding puzzle game based on the Kirby series for the e-Reader peripheral for the Game Boy Advance. It was released sometime in late 2003 in North America. As with all software released for the e-Reader, the media is a card to be swiped across the card reader"),
            GameData("kirbyHeardleGame.png", "Kirby Heardle", 2022, "Web Browser", "\"A clone of Heardle, and K-Pop Heardle but for Kirby songs.\\n\\nEach Kirby Heardle is randomly chosen from Kirby's Soundtracks Library (Owned by HAL Laboratory)\\n\\nMajority songs were chosen from mainline games, while the selective few are from sidegame titles."),
            GameData("yumeNoKirbyGame.png", "Yume No Kirby", 2022, "PC (Microsoft Windows)", "Yume no Kirby is an English Kirby/Yume Nikki fangame created by Ondaja in RPG Maker 2003. It was developed as an entry for Dream Diary Jam 6."),
            GameData("kirbyAirRideGame.png", "Kirby Air Ride", 2003, "Nintendo GameCube", "Kirby Air Ride is a 2003 racing game video game developed by HAL Laboratory and published by Nintendo for the Nintendo GameCube video game console. The game has the players and computer-controlled racers ride on Air Ride Machines. The game supports up to four players, and was the first GameCube title to support LAN play using broadband adapters and up to four GameCube systems. Players take control of Kirby or any of his multicolored counterparts to compete in races or other minigames. The game consists of three different game modes: Air Ride, Top Ride, and City Trial."),
            GameData("kirbyStarRideGame.png", "Kirby Star Ride", 2008, "Web Browser", "Kirby Star Ride is a Flash game made by 4Kids as a promotion for Kirby: Right Back at Ya!. The game was only up for 1 year before being delisted from the website. The game is currently lost, with 0 information about the game surviving online apart from it's existence.")
        )

    }
    override suspend fun GetGames(offset: Int, limit: Int): MutableList<GameData> {

        val games = kirbyGames

        if (games.size <= offset)
        {
            return mutableListOf()
        }

        if (games.size <= offset + limit)
        {
            return kirbyGames.subList(offset, kirbyGames.size - 1)
        }

        return kirbyGames.subList(offset, limit)
    }
}