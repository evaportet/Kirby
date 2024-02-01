package cdi.kirby.fragments.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cdi.kirby.MyApp
import cdi.kirby.R
import cdi.kirby.clases.adapter.GamesAdapter
import cdi.kirby.clases.repository.KirbyApiRepository

class Games_screen : Fragment() {

    lateinit var fragmentView : View
    private val table by lazy { fragmentView.findViewById<RecyclerView>(R.id.games_table) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.games_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table.layoutManager = LinearLayoutManager(MyApp.get().context)

        val repository = KirbyApiRepository()

        table.adapter = GamesAdapter(repository)
    }
}