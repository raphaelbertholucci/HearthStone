package com.bertholucci.home.ui.card

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bertholucci.domain.helper.fold
import com.bertholucci.domain.model.Card
import com.bertholucci.home.databinding.FragmentCardDetailsBinding
import com.bertholucci.home.extensions.loadFromUrl
import com.bertholucci.home.extensions.navProvider
import com.bertholucci.home.extensions.onBackPressed
import com.bertholucci.home.extensions.setValueIfNotEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CardFragment : Fragment() {

    private val navController by navProvider()
    private val args: CardFragmentArgs by navArgs()

    private val viewModel: CardViewModel by viewModel {
        parametersOf(args.name)
    }

    private val binding by lazy {
        FragmentCardDetailsBinding.inflate(layoutInflater)
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
        viewModel.card.observe(viewLifecycleOwner) { response ->
            response.fold(
                error = ::handleError,
                loading = { if (it) display(loading = true) },
                success = ::handleSuccess
            )
        }
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener {
            activity.onBackPressed()
        }

        binding.error.btTryAgain.setOnClickListener {
            viewModel.getCardDetails()
        }
    }

    private fun display(
        content: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        binding.content.isVisible = content
        binding.loading.shimmer.isVisible = loading
        binding.error.root.isVisible = error
    }

    private fun handleError(throwable: Throwable) {
        Log.d("ERROR", throwable.message ?: "Error encountered on displaying the show!")
        display(error = true)
    }

    private fun handleSuccess(card: Card) {
        display(content = true)
        binding.apply {
            ivPoster.loadFromUrl(card.image)
            tvName.setValueIfNotEmpty(card.name)
            tvSet.setValueIfNotEmpty(card.cardSet)
            tvType.setValueIfNotEmpty(card.type)
            tvFaction.setValueIfNotEmpty(card.faction)
            tvRarity.setValueIfNotEmpty(card.rarity)
            tvAttack.setValueIfNotEmpty(card.attack)
            tvCost.setValueIfNotEmpty(card.cost)
            tvHealth.setValueIfNotEmpty(card.health)
            tvFlavorLabel.isVisible = card.flavor.isNotEmpty()
            tvFlavor.setValueIfNotEmpty(card.flavor)
            tvDescriptionLabel.isVisible = card.text.isNotEmpty()
            tvDescription.text = Html.fromHtml(card.text, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}
