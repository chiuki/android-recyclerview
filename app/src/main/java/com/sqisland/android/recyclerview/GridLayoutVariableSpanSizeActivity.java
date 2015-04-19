package com.sqisland.android.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class GridLayoutVariableSpanSizeActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.addItemDecoration(new MarginDecoration(this));
    recyclerView.setHasFixedSize(true);

    GridLayoutManager manager = new GridLayoutManager(this, 3);
    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return (3 - position % 3);
      }
    });
    recyclerView.setLayoutManager(manager);

    recyclerView.setAdapter(new NumberedAdapter(30));
  }
}