package businesscard.dhruv.businesscardscanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;

/**
 * Created by dhruv on 29/12/16.
 */

public class allCardRecyclerViewAdapter
        extends RecyclerView
        .Adapter<allCardRecyclerViewAdapter
        .DataObjectHolder> {

    public static final String TAG = "myRecViewAdapter";
    private ArrayList<CardObject1> mCardSet;
    private static MyClickListener myClickListener;
    private Context context;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        ImageView imageDrawable;
        TextView txtName;
        TextView txtPosition;
        TextView txtCompany;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imageDrawable = (ImageView) itemView.findViewById(R.id.img_card);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_all_card);
            txtPosition = (TextView) itemView.findViewById(R.id.txt_position_all_card);
            txtCompany = (TextView) itemView.findViewById(R.id.txt_company_all_card);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ShowCardDetails.class);
            v.getContext().startActivity(i);

            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public allCardRecyclerViewAdapter(ArrayList<CardObject1> myCardSet, Context context) {
        mCardSet = myCardSet;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_cards_list_cardview, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.txtName.setText(mCardSet.get(position).getTxtName());
        if (mCardSet.get(position).getmDrawableImage() != 0) {
            holder.imageDrawable.setImageResource(mCardSet.get(position).getmDrawableImage());
        } else {
            char ch = mCardSet.get(position).getTxtName().charAt(0);

            TextDrawable drawable = TextDrawable.builder().beginConfig().fontSize(100).bold().endConfig()
                    .buildRect(String.valueOf(ch).toUpperCase(), Color.rgb(0, 105, 0));
            holder.imageDrawable.setImageDrawable(drawable);
        }
        holder.txtPosition.setText(mCardSet.get(position).getTxtPosition());
        holder.txtCompany.setText(mCardSet.get(position).getTxtCompany());
    }

    public void addItem(CardObject1 cardObject, int index) {
        mCardSet.add(index, cardObject);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mCardSet.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mCardSet.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}