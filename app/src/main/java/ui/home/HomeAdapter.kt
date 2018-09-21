package ui.home

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.companyname.boilerplate.R
import com.companyname.boilerplate.databinding.ItemHomeFooSummaryBinding

/** Adapter for [HomeFragment] content */
class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val adapterItems = ArrayList<AdapterData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_FOO_SUMMARY -> createFooSummaryViewHolder(inflater, parent)
            else -> throw AssertionError("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FooSummaryViewHolder -> bind(holder, adapterItems[position].any as FooSummaryVm)
        }
    }

    override fun getItemCount() = adapterItems.size

    /** Returns a new [FooSummaryViewHolder] */
    private fun createFooSummaryViewHolder(inflater: LayoutInflater,
                                              parent: ViewGroup): FooSummaryViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeFooSummaryBinding>(inflater,
                R.layout.item_home_foo_summary, parent, false)
        return FooSummaryViewHolder(binding)
    }

    /** Binds the given data to a [FooSummaryViewHolder] */
    private fun bind(summaryViewHolder: FooSummaryViewHolder, data: FooSummaryVm) {
        summaryViewHolder.binding.vm = data
    }

    fun add(data: List<FooSummaryVm>) {
        val vms = data.map { AdapterData(it, VIEW_TYPE_FOO_SUMMARY) }
        adapterItems.addAll(vms)
    }

    fun add(data: FooSummaryVm) {
        val vms = AdapterData(data, VIEW_TYPE_FOO_SUMMARY)
        adapterItems.add(vms)
    }

    fun clear() {
        adapterItems.clear()
    }
}

private const val VIEW_TYPE_FOO_SUMMARY = 0

/** [RecyclerView.ViewHolder] to hold a header */
class FooSummaryViewHolder(
        val binding: ItemHomeFooSummaryBinding
) : RecyclerView.ViewHolder(binding.root)

class AdapterData(val any: Any, val type: Int)