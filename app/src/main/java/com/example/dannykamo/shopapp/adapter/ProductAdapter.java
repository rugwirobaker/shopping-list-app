package com.example.dannykamo.shopapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dannykamo.shopapp.R;
import com.example.dannykamo.shopapp.items.ProdItem;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
   private ArrayList<ProdItem> prodItems;
   private Context context;

    public ProductAdapter(ArrayList<ProdItem> prodItems, Context context) {
        this.prodItems = prodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return  new ProductHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_product_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder productHolder, int i) {
        ProdItem prodItem = (ProdItem) prodItems.get(i);
        productHolder.txtUserId.setText(Integer.toString(prodItem.getUserId()));
        productHolder.txtProdName.setText(prodItem.getProdName());
        productHolder.txtProdDescription.setText(prodItem.getProdDesc());
    }

    @Override
    public int getItemCount() {
        return prodItems.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        TextView txtUserId,txtProdName,txtProdDescription;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            txtUserId=(TextView)itemView.findViewById(R.id.txt_user);
            txtProdName=(TextView)itemView.findViewById(R.id.txt_pro_name);
            txtProdDescription=(TextView)itemView.findViewById(R.id.txt_pro_desc);
        }
    }
}
