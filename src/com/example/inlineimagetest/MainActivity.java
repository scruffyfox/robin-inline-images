package com.example.inlineimagetest;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainActivity extends ListActivity
{
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<DataMap> items = new ArrayList<DataMap>();
		items.add(new DataMap(R.drawable.a));
		items.add(new DataMap(-1));
		items.add(new DataMap(-1));
		items.add(new DataMap(R.drawable.b));
		items.add(new DataMap(-1));
		items.add(new DataMap(-1));
		items.add(new DataMap(R.drawable.c));
		items.add(new DataMap(-1));
		items.add(new DataMap(-1));
		TestAdapter adapter = new TestAdapter(this, items);

		getListView().setAdapter(adapter);
	}

	private class TestAdapter extends BaseAdapter
	{
		private final Context context;
		private final List<DataMap> items;

		public TestAdapter(Context context, List<DataMap> objects)
		{
			this.context = context;
			this.items = objects;
		}

		@Override public int getCount()
		{
			return items.size();
		}

		@Override public DataMap getItem(int position)
		{
			return items.get(position);
		}

		@Override public long getItemId(int position)
		{
			return 0;
		}

		@Override public View getView(int position, View convertView, ViewGroup parent)
		{
			DataMap item = getItem(position);
			ViewHolder holder;

			if (convertView == null)
			{
				convertView = getLayoutInflater().inflate(R.layout.list_item, null, false);
				holder = new ViewHolder();
				holder.imageView = (ScrollableImageView)convertView.findViewById(R.id.image_view);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder)convertView.getTag();
			}

			if (item.imageResource > -1)
			{
				holder.imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), item.imageResource));
			}
			else
			{
				holder.imageView.setImageBitmap(null);
			}

			return convertView;
		}
	}

	private class ViewHolder
	{
		public ScrollableImageView imageView;
	}

	private class DataMap
	{
		public int imageResource = -1;
		public DataMap(int res)
		{
			imageResource = res;
		}
	}
}