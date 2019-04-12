package com.example.apple.json2kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_fruit.view.*

public  class FruitViewAdapter(val fruits:List<Fruits>):RecyclerView.Adapter<Cell>(){

    override fun getItemCount(): Int {

        return this.fruits.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Cell {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cell_fruit,parent,false)
        return Cell(view)
    }

    override fun onBindViewHolder(cell: Cell, row: Int) {

        cell.itemView.textViewFruitName.text = this.fruits.get(row).name
        cell.itemView.textViewFruitUses.text = this.fruits.get(row).uses
    }

}

public  class Cell(view: View):RecyclerView.ViewHolder(view)