package com.onfengma.androidprac.views;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import com.onfengma.androidprac.utils.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by chuyifeng on 2015/10/12.
 */
public class VideoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public static final int CAMERA_NULL = -1;
    public static final int CAMERA_READY = 0;
    public static final int CAMERA_PREPARING = 1;
    public static final int RECORD_STOPPED = 2;
    public static final int RECODING = 3;

    private Camera mCamera;
    private SurfaceHolder mHolder;
    private MediaRecorder mediaRecorder;

    private VideoCallbacks mVideoCallbacks;
    private Size mNiceSize;

    private int mCurrentCameraFace;
    private CameraInfo mCurrentCameraInfo;

    private int mRecordStatus = CAMERA_NULL;
    private String mCurrentVideoFileName;

    public interface VideoCallbacks {
        void startPreview();
        void onCameraReady(Size size);
        void onPreviewReady();
    }

    public VideoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);

        mCurrentCameraFace = CameraInfo.CAMERA_FACING_BACK;
    }

    public void setVideoCallbacks(VideoCallbacks mVideoCallbacks) {
        this.mVideoCallbacks = mVideoCallbacks;
    }

    public void switchCamera() {
        if (mCamera == null) {
            return;
        }
        release();

        mCurrentCameraFace = (mCurrentCameraFace + 1) % Camera.getNumberOfCameras();
        startPreviewing();
    }

    public void release() {
        if (isRecording()) {
            cancelRecoding();
        }
        mRecordStatus = CAMERA_NULL;
        if (mCamera == null) {
            return;
        }
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    public void startPreviewing() {
        if (!isInvalid()) {
            Logger.i("can not previewing yet.");
            return;
        }

        if (mVideoCallbacks != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    mVideoCallbacks.startPreview();
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                    mRecordStatus = CAMERA_PREPARING;
                    try {
                        prepareCamera();
                        if (mVideoCallbacks != null) {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    mVideoCallbacks.onCameraReady(mNiceSize);
                                }
                            });
                        }
                        mRecordStatus = CAMERA_READY;
                        mCamera.startPreview();
                        if (mVideoCallbacks != null) {
                            postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mVideoCallbacks.onPreviewReady();
                                }
                            }, 800);
                        }
                    } catch (IOException e) {
                        Logger.i("prepareCamera error:" + e);
                        mRecordStatus = CAMERA_NULL;
                    } catch (NullPointerException e) {
                        Logger.i("fetch camera error:" + e);
                        mRecordStatus = CAMERA_NULL;
                    }
            }
        }).start();
    }

    public void startRecording() {
        if (!isReady()) {
            Logger.i("camera is not ready");
        }

        try {
            prepareRecoding();
            mediaRecorder.start();
            mRecordStatus = RECODING;
        } catch (IOException e) {
            Logger.i("prepare recoding error!" + e);
        } catch (IllegalStateException e) {
            Logger.i("start recoding error!:" + e);
        }
    }

    public String stopRecoding() {
        if (!isRecording()) {
            Logger.i("not recording yet!");
            return null;
        }

        mRecordStatus = RECORD_STOPPED;
        if (mediaRecorder == null || mCamera == null) {
            return null;
        }
        try {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mCamera.lock();
        } catch (Exception e) {
            Logger.i("stop media recorder failed");
        }
        return mCurrentVideoFileName;
    }

    public void cancelRecoding() {
        stopRecoding();

        File file = new File(mCurrentVideoFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public boolean isInvalid() {
        return mRecordStatus == CAMERA_NULL;
    }

    public boolean isReady() {
        return mRecordStatus == CAMERA_READY;
    }

    public boolean isRecording() {
        return mRecordStatus == RECODING;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {}

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void resetCamera(Camera camera) throws IOException {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }
        mCamera = camera;
        mCamera.setPreviewDisplay(mHolder);
    }

    private boolean checkCameraHardware() {
        if (getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private void prepareCamera() throws IOException {
        release();

        Camera camera = getCameraByFace();
        if (camera == null) {
            throw new NullPointerException("Get camera by face error!");
        }

        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
        mNiceSize = sizeList.get(sizeList.size() / 2);
        camera.setDisplayOrientation(90);
        parameters.setPreviewSize(mNiceSize.width, mNiceSize.height);
        camera.setParameters(parameters);
        resetCamera(camera);

        post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams lp = getLayoutParams();
                lp.width = getResources().getDisplayMetrics().widthPixels;
                lp.height = getResources().getDisplayMetrics().widthPixels * mNiceSize.width / mNiceSize.height;
                setLayoutParams(lp);
            }
        });
    }

    private void prepareRecoding() throws IOException {
        mCamera.unlock();

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setCamera(mCamera);

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
        mediaRecorder.setVideoEncodingBitRate(400000);
        mediaRecorder.setVideoSize(352 , 288);

        mediaRecorder.setOutputFile(generateVideoFileName());
        mediaRecorder.setPreviewDisplay(mHolder.getSurface());

        //Tags the video with proper angle in order to tell the phone how to display it
        int rotation;
        int mOrientation = ((Activity)getContext()).getWindowManager().getDefaultDisplay().getRotation();
        if (mCurrentCameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
            rotation = (mCurrentCameraInfo.orientation - mOrientation + 360) % 360;
        } else {  // back-facing camera
            rotation = (mCurrentCameraInfo.orientation + mOrientation) % 360;
        }
        mediaRecorder.setOrientationHint(rotation);

        mediaRecorder.prepare();
    }

    private String generateVideoFileName() {
        mCurrentVideoFileName = getContext().getExternalFilesDir("videos") + File.separator + System.currentTimeMillis() + ".mp4";
        return mCurrentVideoFileName;
    }

    private Camera getCameraByFace() {
        if (!checkCameraHardware()) {
            return null;
        }
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);

            // get request face camera or last camera
            if (cameraInfo.facing == mCurrentCameraFace) {
                mCurrentCameraInfo = cameraInfo;
                Camera camera = Camera.open(i);
                return camera;
            }
        }

        return null;
    }
}
