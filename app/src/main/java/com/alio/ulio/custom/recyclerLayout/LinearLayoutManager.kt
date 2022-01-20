package com.alio.ulio.custom.recyclerLayout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.children
import com.alio.ulio.R

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-20
 * @desc
 */
class LinearLayoutManager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, style: Int = 0) :
    ConstraintLayout(context, attrs, style) {

    private lateinit var backgroundContainer: View
    private lateinit var backgroundParams: LayoutParams
    private lateinit var foregroundContainer: View
    private lateinit var foregroundParams: LayoutParams

    private var onClickListener: OnClickListener? = null
    private var onSwipeListener: OnSwipeListener? = null

    private var positionOnActionDown = 0.0f
    private var distanceAfterMove = 0.0f
    private var calculatedNewMargin = 0
    private var backgroundContainerWidth = 0
    private var halfBackgroundContainerWidth = 0

    private var isExpanded = false
    private var isMenuOnTheLeft = true
    private var dynamicMenuWidth = true
    private var measureBackgroundContainerWidth = true

    init {
        attrs?.let { }
    }

    @SuppressLint("Recycle")
    private fun getAttributes(context: Context, attributeSet: AttributeSet) {
        context.obtainStyledAttributes(attributeSet, R.styleable.SwipeMenuLayout)?.let {
            isMenuOnTheLeft = false
            it.getInt(R.styleable.SwipeMenuLayout_menuSide, ATTR_LEFT_INT_VALUE)
            dynamicMenuWidth = true
            it.getBoolean(R.styleable.SwipeMenuLayout_dynamicMenuWidth, ATTR_DYNAMIC_MENU_WIDTH_DEFAULT)
            it.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != NUMBER_OF_REQUIRED_CHILDREN) throw Throwable(
            INCORRECT_NUMBER_OF_CHILDREN_MESSAGE)

        backgroundContainer = getChildAt(FOREGROUND_INDEX)
        foregroundContainer = getChildAt(BACKGROUND_INDEX)

        if (id == EMPTY_ID) throw Throwable(INCORRECT_MAIN_CONTAINER_ID_MESSAGE)
        if (backgroundContainer.id == EMPTY_ID) throw Throwable(INCORRECT_MAIN_CONTAINER_ID_MESSAGE)
        if (foregroundContainer.id == EMPTY_ID) throw Throwable(INCORRECT_FOREGROUND_CONTAINER_ID_MESSAGE)

        foregroundParams = foregroundContainer.layoutParams as LayoutParams
        backgroundParams = backgroundContainer.layoutParams as LayoutParams

        if (backgroundParams.height != REQUIRED_BACKGROUND_CONTAINER_HEIGHT) throw Throwable(
            INCORRECT_BACKGROUND_CONTAINER_HEIGHT_MESSAGE
        )

        if (!dynamicMenuWidth) {
            backgroundContainerWidth = backgroundContainer.layoutParams.width
            halfBackgroundContainerWidth = backgroundContainerWidth / 2
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (dynamicMenuWidth && measureBackgroundContainerWidth) {
            measureBackgroundContainerWidth = false
            measureBackgroundContainerWidth()
        }
    }

    private fun measureBackgroundContainerWidth() {
        backgroundContainerWidth = backgroundContainer.measuredWidth
        halfBackgroundContainerWidth = backgroundContainerWidth / 2
    }

    fun apply(isExpanded : Boolean) {
        this.isExpanded = isExpanded

        if (dynamicMenuWidth) {
            post {
                measureBackgroundContainerWidth()
                refreshForegroundContainerSwipeStatus()
            }
        }
    }

    private fun refreshForegroundContainerSwipeStatus() {
        //if (isExpanded) expand()
    }

    private fun enableBackgroundContainerClickEventsOnExpand() {
        (backgroundContainer as? ViewGroup)?.let { viewGroup ->
            viewGroup.children.forEach {
                it.isClickable = isExpanded
            }
        }
    }

    private fun setConstraints() {
        ConstraintSet().let {
            //val sideConstraintSet = if
        }
    }



    override fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    companion object {
        var isSwiping = false

        private const val ATTR_DYNAMIC_MENU_WIDTH_DEFAULT = true
        private const val ATTR_LEFT_INT_VALUE = 1
        private const val MIN_DISTANCE_TO_SWIPE = 10

        private const val NUMBER_OF_REQUIRED_CHILDREN = 2
        private const val FOREGROUND_INDEX = 0
        private const val BACKGROUND_INDEX = 1
        private const val INCORRECT_NUMBER_OF_CHILDREN_MESSAGE =
            "Incorrect number of children, required two: background container and foreground container"

        private const val EMPTY_ID = -1
        private const val INCORRECT_MAIN_CONTAINER_ID_MESSAGE =
            "Incorrect ID, main container (SimpleSwipeMenuLayout) should have ID to correct attach constraints"
        private const val INCORRECT_FOREGROUND_CONTAINER_ID_MESSAGE =
            "Incorrect ID, foreground container should have ID to correct attach constraints"
        private const val INCORRECT_BACKGROUND_CONTAINER_ID_MESSAGE =
            "Incorrect ID, background container should have ID to correct attach constraints"

        private const val REQUIRED_BACKGROUND_CONTAINER_HEIGHT = 0
        private const val INCORRECT_BACKGROUND_CONTAINER_HEIGHT_MESSAGE =
            "Incorrect height, background container should have 0dp height"
    }

}