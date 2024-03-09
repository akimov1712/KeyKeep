package ru.topbun.keyKeep.presentation

import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
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
            btnSearch.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
            btnSetMasterPassword.setOnClickListener {
                closeDrawerLayout()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetMasterPasswordDialog())
            }
            btnSetFingerPassword.setOnClickListener {
                closeDrawerLayout()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetFingerPasswordDialog())
            }
            btnAddPassword.setOnClickListener{
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddOrEditFragment())
            }
        }
    }

    private fun closeDrawerLayout(){
        binding.drawerLayout.closeDrawers()
    }
}