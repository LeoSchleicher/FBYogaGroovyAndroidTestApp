package de.testgroovyyoga

import android.content.Context
import android.graphics.Color
import android.widget.RelativeLayout
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaFlexDirection

public class CompositeBrick extends Brick {

    private Brick left
    private Brick right

    public CompositeBrick(Context context) {
        super(context);
        this.createUI(context)
    }


    private createUI(Context context){
        this.view = new RelativeLayout(context)
        this.left = new Brick(new RelativeLayout(context))
        this.right = new Brick(new RelativeLayout(context))

        this.appendChild(this.left)
        this.appendChild(this.right)

        this.right.view.backgroundColor = Color.WHITE
        this.left.view.backgroundColor = Color.LTGRAY

        this.left.flex = 1
        this.right.flex = 4

        this.minHeight = 100
        this.flexDirection = YogaFlexDirection.ROW
        this.alignItems = YogaAlign.STRETCH

    }

}