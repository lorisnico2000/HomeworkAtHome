package com.home.homework.homeworkhome;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sven.reutimann on 21.11.2017.
 */

public class HomeActivityUnitTest {

    @Test
    public void testLoadSubjects() throws Exception {
        HomeActivity homeActivity = new HomeActivity();
        Assert.assertNotNull(homeActivity.loadSubjects());
    }

}
