package com.sqisland.android.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class GridLayoutAutoFitActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auto_fit_recycler_view);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.addItemDecoration(new MarginDecoration(this));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(new NumberedAdapter(30));
  }
}