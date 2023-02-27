package com.bertholucci.home.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Card
import com.bertholucci.home.databinding.FragmentHomeBinding
import com.bertholucci.home.extensions.navProvider
import com.bertholucci.home.extensions.navigateWithAnimation
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val navController: NavController by navProvider()
    private val viewModel: HomeViewModel by viewModel()

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        addListeners()
    }

    private fun addObservers() {
        viewModel.cards.observe(viewLifecycleOwner) { response ->
            binding.swipe.isRefreshing = false
            response.fold(
                loading = { loading -> if (loading) display(loading = true) },
                error = ::handleError,
                success = ::handleSuccess
            )
        }
    }

    private fun addListeners() {
        binding.swipe.setOnRefreshListener {
            viewModel.getCards()
        }

        binding.error.btTryAgain.setOnClickListener {
            viewModel.getCards()
        }
    }

    private fun handleSuccess(cards: List<Card>) {
        binding.rvCards.adapter = HomeAdapter(
            cards = cards,
            onClick = { card ->
                navController.navigateWithAnimation(
                    HomeFragmentDirections.toCardDetails(card.id)
                )
            }
        )
        display(content = true)
    }

    private fun handleError(throwable: Throwable) {
        Log.d("ERROR", throwable.message ?: "Error encountered on displaying the cards!")
        display(error = true)
    }

    private fun display(
        content: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        binding.rvCards.isVisible = content
        binding.loading.shimmer.isVisible = loading
        binding.error.root.isVisible = error
    }
}
