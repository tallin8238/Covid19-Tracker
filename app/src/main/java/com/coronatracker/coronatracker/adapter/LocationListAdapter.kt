package com.coronatracker.coronatracker.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coronatracker.coronatracker.activity.HomeActivity
import com.coronatracker.coronatracker.databinding.HomeLayoutItemBinding
import com.coronatracker.coronatracker.model.CheckInDetails
import com.coronatracker.coronatracker.utils.CommonFunction
import com.coronatracker.coronatracker.utils.Constants

class LocationListAdapter(
    private var mContext: HomeActivity,
    private var checkInDetails: ArrayList<CheckInDetails?>
) :
    RecyclerView.Adapter<LocationListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationListAdapter.UserViewHolder {
        val mBinding = HomeLayoutItemBinding.inflate(mContext.layoutInflater, parent, false)
        return UserViewHolder(mBinding)

    }


    override fun getItemCount(): Int {
        return checkInDetails.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(checkInDetails[holder.adapterPosition])

    }

    var onItemClick: ((pos: Int, view: View) -> Unit)? = null

    inner class UserViewHolder(var mBinding: HomeLayoutItemBinding) :
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {

        fun bindData(checkInDetails: CheckInDetails?) {
            mBinding.checkIn = checkInDetails
            mBinding.textViewTime.text = CommonFunction.getDateTimeFromMilliseconds(
                checkInDetails!!.getCheckInTime().toLong(),
                Constants.timeFormat
            ).plus(" - ").plus(
                CommonFunction.getDateTimeFromMilliseconds(
                    checkInDetails.getCheckOutTime().toLong(),
                    Constants.timeFormat
                )
            )
            mBinding.executePendingBindings()
            itemView.setOnClickListener(this)
        }

        override fun onClick(v1: View?) {
            if (v1 != null) {
                onItemClick?.invoke(adapterPosition, v1)
            }
        }
    }
}
