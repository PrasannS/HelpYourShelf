package com.scdevs.helpyourshelf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.scdevs.helpyourshelf.DBModels.Volume;

import java.util.List;



    public class BooksRecyclerView extends RecyclerView.Adapter<BooksRecyclerView.ViewHolder>{
        private List<Volume> mData;
        private LayoutInflater mInflater;
        private com.scdevs.helpyourshelf.BooksRecyclerView.ItemClickListener mClickListener;

        BooksRecyclerView(Context context, List<Volume> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.bookitem, parent, false);
            return new BooksRecyclerView.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BooksRecyclerView.ViewHolder holder, int position) {
            String name = mData.get(position).getTitle();
            holder.myTextView.setText(name);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.bookname);
                itemView.setOnClickListener(this);
            }


            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        String getItem(int id) {
            return mData.get(id).getTitle();
        }

        void setClickListener(com.scdevs.helpyourshelf.BooksRecyclerView.ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }


        public interface ItemClickListener {

            void onItemClick(View view, int position);
        }
    }

