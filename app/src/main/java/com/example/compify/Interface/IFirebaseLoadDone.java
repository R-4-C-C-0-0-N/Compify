package com.example.compify.Interface;

import com.example.compify.Model.Data;
import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Data> dataList);
    void onFirebaseLoadFailed(String message);
}
