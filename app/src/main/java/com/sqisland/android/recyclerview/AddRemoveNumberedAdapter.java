package com.sqisland.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AddRemoveNumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
  private static final int ITEM_VIEW_TYPE_ITEM = 0;
  private static final int ITEM_VIEW_TYPE_ADD = 1;

  private List<String> labels;

  public AddRemoveNumberedAdapter(int count) {
    labels = new ArrayList<String>(count);
    for (int i = 0; i < count; ++i) {
      labels.add(String.valueOf(i));
    }
  }

  @Override
  public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(
            viewType == ITEM_VIEW_TYPE_ADD ? R.layout.item_add : R.layout.item, parent, false);
    return new TextViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final TextViewHolder holder, final int position) {
    if (position == labels.size()) {
      holder.textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          addItem();
        }
      });
      return;
    }

    final String label = labels.get(position);
    holder.textView.setText(label);
    holder.textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeItem(holder.getPosition());
        }
    });
  }

  private void addItem() {
    if (labels.size() >=1){
        int lastItem = Integer.parseInt(labels.get(labels.size() - 1));
        labels.add(String.valueOf(lastItem + 1));
        notifyItemInserted(labels.size() - 1);
    } else {
        labels.add(new String("0"));
        notifyItemInserted(0);
    }

  }

  private void removeItem(int position) {
    labels.remove(position);
    notifyItemRemoved(position);
  }

  @Override
  public int getItemViewType(int position) {
    return position == labels.size() ? ITEM_VIEW_TYPE_ADD : ITEM_VIEW_TYPE_ITEM;
  }

  @Override
  public int getItemCount() {
    return labels.size() + 1;
  }
}