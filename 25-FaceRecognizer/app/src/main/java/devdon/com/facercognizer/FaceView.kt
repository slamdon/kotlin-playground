package devdon.com.facercognizer

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.media.FaceDetector
import android.media.ThumbnailUtils
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 * Created by slamdon on 2017/12/14.
 */


class FaceView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private var paint: Paint
    private var bitmap: Bitmap
    private var canvas: Canvas

    private val maxFaceNumbers = 25
    private var numberOfFaceDetected = 0
    private var faces = arrayOfNulls<FaceDetector.Face>(25)

    init {
        // bitmap
        val width = Resources.getSystem().displayMetrics.widthPixels
        bitmap = Bitmap.createBitmap(width, 800, Bitmap.Config.RGB_565)

        // Canvas
        canvas = Canvas(bitmap)
        canvas.drawColor(Color.GRAY)

        // Paint
        paint = Paint()
        paint.color = Color.YELLOW
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.textSize = 50f

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, 0f, 0f , null)

        for(i in 0..numberOfFaceDetected){
            val face = faces[i]
            if (face != null){
                val point = PointF()
                face.getMidPoint(point)

                val eyesDistance = face.eyesDistance()
                canvas.drawRect(
                        (point.x - eyesDistance).toFloat(),
                        (point.y - eyesDistance / 2).toFloat(),
                        (point.x + eyesDistance).toFloat(),
                        (point.y + eyesDistance * 3 / 2).toFloat(),
                        paint)
            }

        }

    }

    fun setupWithImage(uri: Uri) {
        Log.e("FaceView","Setup With Uri $uri")

        try {
            val resolver = context.contentResolver

            // must be RGB_565
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565

            val image = BitmapFactory.decodeStream(resolver.openInputStream(uri), null, options)

            // scale the bitmap
            bitmap = ThumbnailUtils.extractThumbnail(image, width, height)

        } catch (e: Exception) {
            Log.e("setup with image","failed")
        }
    }



    fun detectFace() {
        // init FaceDetector
        val faceDetector = FaceDetector(bitmap.width, bitmap.height, maxFaceNumbers)
        faces = arrayOfNulls<FaceDetector.Face>(maxFaceNumbers)

        numberOfFaceDetected = faceDetector.findFaces(bitmap, faces)

        Toast.makeText(context,"Detected $numberOfFaceDetected faces",Toast.LENGTH_SHORT).show()

        // call onDraw
        invalidate()
    }


}