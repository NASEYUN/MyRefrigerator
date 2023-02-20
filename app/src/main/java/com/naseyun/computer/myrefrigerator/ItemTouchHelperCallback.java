package com.naseyun.computer.myrefrigerator;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;

enum ButtonsState{
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE
}

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperListener listener;
    private boolean swipeBack = false; //손을 뗐는지 확인하기 위함
    private ButtonsState buttonsShowedState = ButtonsState.GONE; //현재 버튼 상태
    private static final float buttonWidth = 115;
    private RectF buttonInstance = null;
    private RecyclerView.ViewHolder currentItemViewHolder = null;

    public ItemTouchHelperCallback(ItemTouchHelperListener listener) {
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag_flags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipe_flags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        return makeMovementFlags(drag_flags, swipe_flags);
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemSwipe(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if(buttonsShowedState != ButtonsState.GONE) {
                if (buttonsShowedState == ButtonsState.LEFT_VISIBLE)
                    dX = Math.max(dX, buttonWidth);
                if (buttonsShowedState == ButtonsState.RIGHT_VISIBLE)
                    dX = Math.min(dX, -buttonWidth);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            } else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            if(buttonsShowedState == ButtonsState.GONE) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
        currentItemViewHolder = viewHolder;
        drawButtons(c, currentItemViewHolder);
    }

    //swipe 시, 노출되는 버튼 그려주는 함수
    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
        float buttonWidthWithOutPadding = buttonWidth - 20;
        float corners = 5;

        View itemView = viewHolder.itemView;
        Paint p = new Paint();

        buttonInstance = null;

//        RectF leftButton = new RectF(itemView.getRight() - buttonWidthWithOutPadding -70, itemView.getTop() + 10, itemView.getRight() -70, itemView.getBottom() - 10);
//        p.setColor(Color.BLUE);
//        c.drawRoundRect(leftButton, corners, corners, p);
//        drawText("수정", c, leftButton, p);

        //오른쪽으로 스와이프할 경우
        if(buttonsShowedState == ButtonsState.LEFT_VISIBLE) {
            RectF leftButton = new RectF(itemView.getLeft() + 10, itemView.getTop() + 10, itemView.getLeft() + buttonWidthWithOutPadding, itemView.getBottom() - 10);
            p.setColor(Color.BLUE);
            c.drawRoundRect(leftButton, corners, corners, p);
            drawText("수정", c, leftButton, p);
            buttonInstance = leftButton;
        }else if(buttonsShowedState == ButtonsState.RIGHT_VISIBLE) { // 왼쪽으로 스와이프할 경우
            RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithOutPadding, itemView.getTop() + 10, itemView.getRight() - 10, itemView.getBottom() - 10);
            p.setColor(Color.RED);
            c.drawRoundRect(rightButton, corners, corners, p);
            drawText("삭제", c, rightButton, p);
            buttonInstance = rightButton;
        }
    }

    //노출되는 버튼에 텍스트 그려주는 함수
    private void drawText(String text, Canvas c, RectF button, Paint p) {
        float textSize = 40;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);

        float textWidth = p.measureText(text);
        c.drawText(text, button.centerX() - (textWidth/2), button.centerY() + (textSize/2), p);
    }

    //SWIPE 종료 시, 아이템이 다시 돌아오도록 설정
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if(swipeBack) {
            //swipeBack = buttonsShowedState != ButtonsState.GONE;
            swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    //손을 뗐는지 확인하기 위한 함수
    private void setTouchListener(final Canvas c, final RecyclerView recyclerView,
                                  final RecyclerView.ViewHolder viewHolder, final float dX, final float dY,
                                  final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                swipeBack = motionEvent.getAction() == MotionEvent.ACTION_CANCEL || motionEvent.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (dX < -buttonWidth)
                        buttonsShowedState = ButtonsState.RIGHT_VISIBLE;
                    else if (dX > buttonWidth)
                        buttonsShowedState = ButtonsState.LEFT_VISIBLE;

                    if (buttonsShowedState != ButtonsState.GONE) {
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    private void setOnTouchListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder,
                                  final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (dX < -buttonWidth)
                        buttonsShowedState = ButtonsState.RIGHT_VISIBLE;
                    else if (dX > buttonWidth)
                        buttonsShowedState = ButtonsState.LEFT_VISIBLE;

                    if (buttonsShowedState != ButtonsState.GONE) {
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder,
                                      final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }

    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder,
                                    final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    ItemTouchHelperCallback.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    setItemClickable(recyclerView, true);
                    swipeBack = false;
                    //buttonsShowedState = ButtonsState.GONE;

                    //터치 시, 동작 추가
                    if (buttonsShowedState != null && buttonInstance != null && buttonInstance.contains(motionEvent.getX(), motionEvent.getY())) {
                        if (buttonsShowedState == ButtonsState.LEFT_VISIBLE) {
                            listener.onLeftClick(viewHolder.getAdapterPosition(), viewHolder);
                        } else if (buttonsShowedState == ButtonsState.RIGHT_VISIBLE) {
                            listener.onRightClick(viewHolder.getAdapterPosition(), viewHolder);
                        }
                    }
                    buttonsShowedState = ButtonsState.GONE;
                    currentItemViewHolder = null;
                }
                return false;
            }
        });
    }

    private void setItemClickable(RecyclerView recyclerView, boolean isClickable) {
        for(int i=0; i<recyclerView.getChildCount(); i++) {
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }

}
