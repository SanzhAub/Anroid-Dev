package com.example.aviatickets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.entity.Flight
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.service.FakeService


class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        adapter.setItems(FakeService.offerList)
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter
            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        adapter.setItems(sortByPrice(FakeService.offerList))
                    }
                    R.id.sort_by_duration -> {
                        adapter.setItems(sortByDuration(FakeService.offerList))
                    }
                }
            }
        }
    }

    private fun sortByPrice(offerList: List<Offer>): List<Offer> {
        return offerList.sortedBy { it.price }
    }

    private fun sortByDuration(offerList: List<Offer>): List<Offer> {
        return offerList.sortedBy { it.flight.duration }
    }
}