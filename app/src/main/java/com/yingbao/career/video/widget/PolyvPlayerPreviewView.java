package com.yingbao.career.video.widget;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.easefun.polyvsdk.PolyvSDKUtil;
import com.easefun.polyvsdk.util.PolyvDownloadDirUtil;
import com.easefun.polyvsdk.vo.PolyvVideoVO;
import com.yingbao.career.R;
import com.yingbao.career.common.MyApplication;

import java.io.File;

/**
 * 预览图视图
 * @author Lion 2016-3-3
 */
public class PolyvPlayerPreviewView extends RelativeLayout {
	private static final String TAG = PolyvPlayerPreviewView.class.getSimpleName();
	private Context mContext = null;
	private ImageView mPreviewImage = null;
	private ImageView iv_finish ;
	private ImageButton mStartBtn = null;
	private Callback mCallback = null;

    public PolyvPlayerPreviewView(Context context) {
        this(context, null);
    }
    
    public PolyvPlayerPreviewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public PolyvPlayerPreviewView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initViews();
    }
    
    private void initViews() {
    	LayoutInflater.from(getContext()).inflate(R.layout.polyv_player_preview_view, this);
		mPreviewImage = (ImageView) findViewById(R.id.preview_image);
		mStartBtn = (ImageButton) findViewById(R.id.start_btn);
		iv_finish = (ImageView) findViewById(R.id.iv_finish);
		mStartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mCallback != null) {
					mCallback.onClickStart();
				}

                hide();
			}
		});
    }

    public void setFinishClickListener(OnClickListener onClickListener){
    	iv_finish.setOnClickListener(onClickListener);
	}
    /**
     * 设置图片并显示
     * @param vid
     */
    public void show(String vid) {
		new LoadVideoJson(vid).execute();
        setVisibility(View.VISIBLE);
    }

	private class LoadVideoJson extends AsyncTask<String, Void, PolyvVideoVO> {

		private final String mVid;

		LoadVideoJson(String vid) {
			mVid = vid;
		}

		@Override
		protected PolyvVideoVO doInBackground(String... params) {
			PolyvVideoVO video = null;
			try {
				video = PolyvSDKUtil.loadVideoJSON2Video(mVid);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return video;
		}

		@Override
		protected void onPostExecute(PolyvVideoVO v) {
			super.onPostExecute(v);
			if (v == null) {
				return;
			}

			if (TextUtils.isEmpty(v.getFirstImage())) {
				return;
			}

			int index = 0;
			if (v.getFirstImage().contains("/")) {
				index = v.getFirstImage().lastIndexOf("/");
			}

			String fileName = v.getFirstImage().substring(index);
			File file = PolyvDownloadDirUtil.getFileFromExtraResourceDir(mVid, fileName);
			if (file != null) {
				mPreviewImage.setImageURI(Uri.parse(file.getAbsolutePath()));
			} else {
			Glide.with(MyApplication.context).load(v.getFirstImage()).into(mPreviewImage);
		}
		}
	}

    /**
     * 隐藏
     */
    public void hide() {
        setVisibility(View.GONE);
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
    	super.onTouchEvent(event);
		return true;
	}

    public void setCallback(Callback callback) {
    	mCallback = callback;
    }

    public interface Callback {
    	public void onClickStart();
    }
}
