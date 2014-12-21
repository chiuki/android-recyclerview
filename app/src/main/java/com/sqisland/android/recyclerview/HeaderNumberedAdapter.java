package com.sqisland.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HeaderNumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
  private static final int ITEM_VIEW_TYPE_HEADER = 0;
  private static final int ITEM_VIEW_TYPE_ITEM = 1;

  private final View header;
  private final List<String> labels;

  public HeaderNumberedAdapter(View header, int count) {
    if (header == null) {
      throw new IllegalArgumentException("header may not be null");
    }
    this.header = header;
    this.labels = new ArrayList<String>(count);
    for (int i = 0; i < count; ++i) {
      labels.add(String.valueOf(i));
    }
  }

  public boolean isHeader(int position) {
    return position == 0;
  }

  @Override
  public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == ITEM_VIEW_TYPE_HEADER) {
      return new TextViewHolder(header);
    }
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
    return new TextViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final TextViewHolder holder, final int position) {
    if (isHeader(position)) {
      return;
    }
    final String label = labels.get(position - 1);  // Subtract 1 for header
    holder.textView.setText(label);
    holder.textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(
            holder.textView.getContext(), label, Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemViewType(int position) {
    return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
  }

  @Override
  public int getItemCount() {
    return labels.size() + 1;
  }
}