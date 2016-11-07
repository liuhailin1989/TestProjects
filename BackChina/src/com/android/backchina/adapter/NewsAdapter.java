package com.android.backchina.adapter;

import android.widget.TextView;

import com.android.backchina.AppContext;
import com.android.backchina.R;
import com.android.backchina.base.adapter.BaseListAdapter;
import com.android.backchina.bean.News;
import com.android.backchina.fragment.NewsFragment;
import com.android.backchina.utils.StringUtils;

public class NewsAdapter extends BaseListAdapter<News>{

	public NewsAdapter(Callback callback) {
		super(callback);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void convert(ViewHolder vh, News item, int position) {
		// TODO Auto-generated method stub
		TextView title = vh.getView(R.id.tv_title);
		
		//
		title.setText(item.getTitle());
//		vh.setText(R.id.tv_title, item.getTitle());
		vh.setImageForNet(R.id.iv_thumb, item.getPic());
		vh.setText(R.id.tv_time, StringUtils.friendly_time(item.getDateline()));
		vh.setText(R.id.tv_origin, item.getFrom());
		//
		String cacheName = NewsFragment.HISTORY_NEWS;
		if (AppContext.isOnReadedPostList(cacheName, item.getId() + "")) {
			title.setTextColor(mCallback.getContext().getResources().getColor(R.color.count_text_color_light));
		}else{
			title.setTextColor(mCallback.getContext().getResources().getColor(R.color.news_item_title_color));
		}
	}

	@Override
	protected int getLayoutId(int position, News item) {
		// TODO Auto-generated method stub
		return R.layout.layout_list_item_news;
	}
}
