package com.thisit.southavencrm.FAQList.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thisit.southavencrm.FAQList.model.FAQListResponseModel;
import com.thisit.southavencrm.FAQList.view.FQAFragment;
import com.thisit.southavencrm.FAQList.view.IFAQListView;
import com.thisit.southavencrm.R;

import java.util.ArrayList;
import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.HeroViewHolder> {


    private List<FAQListResponseModel> faqListResponseModelArrayList;
    private Context context;
    private IFAQListView ifaqListView;
    private static int currentPosition = 0;



    public FAQAdapter(Context context, ArrayList<FAQListResponseModel> faqListResponseModelArrayList, FQAFragment fqaFragment) {

        this.context = context;
        this.faqListResponseModelArrayList = faqListResponseModelArrayList;
        this.ifaqListView = fqaFragment;

    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_faq, parent, false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        FAQListResponseModel hero = faqListResponseModelArrayList.get(position);
        holder.Title_textView.setText(hero.getTitle());
        holder.ShortDescription_textview.setText(hero.getShortDescription());

        holder.linearLayout.setVisibility(View.GONE);
        if (currentPosition == position) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(slideDown);
        }
        holder.Title_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return faqListResponseModelArrayList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView Title_textView, ShortDescription_textview;
        LinearLayout linearLayout;

        HeroViewHolder(View itemView) {
            super(itemView);

            Title_textView = itemView.findViewById(R.id.Title_textView);
            ShortDescription_textview = itemView.findViewById(R.id.ShortDescription_textview);


            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}