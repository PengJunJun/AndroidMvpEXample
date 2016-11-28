package hankou.com.hankouapp.home;

import com.hankou.home.presenter.VideoContact;
import com.hankou.home.presenter.VideoPresenterImpl;
import com.hankou.utils.RxUnitTestTools;
import com.hankou.utils.network.HttpManager;
import com.hankou.utils.network.api.UserApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * 主要测试删除用户和获取所以用户功能
 * <p>
 * Created by pjj on 2016/10/21.
 */
@RunWith(JUnit4.class)
public class UserDetailPresenterTest {

    @Mock
    public VideoContact.VideoView mUserDetailView;

    @Mock
    public UserApi mResponsibility;

    private VideoPresenterImpl mUserPresenter;

    private static final int TEST_UID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RxUnitTestTools.openRxTools();
        mUserPresenter = new VideoPresenterImpl();
        mUserPresenter.attachView(mUserDetailView);
        mResponsibility = HttpManager.getInstance().getUserApi();
    }

    @Test
    public void testDeleteUser() {

    }
}
