package cdi.kirby.clases.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cdi.kirby.clases.data.GameData
import cdi.kirby.clases.provider.GamesProvider

class GameViewHolder(view : View, var gameData: GameData? = null) : ViewHolder(view) {



    fun SetupWithGame(gameData: GameData, provider: GamesProvider){

    }

}
