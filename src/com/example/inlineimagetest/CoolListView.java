package com.example.inlineimagetest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Custom list view to have better scroll listeners
 * @author Scruffy
 */
public class CoolListView extends ListView
{
	private final Context mContext;
	private List<OnScrollListener> mOnScrollListener;
	private int mScrollState;

	public interface OnScrollListener
	{
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
		public void onScrollStateChanged(AbsListView view, int scrollState);
		public void onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
	}

	public CoolListView(Context context)
	{
		super(context);
		mContext = context;

		init();
	}

	public CoolListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mContext = context;

		init();
	}

	public int getScrollState()
	{
		return mScrollState;
	}

	private void init()
	{
		mOnScrollListener = new ArrayList<OnScrollListener>();

		setOnScrollListener(new AbsListView.OnScrollListener()
		{
			@Override public void onScrollStateChanged(AbsListView view, int scrollState)
			{
				mScrollState = scrollState;
				if (mOnScrollListener != null)
				{
					for (OnScrollListener l : mOnScrollListener)
					{
						l.onScrollStateChanged(view, scrollState);
					}
				}
			}

			@Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				if (mOnScrollListener != null)
				{
					for (OnScrollListener l : mOnScrollListener)
					{
						l.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
					}
				}
			}
		});
	}

	public void addOnScrollListener(OnScrollListener l)
	{
		this.mOnScrollListener.add(l);
	}

	public void removeOnScrollListener(OnScrollListener l)
	{
		this.mOnScrollListener.remove(l);
	}

	public void setOnScrollListener(OnScrollListener l)
	{
		this.mOnScrollListener.clear();
		this.mOnScrollListener.add(l);
	}
}