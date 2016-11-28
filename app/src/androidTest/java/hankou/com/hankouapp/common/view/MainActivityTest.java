package hankou.com.hankouapp.common.view;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.hankou.R;
import com.hankou.common.view.MainActivity;
import com.hankou.home.view.HomeFragment;
import com.hankou.mine.view.MineFragment;
import com.hankou.scene.view.SceneFragment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.matcher.LayoutMatchers.*;

/**
 * Created by bykj003 on 2016/9/19.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mTestActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mMainActivity;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mMainActivity = mTestActivity.getActivity();
    }
}
