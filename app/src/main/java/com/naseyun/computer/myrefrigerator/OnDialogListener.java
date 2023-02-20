package com.naseyun.computer.myrefrigerator;

//다이얼로그가 종료될 때 변경한 값을 받아오기 위한 인터페이스
public interface OnDialogListener {
    void onFinish(int position, Mybasket mybasket);
}
