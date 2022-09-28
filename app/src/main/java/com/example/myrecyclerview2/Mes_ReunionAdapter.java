package com.example.myrecyclerview2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Mes_ReunionAdapter extends RecyclerView.Adapter<Mes_ReunionAdapter.MyViewHolder> {
    private ArrayList<Liste_Reunion> listData;
    private Context context;
    private OnEditListener onEditListener;



    public Mes_ReunionAdapter(Context context, ArrayList<Liste_Reunion> list, OnEditListener onEditListener){
        this.listData = list;
        this.context = context;
        this.onEditListener = onEditListener;


    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reunion_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position){
        Liste_Reunion dataObj = listData.get(position);

        holder.mNameTV.setText(dataObj.getNameReunion());
        holder.mliste_emailTV.setText(dataObj.getListe_emailReunion());
        holder.Heure.setText(dataObj.getHeureReunion());
        holder.mimage_Delete.setOnClickListener(view -> {
            listData.remove(position);
            notifyDataSetChanged();
        });
        holder.mimage_Edit.setOnClickListener(view -> {
            onEditListener.onEditClick(listData.get(position), position);
        });



    }


    @Override
    public int getItemCount() {

        return listData.size();
    }





    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView mNameTV;
         TextView mliste_emailTV;
         ImageView mimage_Edit;
         ImageView mimage_Delete;
        TextView Heure;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTV = itemView.findViewById(R.id.nameTV);
            mliste_emailTV =itemView.findViewById(R.id.liste_emailTV);
            mimage_Edit = itemView.findViewById(R.id.image_Edit);
            mimage_Delete = itemView.findViewById(R.id.image_Delete);
            Heure = itemView.findViewById(R.id.Heure);


        }

    }

    public void editData(Liste_Reunion listDataObj, int currentPosition){

        listData.get(currentPosition).setNameReunion(listDataObj.getNameReunion());
        listData.get(currentPosition).setListe_emailReunion(listDataObj.getListe_emailReunion());
        notifyDataSetChanged();
    }


    public interface OnEditListener{
        void onEditClick(Liste_Reunion listCurrentData,int CurrentPosition);
    }









}
