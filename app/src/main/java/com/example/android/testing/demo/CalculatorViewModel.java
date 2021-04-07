package com.example.android.testing.demo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author zhangjifeng
 * @date on 2021/04/07 17:17
 * @describe
 */
public class CalculatorViewModel extends ViewModel {

    public MutableLiveData<String> calculatorResultLiveData = new MutableLiveData<>();
    private final Calculator mCalculator = new Calculator();

    public void calc(final double operandOne, final double operandTwo, final Calculator.Operator operator) {
        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(mCalculator.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(mCalculator.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(mCalculator.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(mCalculator.mul(operandOne, operandTwo));
                break;
            default:
                result = "Error";
                break;
        }
        calculatorResultLiveData.postValue(result);
    }
}
