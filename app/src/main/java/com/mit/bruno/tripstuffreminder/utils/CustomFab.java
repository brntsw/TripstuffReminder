package com.mit.bruno.tripstuffreminder.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.mit.bruno.tripstuffreminder.R;

/**
 * Created by techresult on 20/02/2015.
 */
public class CustomFab extends ImageButton {

    private static final int RAD = 56;
    private Context context;
    private int bgColor;
    private int bgColorPressed;

    public CustomFab(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    public CustomFab(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public CustomFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private Drawable createButton(int color){
        OvalShape oShape = new OvalShape();
        ShapeDrawable sd = new ShapeDrawable(oShape);
        setWillNotDraw(false);
        sd.getPaint().setColor(color);

        OvalShape oShape1 = new OvalShape();
        ShapeDrawable sd1 = new ShapeDrawable(oShape);

        sd1.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {

                return new LinearGradient(0,0,0,height,
                        new int[]{
                            Color.WHITE,
                            Color.GRAY,
                            Color.DKGRAY,
                            Color.BLACK
                        }, null, Shader.TileMode.REPEAT);
            }
        });

        LayerDrawable ld = new LayerDrawable(new Drawable[]{sd1, sd});
        //ld.setLayerInset(0,5,5,0,0);
        ld.setLayerInset(1,0,0,4,3);

        return ld;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(AttributeSet attrSet){
        Resources.Theme theme = context.getTheme();
        TypedArray arr = theme.obtainStyledAttributes(attrSet, R.styleable.FAB, 0, 0);
        try{
            setBgColor(arr.getColor(R.styleable.FAB_bg_color, Color.parseColor("#0198E1")));
            setBgColorPressed(arr.getColor(R.styleable.FAB_bg_color_pressed, Color.GRAY));
            StateListDrawable sld = new StateListDrawable();

            sld.addState(new int[]{android.R.attr.state_pressed}, createButton(bgColorPressed));
            sld.addState(new int[]{}, createButton(bgColor));
            setBackground(sld);
        }
        catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            arr.recycle();
        }
    }

    public void setBgColor(int color){
        this.bgColor = color;
    }

    public void setBgColorPressed(int color){
        this.bgColorPressed = color;
    }
}
