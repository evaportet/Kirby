package cdi.kirby.clases.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import cdi.kirby.R
import cdi.kirby.SharedPreferencesManager
import cdi.kirby.clases.data.GameData
import cdi.kirby.clases.provider.GamesProvider
import cdi.kirby.clases.repository.KirbyRepository
import cdi.kirby.clases.viewholder.GameViewHolder
import cdi.kirby.fragments.components.AppNavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamesAdapter(repository: KirbyRepository) : Adapter<GameViewHolder>() {

    private val provider = GamesProvider(repository)
    private var gameList: MutableList<GameData> = mutableListOf()
    private var requestingData = false

    init {
        LoadGamesData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = GameViewHolder(layoutInflater.inflate(R.layout.cell_games, parent, false))
        viewHolder.itemView.setOnClickListener {
            val sharedPreferences = SharedPreferencesManager
            sharedPreferences.gameData = viewHolder.gameData!!
            sharedPreferences.gamesDescription = true
            AppNavHost.get().navHost.navigate(R.id.transition_games_description)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = gameList.count()

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.SetupWithGame(gameList[position])
    }

    fun LoadGamesData(){

        if (requestingData)
        {
            return
        }

        requestingData = true

        CoroutineScope(Dispatchers.IO).launch {

            val gamesList = provider.GetGames(0 ,5)

            CoroutineScope(Dispatchers.Main).launch {

                for (game in gamesList)
                {
                    gameList.add(game)
                }

                notifyDataSetChanged()

                requestingData = false
            }
        }
    }
}