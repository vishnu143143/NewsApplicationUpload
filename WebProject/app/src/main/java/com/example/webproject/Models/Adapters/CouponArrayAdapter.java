package com.example.webproject.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webproject.Apipackage.InterfacePassing;
import com.example.webproject.Models.CouponDisplayingModelClass.CouponProductDetails;
import com.example.webproject.R;

import java.util.ArrayList;

public class CouponArrayAdapter extends RecyclerView.Adapter<CouponArrayAdapter.MyViewHolder> {
    ArrayList<CouponProductDetails> couponProductDetails;


    private Context context;
    int ii;
    private InterfacePassing listen;

    public CouponArrayAdapter(Context context,ArrayList<CouponProductDetails> couponProductDetails,InterfacePassing passing) {
        this.couponProductDetails = couponProductDetails;
        this.context = context;
        this.listen=passing;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.couponproduct, parent, false);
        CouponArrayAdapter.MyViewHolder vh = new CouponArrayAdapter.MyViewHolder(v);
//      View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_details,parent,false);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

       // holder.checkBox.setChecked(selectedPosition == position);

        holder.offerText.setText(couponProductDetails.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.checkBox.isChecked())
                {
                    holder.checkBox.setChecked(false);
                }
                else
                {
                    holder.checkBox.setChecked(true);
                }
                if(holder.checkBox.isChecked()==true){

                    listen.pass(couponProductDetails.get(position).getId());
                    ii=couponProductDetails.get(position).getId();
                    // selectedPosition++;

                    notifyDataSetChanged();
                }

                else
                {
                    notifyDataSetChanged();


                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return couponProductDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
TextView offerText;
      public  CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            offerText = itemView.findViewById(R.id.offers);
            checkBox=itemView.findViewById(R.id.couponchecked);
        }
    }
}



