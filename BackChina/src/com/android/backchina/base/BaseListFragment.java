package com.android.backchina.base;

import java.lang.reflect.Type;
import java.util.Date;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

import com.android.backchina.AppContext;
import com.android.backchina.AppOperator;
import com.android.backchina.R;
import com.android.backchina.base.adapter.BaseListAdapter;
import com.android.backchina.interf.OnTabReselectListener;
import com.android.backchina.ui.empty.EmptyLayout;
import com.android.backchina.utils.TLog;
import com.android.backchina.widget.XListView;

public abstract class BaseListFragment<T> extends BaseFragment<T> implements XListView.IXListViewListener,
             OnItemClickListener,OnItemLongClickListener, BaseListAdapter.Callback, View.OnClickListener,OnTabReselectListener {

    //
    protected XListView mListView;
    
    protected EmptyLayout mErrorLayout;
    
    protected BaseListAdapter<T> mAdapter;
    
    @Override
    protected int getLayoutId() {
        // TODO Auto-generated method stub
        return R.layout.fragment_base_list;
    }
    
    @Override
    protected void setupViews(View root) {
        // TODO Auto-generated method stub
        super.setupViews(root);
        mListView = (XListView) root.findViewById(R.id.pull_and_load_listview);
        mErrorLayout = (EmptyLayout) root.findViewById(R.id.error_layout);
        mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        mErrorLayout.setOnLayoutClickListener(this);
    }
    
    @Override
    protected void initData() {
        // TODO Auto-generated method stub
        super.initData();
        //when open this fragment,read the obj
        mAdapter = getListAdapter();
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    	requestData();
    }

    @Override
    public void onRefresh() {
    	// TODO Auto-generated method stub
    	requestData();
    }
    
    public void refreshComplete(){
    	mListView.stopRefresh();
    }
    
    @Override
    public void onLoadMore() {
    	// TODO Auto-generated method stub
    	TLog.d("called");
    }
    
    public void stopLoadMore(){
    	mListView.stopLoadMore();
    }
    
    public void loadMoreNodata(){
    	mListView.completeLoadMore();
    }
    
	public void autoRefresh() {
		mListView.setSelection(0);
		mListView.autoRefresh();

	}
    
    /**
     * request network data
     */
    private void requestData() {
    	TLog.d("called");
    	onRequestData();
    }
    
    @Override
    protected void onShow() {
    	// TODO Auto-generated method stub
    	super.onShow();
    }
    
    @Override
    protected void onHide() {
    	// TODO Auto-generated method stub
		if (mErrorLayout != null) {
			mErrorLayout.dismiss();
		}
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
    	TLog.d("called");
    }
    
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
    		int position, long id) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    protected void onRequestData() {

    }
    
	protected void onRequestError(int type) {
		refreshComplete();
		setEmptyLayoutStatus(type);
	}
    
    protected void onRequestSuccess() {
    	refreshComplete();
    	mErrorLayout.dismiss();
    }
    
    protected void setEmptyLayoutStatus(int type){
    	if (mErrorLayout != null) {
			mErrorLayout.setErrorType(type);
		}
    }
    
    /**
     * save readed list
     *
     * @param fileName fileName
     * @param key      key
     */
    protected void saveToReadedList(String fileName, String key) {

        // 放入已读列表
        AppContext.putReadedPostList(fileName, key, "true");
    }
    

    /**
     * update textColor
     *
     * @param title   title
     * @param content content
     */
    protected void updateTextColor(TextView title, TextView content) {
        if (title != null) {
            title.setTextColor(getResources().getColor(R.color.count_text_color_light));
        }
        if (content != null) {
            content.setTextColor(getResources().getColor(R.color.count_text_color_light));
        }
    }
    
    @Override
    public Date getSystemTime() {
        return new Date();
    }
    
    protected boolean isNeedFooter() {
        return true;
    }
    
    protected abstract BaseListAdapter<T> getListAdapter();

    protected abstract Type getType();
}
