package hankou.com.hankouapp.home;

import android.util.Log;

import com.hankou.utils.ListUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bykj003 on 2016/12/13.
 */
@RunWith(JUnit4.class)
public class ListUtilsTest {

    public ListUtils mListUtil;

    @Before
    public void init(){
        mListUtil = new ListUtils();
    }

    @Test
    public void testList(){
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("peng1");
        stringList.add("peng2");
        stringList.add("peng3");
        stringList.add("peng3");
        stringList.add("peng1");
        stringList.add("peng2");
        stringList.add("peng4");
        Log.i("Junit","处理前:"+stringList.toString());
        List<String> stringList1 = mListUtil.removeSameItemFromList(stringList);
        Log.i("Junit","处理后:"+stringList1.toString());
    }
}
