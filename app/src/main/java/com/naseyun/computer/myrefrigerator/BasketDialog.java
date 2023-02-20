package com.naseyun.computer.myrefrigerator;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasketDialog extends Dialog {
    private OnDialogListener listener;
    private int ingredient_image, ingredient_count;
    private String ingredient_title, ingredient_unit, ingredient_category, expiration_date, deadline_date;
    private boolean isSelected;
    private TextView textView_ingredient_title, textView_ingredient_category, textView_basket_count;
    private ImageButton imageButton_basket_minus, imageButton_basket_plus;
    private Button button_cancel, button_confirm;

    public BasketDialog(Context context, final int position, Mybasket mybasket) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_basket_modify);

        textView_ingredient_title = findViewById(R.id.textView_ingredient_title);
        textView_ingredient_category = findViewById(R.id.textView_ingredient_category);
        textView_basket_count = findViewById(R.id.textView_basket_count);
        imageButton_basket_minus = findViewById(R.id.imageButton_basket_minus);
        imageButton_basket_plus = findViewById(R.id.imageButton_basket_plus);
        button_cancel = findViewById(R.id.button_cancel);
        button_confirm = findViewById(R.id.button_confirm);

        ingredient_image = mybasket.getIngredient_image();
        ingredient_title = mybasket.getIngredient();
        ingredient_count = mybasket.getIngredient_count();
        ingredient_unit = mybasket.getIngredient_unit();
        ingredient_category = mybasket.getIngredient_category();
        expiration_date = mybasket.getExpiration_date();
        deadline_date = mybasket.getDeadline_date();

        textView_ingredient_title.setText(ingredient_title);
        textView_ingredient_category.setText(ingredient_category);
        textView_basket_count.setText(String.valueOf(ingredient_count));

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    int new_ingredient_count = Integer.parseInt(textView_basket_count.getText().toString());
                    Mybasket new_mybasket = new Mybasket(ingredient_image, ingredient_title,
                            new_ingredient_count, ingredient_unit, ingredient_category, expiration_date, deadline_date);
                    //listener를 통해 새로운 mybasket 객체 전달
                    listener.onFinish(position, new_mybasket);
                    //다이얼로그 종료
                    dismiss();
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
    public void setDialogListener(OnDialogListener listener) {
        this.listener = listener;
    }
}
