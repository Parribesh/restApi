package com.example.restapi;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void getUsername() {
        assertEquals("din_djarin", MainActivity.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("baby_yoda_ftw", MainActivity.getPassword());
    }
}