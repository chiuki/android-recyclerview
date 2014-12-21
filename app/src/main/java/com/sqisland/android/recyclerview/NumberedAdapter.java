package com.sqisland.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
  private List<String> labels;

  public NumberedAdapter(int count) {
    labels = new ArrayList<String>(count);
    for (int i = 0; i < count; ++i) {
      labels.add(String.valueOf(i));
    }
  }

  @Override
  public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
    return new TextViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final TextViewHolder holder, final int position) {
    final String label = labels.get(position);
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
  public int getItemCount() {
    return labels.size();
  }
}