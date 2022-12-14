package com.demo.walterlauk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.walterlauk.databinding.PartsSingleRowLayoutBinding
import com.demo.walterlauk.model.TrailerParts

class TrailerPartsAdapter(val mList : List<TrailerParts>, val mContext: Context):RecyclerView.Adapter<TrailerPartsAdapter.ViewHolder>() {

    inner class ViewHolder(val mBinding : PartsSingleRowLayoutBinding):RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bsBinding = PartsSingleRowLayoutBinding.inflate(LayoutInflater.from(mContext) , parent , false)
        return ViewHolder(bsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mList.get(position)

        holder.mBinding.partNameTv.text = data.category_name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}