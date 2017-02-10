package de.testgroovyyoga

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Point
import android.util.Log
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import com.facebook.yoga.YogaNode

public class Brick extends YogaNode {

    public View view
    private List<Brick> children = []

    public Brick(){
        super()
    }

    public Brick(View view){
        super()
        this.view = view
    }

    public Brick(Context context){
        super()
    }

    public appendChild(Brick child){
        ((ViewGroup)this.view).addView(child.view)
        this.addChildAt(child, this.children.size())
        this.children.add(child)
    }

    public void applyToViews(){
        def ip = this.view.layoutParams
        if(ip == null){
            ip = new RelativeLayout.LayoutParams(0,0)
            this.view.layoutParams = ip
        }
        if(ip != null){
            ip.height = this.getLayoutHeight()
            ip.width = this.getLayoutWidth()
            ip.leftMargin = (int) this.getLayoutX();
            ip.topMargin = (int) this.getLayoutY();
            Log.d("TAG", "applied ti view: "+this.view)
        } else {
            Log.d("TAG", "not applied! "+this.view)
        }
        // cascade to childviews
        this.children.each {
            it.applyToViews()
        }
    }

    public void calculateForActivity(Activity a){
        WindowManager wm = (WindowManager) a.getSystemService(Context.WINDOW_SERVICE)
        Display display = wm.getDefaultDisplay()
        Point size = new Point()
        display.getSize(size)
        int width = size.x
        int height = size.y

        final TypedArray styledAttributes = a.getTheme().obtainStyledAttributes([android.R.attr.actionBarSize] as int[])
        int mActionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        int sbh = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            sbh = a.getResources().getDimensionPixelSize(resourceId);
        }
        height = height - mActionBarSize - sbh;

        this.setWidth(width);
        this.setHeight(height);

        this.calculateLayout();
    }

}