package com.example.soundpoolnative;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private MediaPlayer mediaPlayerBg;
	private MediaPlayer mediaPlayerTeXiao1;
	private MediaPlayer mediaPlayerTeXiao2;
	private MediaPlayer mediaPlayerTeXiao3;
	private SoundPool soundPool;
	private AudioManager am; 
	private int maxVol;
	private int loadId1,loadId2,loadId3;
	private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        //获得当前的上下文
        context=getApplicationContext();
        
        mediaPlayerBg=MediaPlayer.create(context, R.raw.door);
        //背景音乐循环播放
        mediaPlayerBg.setLooping(true);
        mediaPlayerTeXiao1=MediaPlayer.create(context, R.raw.door);
        mediaPlayerTeXiao2=MediaPlayer.create(context, R.raw.door);
        mediaPlayerTeXiao3=MediaPlayer.create(context, R.raw.door);
        //SoundPool的初始化
        /*
         * 第一个参数是：同时播放特效的数量
         * 第二个参数是：音乐播放的音频流
         * 第三个参数是：音乐的质量
         */
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
        loadId1=soundPool.load(context, R.raw.door, 1);
        loadId2=soundPool.load(context, R.raw.door, 1);
        loadId3=soundPool.load(context, R.raw.door, 1);
        /*
		 * Android OS中，如果你去按手机上的调节音量的按钮，会分两种情况，
		 * 一种是调整手机本身的铃声音量，一种是调整游戏，软件，音乐播放的音量
		 * 当我们在游戏中的时候 ，总是想调整游戏的音量而不是手机的铃声音量，
		 * 可是烦人的问题又来了，我在开发中发现，只有游戏中有声音在播放的时候
		 * ，你才能去调整游戏的音量，否则就是手机的音量，有没有办法让手机只要是
		 * 在运行游戏的状态就只调整游戏的音量呢？试试下面这段代码吧!
		 * setVolumeControlStream(AudioManager.STREAM_MUSIC);
		 * 设定调整音量为媒体音量,当暂停播放的时候调整音量就不会再默认调整铃声音量了
		 */
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        // 获取音频服务然后强转成一个音频管理器,后面方便用来控制音量大小用
     	am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        // 获取最大音量值（15最大! .不是100！）
     	maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }
    /**
     * 播放背景音乐
     * @param view
     */
    public void startBgMusic(View view){
    	if(mediaPlayerBg != null) {
    		  mediaPlayerBg.stop();
    	}
    	try {
			mediaPlayerBg.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	mediaPlayerBg.start();
    }
    /**
     * 停止播放背景音乐
     * @param view
     */
    public void stopBgMusic(View view){
    	mediaPlayerBg.stop();
    }
    
    
    /**
     * MediaPlayer特效1
     * @param view
     */
    public void playTeXiao1(View view){
    	if(mediaPlayerTeXiao1 != null) {
    		mediaPlayerTeXiao1.stop();
    	}
    	try {
    		mediaPlayerTeXiao1.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	mediaPlayerTeXiao1.start();
    }
    /**
     * MediaPlayer特效2
     * @param view
     */
    public void playTeXiao2(View view){
    	if(mediaPlayerTeXiao2 != null) {
    		mediaPlayerTeXiao2.stop();
    	}
    	try {
    		mediaPlayerTeXiao2.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	mediaPlayerTeXiao2.start();
    }
    /**
     * MediaPlayer特效2
     * @param view
     */
    public void playTeXiao3(View view){
    	if(mediaPlayerTeXiao3 != null) {
    		mediaPlayerTeXiao3.stop();
    	}
    	try {
    		mediaPlayerTeXiao3.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	mediaPlayerTeXiao3.start();
    }
    
    
    /**
     * SoundPool特效1
     * @param view
     */
    public void playSoundPoolTeXiao1(View view){
    	//参数1：播放特效加载后的ID值
    	//参数2：左声道音量大小(range = 0.0 to 1.0)
    	//参数3：右声道音量大小(range = 0.0 to 1.0)
    	//参数4：特效音乐播放的优先级，因为可以同时播放多个特效音乐
    	//参数5：是否循环播放，0只播放一次(0 = no loop, -1 = loop forever)
    	//参数6：特效音乐播放的速度，1F为正常播放，范围 0.5 到 2.0
    	soundPool.play(loadId1, 0.5f, 0.5f, 1, 0, 1f);
    	
    }
    /**
     * SoundPool特效2
     * @param view
     */
    public void playSoundPoolTeXiao2(View view){
    	soundPool.play(loadId2, 0.5f, 0.5f, 1, 0, 1f);
    	
    }
    /**
     * SoundPool特效3
     * @param view
     */
    public void playSoundPoolTeXiao3(View view){
    	soundPool.play(loadId3, 0.5f, 0.5f, 1, 0, 1f);
    	
    }
    
}