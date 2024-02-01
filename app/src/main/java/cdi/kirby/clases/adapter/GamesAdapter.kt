package cdi.kirby.clases.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import cdi.kirby.R
import cdi.kirby.clases.data.GameData
import cdi.kirby.clases.provider.GamesProvider
import cdi.kirby.clases.repository.KirbyRepository
import cdi.kirby.clases.viewholder.GameViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamesAdapter(repository: KirbyRepository) : Adapter<GameViewHolder>() {

    private val provider = GamesProvider(repository)
    private var gamesList: MutableList<GameData> = mutableListOf()
    private var requestingData = false

    init {
        LoadGamesData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = GameViewHolder(layoutInflater.inflate(R.layout.cell_games, parent, false))
        viewHolder.itemView.setOnClickListener {

        }
        return viewHolder
    }

    override fun getItemCount(): Int = gamesList.count()

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.SetupWithGame(gamesList[position], provider)
    }

    fun LoadGamesData(){

        if (requestingData)
        {
            return
        }

        requestingData = true

        CoroutineScope(Dispatchers.IO).launch {


            CoroutineScope(Dispatchers.Main).launch {


                requestingData = false
            }
        }
    }
}