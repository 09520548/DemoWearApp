package com.gabrielbl.blwatchfaces;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.text.format.Time;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by baolanlequang on 1/12/15.
 */
public class AnalogWatchFaceService extends CanvasWatchFaceService {
    private static final String TAG = "AnalogWatchFaceService";

    /**
     * Update rate in milliseconds for interactive mode. We update once a second to advance the
     * second hand.
     */
    private static final long INTERACTIVE_UPDATE_RATE_MS = TimeUnit.SECONDS.toMillis(1);

    @Override
    public Engine onCreateEngine() {
        /* provide your watch face implementation */
        return new Engine();
    }

    /* implement service callback methods */
    private class Engine extends CanvasWatchFaceService.Engine {

        static final int MSG_UPDATE_TIME = 0;

        Paint mHourPaint;
        Paint mMinutePaint;
        Paint mSecondPaint;
        Paint mTickPaint;
        boolean mMute;
        Time mTime;


        /** Handler to update the time once a second in interactive mode. */
        final Handler mUpdateTimeHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case MSG_UPDATE_TIME:
                        if (Log.isLoggable(TAG, Log.VERBOSE)) {
                            Log.v(TAG, "updating time");
                        }
                        invalidate();
                        if (shouldTimerBeRunning()) {
                            long timeMs = System.currentTimeMillis();
                            long delayMs = INTERACTIVE_UPDATE_RATE_MS
                                    - (timeMs % INTERACTIVE_UPDATE_RATE_MS);
                            mUpdateTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, delayMs);
                        }
                        break;
                }
            }
        };

        final BroadcastReceiver mTimeZoneReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mTime.clear(intent.getStringExtra("time-zone"));
                mTime.setToNow();
            }
        };
        boolean mRegisteredTimeZoneReceiver = false;

        /**
         * Returns whether the {@link #mUpdateTimeHandler} timer should be running. The timer should
         * only run when we're visible and in interactive mode.
         */
        private boolean shouldTimerBeRunning() {
            return isVisible() && !isInAmbientMode();
        }

        /**
         * Whether the display supports fewer bits for each color in ambient mode. When true, we
         * disable anti-aliasing in ambient mode.
         */
        boolean mLowBitAmbient;

        Bitmap mBackgroundBitmap;
        Bitmap mBackgroundScaledBitmap;
        Bitmap num1Bitmap;
        Bitmap num2Bitmap;
        Bitmap num3Bitmap;
        Bitmap num4Bitmap;
        Bitmap num5Bitmap;
        Bitmap num6Bitmap;
        Bitmap num7Bitmap;
        Bitmap num8Bitmap;
        Bitmap num9Bitmap;
        Bitmap num10Bitmap;
        Bitmap num11Bitmap;
        Bitmap num12Bitmap;

        @Override
        public void onCreate(SurfaceHolder holder) {
            /* initialize your watch face */
            Log.d(TAG, "onCreate");
            super.onCreate(holder);

            setWatchFaceStyle(new WatchFaceStyle.Builder(AnalogWatchFaceService.this)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setShowSystemUiTime(false)
                    .build());

            Resources resources = AnalogWatchFaceService.this.getResources();
            Drawable backgroundDrawable = resources.getDrawable(R.drawable.bg);
            mBackgroundBitmap = ((BitmapDrawable)backgroundDrawable).getBitmap();

            Drawable num1Drawable = resources.getDrawable(R.drawable.img_1);
            Drawable num2Drawable = resources.getDrawable(R.drawable.img_2);
            Drawable num3Drawable = resources.getDrawable(R.drawable.img_3);
            Drawable num4Drawable = resources.getDrawable(R.drawable.img_4);
            Drawable num5Drawable = resources.getDrawable(R.drawable.img_5);
            Drawable num6Drawable = resources.getDrawable(R.drawable.img_6);
            Drawable num7Drawable = resources.getDrawable(R.drawable.img_7);
            Drawable num8Drawable = resources.getDrawable(R.drawable.img_8);
            Drawable num9Drawable = resources.getDrawable(R.drawable.img_9);
            Drawable num10Drawable = resources.getDrawable(R.drawable.img_10);
            Drawable num11Drawable = resources.getDrawable(R.drawable.img_11);
            Drawable num12Drawable = resources.getDrawable(R.drawable.img_12);

            num1Bitmap = ((BitmapDrawable)num1Drawable).getBitmap();
            num2Bitmap = ((BitmapDrawable)num2Drawable).getBitmap();
            num3Bitmap = ((BitmapDrawable)num3Drawable).getBitmap();
            num4Bitmap = ((BitmapDrawable)num4Drawable).getBitmap();
            num5Bitmap = ((BitmapDrawable)num5Drawable).getBitmap();
            num6Bitmap = ((BitmapDrawable)num6Drawable).getBitmap();
            num7Bitmap = ((BitmapDrawable)num7Drawable).getBitmap();
            num8Bitmap = ((BitmapDrawable)num8Drawable).getBitmap();
            num9Bitmap = ((BitmapDrawable)num9Drawable).getBitmap();
            num10Bitmap = ((BitmapDrawable)num10Drawable).getBitmap();
            num11Bitmap = ((BitmapDrawable)num11Drawable).getBitmap();
            num12Bitmap = ((BitmapDrawable)num12Drawable).getBitmap();

//            mHourPaint = new Paint();
//            mHourPaint.setARGB(255, 0, 0, 0);
//            mHourPaint.setStrokeWidth(7.f);
//            mHourPaint.setAntiAlias(true);
//            mHourPaint.setStrokeCap(Paint.Cap.ROUND);
//
//            mMinutePaint = new Paint();
//            mMinutePaint.setARGB(255, 0, 0, 0);
//            mMinutePaint.setStrokeWidth(5.f);
//            mMinutePaint.setAntiAlias(true);
//            mMinutePaint.setStrokeCap(Paint.Cap.ROUND);
//
//            mSecondPaint = new Paint();
//            mSecondPaint.setARGB(255, 255, 0, 0);
//            mSecondPaint.setStrokeWidth(3.f);
//            mSecondPaint.setAntiAlias(true);
//            mSecondPaint.setStrokeCap(Paint.Cap.ROUND);
//
//            mTickPaint = new Paint();
//            mTickPaint.setARGB(204, 255, 255, 255);
//            mTickPaint.setStrokeWidth(3.f);
//            mTickPaint.setAntiAlias(true);

            mTime = new Time();

        }

        @Override
        public void onDestroy() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            super.onDestroy();
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            /* get device features (burn-in, low-bit ambient) */
            super.onPropertiesChanged(properties);
            mLowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "onPropertiesChanged: low-bit ambient = " + mLowBitAmbient);
            }
        }

        @Override
        public void onTimeTick() {
            /* the time changed */
            super.onTimeTick();
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "onTimeTick: ambient = " + isInAmbientMode());
            }
            invalidate();
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            /* the wearable switched between modes */
            super.onAmbientModeChanged(inAmbientMode);
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "onAmbientModeChanged: " + inAmbientMode);
            }
//            if (mLowBitAmbient) {
//                boolean antialias = !inAmbientMode;
//                mHourPaint.setAntiAlias(antialias);
//                mMinutePaint.setAntiAlias(antialias);
//                mSecondPaint.setAntiAlias(antialias);
//                mTickPaint.setAntiAlias(antialias);
//            }

            invalidate();

            // Whether the timer should be running depends on whether we're in ambient mode (as well
            // as whether we're visible), so we may need to start or stop the timer.
            updateTimer();
        }

        @Override
        public void onInterruptionFilterChanged(int interruptionFilter) {
            super.onInterruptionFilterChanged(interruptionFilter);
            boolean inMuteMode = (interruptionFilter == WatchFaceService.INTERRUPTION_FILTER_NONE);
//            if (mMute != inMuteMode) {
//                mMute = inMuteMode;
//                mHourPaint.setAlpha(inMuteMode ? 100 : 255);
//                mMinutePaint.setAlpha(inMuteMode ? 100 : 255);
//                mSecondPaint.setAlpha(inMuteMode ? 80 : 255);
//                invalidate();
//            }
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            /* draw your watch face */
            mTime.setToNow();

            int width = bounds.width();
            int height = bounds.height();
            int sizeofset = width/7;

            // Draw the background, scaled to fit.
            if (mBackgroundScaledBitmap == null
                    || mBackgroundScaledBitmap.getWidth() != width
                    || mBackgroundScaledBitmap.getHeight() != height) {
                mBackgroundScaledBitmap = Bitmap.createScaledBitmap(mBackgroundBitmap, width, height, true);
            }
            canvas.drawBitmap(mBackgroundScaledBitmap, 0, 0, null);

            canvas.drawBitmap(num1Bitmap, sizeofset*5-sizeofset/2-num1Bitmap.getWidth()/2, sizeofset*2-sizeofset/2-num1Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num2Bitmap, sizeofset*6-sizeofset/2-num2Bitmap.getWidth()/2, sizeofset*3-sizeofset/2-num2Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num3Bitmap, sizeofset*7-sizeofset/2-num3Bitmap.getWidth()/2, height/2-num3Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num4Bitmap, sizeofset*6-sizeofset/2-num4Bitmap.getWidth()/2, sizeofset-sizeofset/2, null);
            canvas.drawBitmap(num5Bitmap, sizeofset*5-sizeofset/2-num5Bitmap.getWidth()/2, sizeofset-sizeofset/2, null);
            canvas.drawBitmap(num6Bitmap, sizeofset*4-sizeofset/2-num12Bitmap.getWidth()/2, height-15-num6Bitmap.getHeight(), null);
            canvas.drawBitmap(num7Bitmap, sizeofset*3-sizeofset/2-num7Bitmap.getWidth()/2, height/2-num9Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num8Bitmap, sizeofset*2-sizeofset/2-num8Bitmap.getWidth()/2, height/2-num9Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num9Bitmap, sizeofset/2-num9Bitmap.getWidth()/2, sizeofset*4-sizeofset/2-num9Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num10Bitmap, sizeofset*2-sizeofset/2-num10Bitmap.getWidth()/2, sizeofset*3-sizeofset/2-num10Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num11Bitmap, sizeofset*3-sizeofset/2-num11Bitmap.getWidth()/2, sizeofset*2-sizeofset/2-num11Bitmap.getHeight()/2, null);
            canvas.drawBitmap(num12Bitmap, sizeofset*4-sizeofset/2-num12Bitmap.getWidth()/2, sizeofset/2-num12Bitmap.getHeight()/2, null);

            // Find the center. Ignore the window insets so that, on round watches with a
            // "chin", the watch face is centered on the entire screen, not just the usable
            // portion.
            float centerX = width / 2f;
            float centerY = height / 2f;

            // Draw the ticks.
//            float innerTickRadius = centerX - 10;
//            float outerTickRadius = centerX;
//            for (int tickIndex = 0; tickIndex < 12; tickIndex++) {
//                float tickRot = (float) (tickIndex * Math.PI * 2 / 12);
//                float innerX = (float) Math.sin(tickRot) * innerTickRadius;
//                float innerY = (float) -Math.cos(tickRot) * innerTickRadius;
//                float outerX = (float) Math.sin(tickRot) * outerTickRadius;
//                float outerY = (float) -Math.cos(tickRot) * outerTickRadius;
//                canvas.drawLine(centerX + innerX, centerY + innerY,
//                        centerX + outerX, centerY + outerY, mTickPaint);
//            }

//            float secRot = mTime.second / 30f * (float) Math.PI;
//            int minutes = mTime.minute;
//            float minRot = minutes / 30f * (float) Math.PI;
//            float hrRot = ((mTime.hour + (minutes / 60f)) / 6f ) * (float) Math.PI;
//
//            float secLength = centerX - 20;
//            float minLength = centerX - 40;
//            float hrLength = centerX - 80;
//
//            if (!isInAmbientMode()) {
//                float secX = (float) Math.sin(secRot) * secLength;
//                float secY = (float) -Math.cos(secRot) * secLength;
//                canvas.drawLine(centerX, centerY, centerX + secX, centerY + secY, mSecondPaint);
//            }
//
//            float minX = (float) Math.sin(minRot) * minLength;
//            float minY = (float) -Math.cos(minRot) * minLength;
//            canvas.drawLine(centerX, centerY, centerX + minX, centerY + minY, mMinutePaint);
//
//            float hrX = (float) Math.sin(hrRot) * hrLength;
//            float hrY = (float) -Math.cos(hrRot) * hrLength;
//            canvas.drawLine(centerX, centerY, centerX + hrX, centerY + hrY, mHourPaint);

        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            /* the watch face became visible or invisible */
            super.onVisibilityChanged(visible);

            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "onVisibilityChanged: " + visible);
            }

            if (visible) {
                registerReceiver();

                // Update time zone in case it changed while we weren't visible.
                mTime.clear(TimeZone.getDefault().getID());
                mTime.setToNow();
            } else {
                unregisterReceiver();
            }

            // Whether the timer should be running depends on whether we're visible (as well as
            // whether we're in ambient mode), so we may need to start or stop the timer.
            updateTimer();
        }

        private void registerReceiver() {
            if (mRegisteredTimeZoneReceiver) {
                return;
            }
            mRegisteredTimeZoneReceiver = true;
            IntentFilter filter = new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED);
            AnalogWatchFaceService.this.registerReceiver(mTimeZoneReceiver, filter);
        }

        private void unregisterReceiver() {
            if (!mRegisteredTimeZoneReceiver) {
                return;
            }
            mRegisteredTimeZoneReceiver = false;
            AnalogWatchFaceService.this.unregisterReceiver(mTimeZoneReceiver);
        }

        /**
         * Starts the {@link #mUpdateTimeHandler} timer if it should be running and isn't currently
         * or stops it if it shouldn't be running but currently is.
         */
        private void updateTimer() {
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "updateTimer");
            }
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            if (shouldTimerBeRunning()) {
                mUpdateTimeHandler.sendEmptyMessage(MSG_UPDATE_TIME);
            }
        }
    }


}
