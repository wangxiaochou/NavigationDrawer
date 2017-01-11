package wlhwh.example.com.offical_navigation_drawers;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by WLHWH on 2016/12/29.
 */

public class DeveloperFragment extends Fragment {

    private WebView mWebView;

    /**
     * Constants
     */
    private View mContentView;
    private Activity mSelf;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != mContentView) {
            ViewGroup vg = (ViewGroup) mContentView.getParent();
            if (null != vg) {
                vg.removeView(mContentView);
            }
        } else {
            mContentView = inflater.inflate(R.layout.dev_fragment, container, false);
            mSelf = getActivity();
            setupViews();
        }
        return mContentView;
    }


    private void setupViews() {
    }
}
