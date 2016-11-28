package hankou.com.hankouapp.home;

import com.hankou.common.model.CommonEntity;
import com.hankou.home.presenter.HomeContact;
import com.hankou.home.presenter.HomePresenterImpl;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.RxUnitTestTools;
import com.hankou.utils.network.HttpManager;
import com.hankou.utils.network.api.UserApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Observer;
import rx.observers.TestSubscriber;

/**
 * 主要测试删除用户和获取所以用户功能
 * <p>
 * Created by pjj on 2016/10/21.
 */
@RunWith(JUnit4.class)
public class HomePresenterTest {

    @Mock
    public HomeContact.IHomeView mUserDetailView;

    private HomePresenterImpl mHomePresenter;
    public UserApi mResponsibility;
    private static final int TEST_UID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RxUnitTestTools.openRxTools();
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.attachView(mUserDetailView);
        mResponsibility = HttpManager.getInstance().getUserApi();
    }

    @Test
    public void testGatAllUser() {
        mHomePresenter.getData();
        TestSubscriber<CommonEntity<List<UserEntity>>> testSubscriber = new TestSubscriber<>();
        Assert.assertNotNull("Responsibility Is Null", mResponsibility);
        mResponsibility.getAllUser().subscribe(testSubscriber);

        Mockito.verify(mUserDetailView).showSuccess(Mockito.anyList());
    }

    @Test
    public void testErrorGatAllUser() {
        mHomePresenter.getData();
        TestSubscriber<CommonEntity<List<UserEntity>>> testSubscriber = new TestSubscriber<>();
        testSubscriber.assertError(new TimeoutException());
        Assert.assertNotNull("Responsibility Is Null", mResponsibility);
        mResponsibility.getAllUser().subscribe(testSubscriber);

        Mockito.verify(mUserDetailView).showError(Mockito.anyString());
    }

    @Test
    public void testDeleteUser(){
    }
}
