package ink.moming.installmentmemo;

import android.provider.BaseColumns;

import org.junit.Test;

import java.lang.reflect.Modifier;

import ink.moming.installmentmemo.data.InstallmentContract;


import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void inner_class_exists() throws Exception {
        Class[] innerClasses = InstallmentContract.class.getDeclaredClasses();
        assertEquals("There should be 1 Inner class inside the contract class", 2, innerClasses.length);
    }

}