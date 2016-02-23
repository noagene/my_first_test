package com.project.demo.demoprojects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kimsj on 2016. 2. 11..
 */
public class DemoModel {
    private static int nextId = 0;
    public String label;
    public Date dateTime;
    public int cellType = 0;
    public List<String> bannerList = new ArrayList<>();

    String pathToImage;
    int id = ++nextId;


}
