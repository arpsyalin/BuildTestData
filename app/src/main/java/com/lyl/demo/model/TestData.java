package com.lyl.demo.model;

import com.lyl.testobject.TestListType;
import com.lyl.testobject.TestObject;

import java.io.Serializable;
import java.util.List;

/**
 * * @Description
 * * @Author 刘亚林
 * * @CreateDate 2021/9/23
 * * @Version 1.0
 * * @Remark TODO
 **/
public class TestData implements Serializable {
    @TestObject(value = {"张三", "李四", "王五"})
    private String userName;
    @TestObject(value = {"13511111111", "13500000000"})
    public String userTel;
    @TestObject(value = {"2021-09-28 18:44:12", "2021-09-30 18:44:12"})
    private String createTime;
    @TestObject(value = {"113.0846", "113.1846"})
    private String svrLongitude;
    @TestObject(value = {"28.204844", "28.212222"})
    private String svrLatitude;
    @TestListType(listType = TestChildData.class)
    private List<TestChildData> data;
    private TestData2 testData2;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSvrLongitude() {
        return svrLongitude;
    }

    public void setSvrLongitude(String svrLongitude) {
        this.svrLongitude = svrLongitude;
    }

    public String getSvrLatitude() {
        return svrLatitude;
    }

    public void setSvrLatitude(String svrLatitude) {
        this.svrLatitude = svrLatitude;
    }

    public List<TestChildData> getData() {
        return data;
    }

    public void setData(List<TestChildData> data) {
        this.data = data;
    }

    public TestData2 getTestData2() {
        return testData2;
    }

    public void setTestData2(TestData2 testData2) {
        this.testData2 = testData2;
    }
}
