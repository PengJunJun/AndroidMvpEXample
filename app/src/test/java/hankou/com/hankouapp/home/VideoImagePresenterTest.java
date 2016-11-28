package hankou.com.hankouapp.home;

import com.hankou.common.model.CommonEntity;
import com.hankou.home.model.ImageEntity;
import com.hankou.home.presenter.VideoImageContact;
import com.hankou.home.presenter.VideoImagePresentImpl;
import com.hankou.utils.network.HttpManager;
import com.hankou.utils.network.api.UserApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.observers.TestSubscriber;

/**
 * Created by bykj003 on 2016/11/2.
 */
@RunWith(JUnit4.class)
public class VideoImagePresenterTest {

    @Mock
    public VideoImageContact.VideoImageView mVideoView;

    public VideoImagePresentImpl mVideoPresenter;

    public UserApi mResponsibility;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mResponsibility = HttpManager.getInstance().getUserApi();
    }

    @Test
    public void testVideoImage(){
        mVideoPresenter = new VideoImagePresentImpl();
        mVideoPresenter.attachView(mVideoView);
        mVideoPresenter.getVideoImage();

        TestSubscriber<CommonEntity<ImageEntity>> testSubscriber = new TestSubscriber<>();
        mResponsibility.getVideoImage().subscribe(testSubscriber);
        testSubscriber.assertTerminalEvent();
    }
}
