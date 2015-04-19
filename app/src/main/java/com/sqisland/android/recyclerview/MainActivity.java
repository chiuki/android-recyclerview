package com.sqisland.android.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);

    Demo[] demos = {
      new Demo(this, LinearLayoutActivity.class, R.string.linear_layout),
      new Demo(this, GridLayoutActivity.class, R.string.grid_layout),
      new Demo(this,
          GridLayoutVariableSpanSizeActivity.class, R.string.grid_layout_variable_span_size),
      new Demo(this, GridLayoutHeaderActivity.class, R.string.grid_layout_header),
      new Demo(this, GridLayoutAutoFitActivity.class, R.string.grid_layout_auto_fit),
      new Demo(this, GridLayoutAutoFitHeaderActivity.class, R.string.grid_layout_auto_fit_header),
      new Demo(this, GridLayoutAddRemoveActivity.class, R.string.grid_layout_add_remove)
    };

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.addItemDecoration(new MarginDecoration(this));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(new DemoAdapter(demos));
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  private static class DemoAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private final Demo[] demos;

    public DemoAdapter(Demo[] demos) {
      this.demos = demos;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int position) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
      return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
      final Demo demo = demos[position];
      holder.textView.setText(demo.title);
      holder.textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Context context = holder.textView.getContext();
          context.startActivity(new Intent(context, demo.activityClass));
        }
      });
    }

    @Override
    public int getItemCount() {
      return demos.length;
    }
  }

  public static class Demo {
    public final Class<?> activityClass;
    public final String title;

    public Demo(Context context, Class<?> activityClass, int titleId) {
      this.activityClass = activityClass;
      this.title = context.getString(titleId);
    }
  }
}