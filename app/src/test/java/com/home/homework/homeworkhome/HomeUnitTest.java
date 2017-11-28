package com.home.homework.homeworkhome;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sven.reutimann on 21.11.2017.
 */

public class HomeUnitTest {

    @Test
    public void testLoadSubjects() throws Exception {
        Home home = new Home();
        Assert.assertNotNull(home.loadSubjects());
    }

}
