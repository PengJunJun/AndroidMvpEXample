package hankou.com.hankouapp.home;

import com.hankou.common.model.CommonEntity;
import com.hankou.home.presenter.HomeContact;
import com.hankou.home.presenter.HomePresenterImpl;
import com.hankou.mine.model.UserEntity;
import com.hankou.utils.RxUnitTestTools;
import com.hankou.utils.network.HttpManager;
import com.hankou.utils.network.api.UserApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Observer;
import rx.observers.Observers;
import rx.observers.TestSubscriber;

/**
 * 主要测试删除用户和获取所以用户功能
 * <p>
 * Created by pjj on 2016/10/21.
 */
@RunWith(JUnit4.class)
public class HomePresenterTest {

    @Mock
    public HomeContact.IHomeView mHomeView;
    private HomePresenterImpl mHomePresenter;

    @Mock
    private UserApi mUserResponsibility;

    private CommonEntity<List<UserEntity>> mUserData;

    @Captor
    private ArgumentCaptor<CommonEntity<List<UserEntity>>> mArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RxUnitTestTools.openRxTools();

        mHomePresenter = new HomePresenterImpl(mHomeView,mUserResponsibility);
        List<UserEntity> userList = Arrays.asList(new UserEntity(1, "peng", "jun", "jun"),
                new UserEntity(2, "peng11111", "jun11111", "jun11111"));
        mUserData = new CommonEntity<>();
        mUserData.data = userList;
        mUserData.status = true;
    }

    @Test
    public void testGatAllUser() {
        Mockito.when(mUserResponsibility.getAllUser()).thenReturn(Observable.just(mUserData));
        mHomePresenter.getData();
        Mockito.verify(mUserResponsibility).getAllUser();
        Mockito.verify(mHomeView).showSuccess(mUserData.data);
    }
}
