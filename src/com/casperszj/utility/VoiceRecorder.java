package com.casperszj.utility;

import java.io.IOException;
import java.util.UUID;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

//based on http://developer.android.com/guide/topics/media/audio-capture.html
public class VoiceRecorder {

	private MediaPlayer mPlayer = null;
	private MediaRecorder mRecorder = null;
	private String voiceNoteFileName;

	public VoiceRecorder(String voiceNoteFileName) {
		this.voiceNoteFileName = voiceNoteFileName;
	}

	// return file name path
	public static String createFileName(String fileName) {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/eventVoice" + fileName + UUID.randomUUID().toString()
				+ ".3gp";
	}

	public boolean startPlaying() {

		try {
			mPlayer = new MediaPlayer();
			mPlayer.setDataSource(voiceNoteFileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
	}

	public boolean startRecording() {

		try {
			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setOutputFile(voiceNoteFileName);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

			mRecorder.prepare();
		} catch (Exception e) {
			return false;
		}

		mRecorder.start();

		return true;
	}

	public void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

}