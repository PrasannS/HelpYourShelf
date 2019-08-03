package com.scdevs.helpyourshelf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



    public class BooksRecyclerView extends RecyclerView.Adapter<BooksRecyclerView.ViewHolder>{
        private List<String> mData;
        private LayoutInflater mInflater;
        private com.scdevs.helpyourshelf.BooksRecyclerView.ItemClickListener mClickListener;

        BooksRecyclerView(Context context, List<String> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @Override
        public BooksRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.bookitem, parent, false);
            return new BooksRecyclerView.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BooksRecyclerView.ViewHolder holder, int position) {
            String name = mData.get(position);
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
                myTextView = itemView.findViewById(R.id.shelfname);
                itemView.setOnClickListener(this);
            }


            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        String getItem(int id) {
            return mData.get(id);
        }

        void setClickListener(com.scdevs.helpyourshelf.BooksRecyclerView.ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }
