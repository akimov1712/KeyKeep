package ru.topbun.keyKeep.presentation

import androidx.core.view.GravityCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.topbun.keyKeep.databinding.FragmentHomeBinding
import ru.topbun.keyKeep.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding) {
            btnMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }
}