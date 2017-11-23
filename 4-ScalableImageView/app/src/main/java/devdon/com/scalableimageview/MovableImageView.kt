package devdon.com.scalableimageview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

/**
 * Created by slamdon on 2017/11/23.
 */

class MovableImageView: ImageView {

    enum class OperationMode {
        DRAG, ZOOM, NONE
    }

    private var originalX = 0f
    private var originalY = 0f
    private var originalDistance = 0.0
    private var operationMode = OperationMode.NONE
    private val MIN_ZOOM_POINTER_DISTANCE = 5.0

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        println("init image view")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.performClick()

        when(event?.actionMasked)
        {
            MotionEvent.ACTION_DOWN -> {
                this.operationMode = OperationMode.DRAG
                this.touchDown(event)
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                if (this.operationMode != OperationMode.ZOOM)  {
                    this.operationMode = OperationMode.ZOOM
                    this.touchZoomDown(event)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if(this.operationMode != OperationMode.NONE) {
                    println("move")
                    touchMove(event)
                } else {
                    println("none")
                }

            }
            MotionEvent.ACTION_POINTER_UP -> {
                this.operationMode = OperationMode.NONE
            }
            MotionEvent.ACTION_UP -> {
                this.operationMode = OperationMode.NONE
            }
        }

        return true
    }

    private fun touchDown(event: MotionEvent) {
        this.originalX = event.x
        this.originalY = event.y
    }

    private fun touchMove(event: MotionEvent) {
        val deltaX = event.x - this.originalX
        val deltaY = event.y - this.originalY

        if (this.operationMode == OperationMode.DRAG) {
            this.left += deltaX.toInt()
            this.right += deltaX.toInt()
            this.top += deltaY.toInt()
            this.bottom += deltaY.toInt()
            setFrame(this.left, this.top, this.right, this.bottom)

        } else if (this.operationMode == OperationMode.ZOOM) {
            val distance = this.getDistanceOfPointers(event)
            val deltaDistance = Math.abs(distance - this.originalDistance)
            if (deltaDistance > MIN_ZOOM_POINTER_DISTANCE) {
                this.scaleImage(distance)
                this.originalDistance = distance //!importance
            }

            this.originalDistance = distance
        }
    }

    private fun touchZoomDown(event: MotionEvent) {
        this.originalDistance = this.getDistanceOfPointers(event)
    }

    private fun scaleImage(newDistance: Double) {
        val ratio = newDistance / this.originalDistance
        if (ratio > 1) {
            val sizeX = this.width * (ratio - 1) / 2
            val sizeY = this.height * (ratio - 1) / 2

            val left = this.left - sizeX
            val right = this.right + sizeX
            val top = this.top - sizeY
            val bottom = this.bottom + sizeY

            this.setFrame(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())

        } else if (ratio < 1) {
            val sizeX = this.width * (1- ratio) / 2
            val sizeY = this.height * (1 - ratio) / 2

            val left = this.left + sizeX
            val right = this.right - sizeX
            val top = this.top + sizeY
            val bottom = this.bottom - sizeY

            setFrame(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())

        }
    }

    private fun getDistanceOfPointers(event: MotionEvent): Double  {
        val deltaX = event.getX(0) - event.getX(1)
        val deltaY = event.getY(0) - event.getY(1)
        return Math.sqrt((deltaX * deltaX + deltaY * deltaY).toDouble())
    }
}