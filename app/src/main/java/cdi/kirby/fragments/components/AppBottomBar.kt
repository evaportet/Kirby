package cdi.kirby.fragments.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdi.kirby.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppBottomBar: Fragment() {

    companion object {
        private lateinit var Instance: AppBottomBar
        fun get() = Instance
    }

    lateinit var bottomBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.component_bottombar, container, false)
        bottomBar = view.findViewById(R.id.AppNavigationBottomBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomBar.setOnItemSelectedListener { menuItem ->

            if(menuItem.itemId != bottomBar.selectedItemId)
            {
                when(menuItem.itemId) {
                    R.id.news -> {
                        when(bottomBar.selectedItemId){
                            R.id.games -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_games_news)
                            }
                            R.id.community -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_community_news)
                            }
                            R.id.configuration -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_configuration_news)
                            }
                        }
                    }
                    R.id.games -> {
                        when(bottomBar.selectedItemId){
                            R.id.news -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_news_games)
                            }
                            R.id.community -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_community_games)
                            }
                            R.id.configuration -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_configuration_games)
                            }
                        }
                    }
                    R.id.community -> {
                        when(bottomBar.selectedItemId){
                            R.id.news -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_news_community)
                            }
                            R.id.games -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_games_community)
                            }
                            R.id.configuration -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_configuration_community)
                            }
                        }
                    }
                    R.id.configuration -> {
                        when(bottomBar.selectedItemId){
                            R.id.news -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_news_configuration)
                            }
                            R.id.games -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_games_configuration)
                            }
                            R.id.community -> {
                                AppNavHost.get().navHost.navigate(R.id.transition_community_cofiguration)
                            }
                        }
                    }
                }
            }

            true
        }

        bottomBar.selectedItemId = bottomBar.menu.getItem(0).itemId
    }
}