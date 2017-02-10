package de.testgroovyyoga

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.ScrollView
import com.facebook.soloader.SoLoader
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaFlexDirection
import com.facebook.yoga.YogaJustify
import com.facebook.yoga.YogaOverflow

public class GMainActivity extends Activity {

    private Brick root

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoLoader.init(this, false);

        // create element
        root = new Brick(new RelativeLayout(this))
        root.view.backgroundColor = Color.YELLOW
        // set layout properties
        root.flexDirection = YogaFlexDirection.COLUMN
        root.alignItems = YogaAlign.STRETCH
        root.justifyContent = YogaJustify.CENTER
        root.setPadding(YogaEdge.ALL, 20)

        def redBlock = new Brick(new RelativeLayout(this))
        redBlock.view.backgroundColor = this.getColor(R.color.colorAccent)

        root.appendChild(redBlock)
        redBlock.flexDirection = YogaFlexDirection.ROW
        redBlock.flex = 1
        //redBlock.width = 400
        // redBlock.height = 300

        (1..3).each {
            def b = new Brick(new RelativeLayout(this))
            redBlock.appendChild(b)

            b.view.backgroundColor = Color.argb(100, 0xFF, 0xEE, 0xEE)
            b.flex = 1
            b.minHeight = 200
            b.setMargin(YogaEdge.ALL, 10)
        }

        def scroll = new Brick(new ScrollView(this))
        root.appendChild(scroll)
        scroll.flex = 2
        scroll.overflow = YogaOverflow.SCROLL

        def greenBlock = new Brick(new RelativeLayout(this))
        greenBlock.view.backgroundColor = Color.GREEN
        scroll.appendChild(greenBlock)

        (1..15).each {
            def cb = new CompositeBrick(this)
            greenBlock.appendChild(cb)
            cb.setMargin(YogaEdge.BOTTOM, 10)
        }

        root.calculateForActivity(this)
        root.applyToViews()
        this.setContentView(root.view, root.view.layoutParams)

    }
}