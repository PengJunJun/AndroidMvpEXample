package com.hankou.home.view;

<<<<<<< HEAD
import com.hankou.base.BaseActivity;
=======
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import com.hankou.R;
import com.hankou.base.BaseActivity;
import com.hankou.mine.view.MineFragment;
import com.hankou.scene.view.SceneFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import butterknife.BindView;
>>>>>>> 55b901b... test first commit

/**
 * Created by bykj003 on 2016/11/15.
 */

public class SearchActivity extends BaseActivity{

    @Override
    public void showError(String message) {
        showToast("one commit");
        showError("two commit");
    }
}
