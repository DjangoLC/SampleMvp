package com.example.joseenrique.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joseenrique.myapplication.R;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;

import java.util.List;

/**
 * Created by Enrique on 21/08/2018.
 */

public class AdapterStock extends RecyclerView.Adapter<AdapterStock.VHClubList>{

    private List<ModelStockBD> data;
    private Context context;

    public AdapterStock(List<ModelStockBD> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public VHClubList onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_content, parent, false);

        return new VHClubList(itemView);
    }

    @Override
    public void onBindViewHolder(VHClubList holder, int position) {
        ModelStockBD current = data.get(position);

            holder.tv_1.setText(""+current.getProduct());
            holder.tv_2.setText(""+current.getStock());

    }

    @Override
    public int getItemCount() {
        return this.data == null ? 0 : this.data.size();
    }

    class VHClubList extends RecyclerView.ViewHolder{

        private TextView tv_1,tv_2;

        public VHClubList(View itemView) {
            super(itemView);

            tv_1     = (TextView)  itemView.findViewById(R.id.tv_1);
            tv_2     = (TextView)  itemView.findViewById(R.id.tv_2);

        }

    }

}
