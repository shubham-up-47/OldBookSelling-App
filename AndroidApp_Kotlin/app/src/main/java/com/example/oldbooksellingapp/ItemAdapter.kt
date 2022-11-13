package com.example.oldbooksellingapp

import android.app.Activity
import android.app.FragmentManager
import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(var context: Context, var list:ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var v:View = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ItemHolder).bind(list[position].name, list[position].price, list[position].photo, list[position].id)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        fun bind(n:String, p:Double, u:String, item_id:Int)
        {
            itemView.itemName.text = n
            itemView.itemPrice.text = "$ " + p.toString()
            var url:String = "http://10.2.90.3/OldBookSellingApp/Images/" + u
            url = url.replace(" ", "%20")
            Picasso.get().load(url).into(itemView.itemPhoto)

            itemView.itemAddPhoto.setOnClickListener {
                UserInfo.itemId = item_id
                var obj = QtyFragment()
                var manager = (itemView.context as Activity).fragmentManager
                obj.show(manager, "Qty")
            }
        }
    }

}









