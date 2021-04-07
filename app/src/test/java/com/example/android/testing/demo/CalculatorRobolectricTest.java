package com.example.android.testing.demo;

import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.testing.demo.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * @author zhangjifeng
 * @date on 2021/04/02 19:00
 * @describe
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class CalculatorRobolectricTest {

    private CalculatorActivity calculatorActivity;
    private EditText et1, et2;
    private TextView resultTextView;

    @Before
    public void setUp() {
        calculatorActivity = Robolectric.setupActivity(CalculatorActivity.class);
        resultTextView = calculatorActivity.findViewById(R.id.operation_result_text_view);
    }

    @After
    public void tearDown() {
        calculatorActivity = null;
        et1 = null;
        et2 = null;
        resultTextView = null;
    }

    @Test
    public void noOperandShowsComputationError() {
        final String expectedResult = calculatorActivity.getString(R.string.computationError);
        calculatorActivity.findViewById(R.id.operation_add_btn).performClick();
        assertEquals(expectedResult, resultTextView.getText().toString());
    }

    @Test
    public void typeOperandsAndPerformAddOperation() {
        System.out.println("typeOperandsAndPerformAddOperation");
        performOperation(R.id.operation_add_btn, "16.0", "16.0", "32.0");
    }

    @Test
    public void typeOperandsAndPerformSubOperation() {
        System.out.println("typeOperandsAndPerformSubOperation");
        performOperation(R.id.operation_sub_btn, "32.0", "16.0", "16.0");
    }

    @Test
    public void typeOperandsAndPerformDivOperation() {
        System.out.println("typeOperandsAndPerformDivOperation");
        performOperation(R.id.operation_div_btn, "128.0", "16.0", "8.0");
    }

    @Test
    public void divZeroForOperandTwoShowsError() {
        System.out.println("divZeroForOperandTwoShowsError");
        final String expectedResult = calculatorActivity.getString(R.string.computationError);
        performOperation(R.id.operation_div_btn, "128.0", "0.0", expectedResult);
    }

    @Test
    public void typeOperandsAndPerformMulOperation() {
        performOperation(R.id.operation_mul_btn, "16.0", "16.0", "256.0");
    }

    private void performOperation(int btnOperationResId, String operandOne,
                                  String operandTwo, String expectedResult) {
        et1 = calculatorActivity.findViewById(R.id.operand_one_edit_text);
        et2 = calculatorActivity.findViewById(R.id.operand_two_edit_text);
        // Type the two operands in the EditText fields
        et1.setText(operandOne);
        et2.setText(operandTwo);

        // Click on a given operation button
        calculatorActivity.findViewById(btnOperationResId).performClick();

        // Check the expected test is displayed in the Ui
        assertEquals(expectedResult, resultTextView.getText().toString());
    }
}
