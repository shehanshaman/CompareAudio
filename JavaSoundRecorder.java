

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JPanel;


public class JavaSoundRecorder extends JPanel{

// record duration, in milliseconds

static final long RECORD_TIME = 12000; // 0.2 minute

// path of the wav file

File wavFile = new File("songs/test0.wav");

// format of audio file

AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

// the line from which audio data is captured

TargetDataLine line;

/**

* Defines an audio format

*/

AudioFormat getAudioFormat() {

float sampleRate = 16000;

int sampleSizeInBits = 8;

int channels = 2;

boolean signed = true;

boolean bigEndian = true;

AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,

channels, signed, bigEndian);

return format;

}

/**

* Captures the sound and record into a WAV file

*/

void start() {

try {

AudioFormat format = getAudioFormat();

DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

// checks if system supports the data line

if (!AudioSystem.isLineSupported(info)) {

System.out.println("Line not supported");

System.exit(0);

}

line = (TargetDataLine) AudioSystem.getLine(info);

line.open(format);

line.start(); // start capturing

System.out.println("Start capturing...");

AudioInputStream ais = new AudioInputStream(line);

System.out.println("Start recording...");

// start recording

AudioSystem.write(ais, fileType, wavFile);

} catch (LineUnavailableException ex) {

ex.printStackTrace();

} catch (IOException ioe) {

ioe.printStackTrace();

}

}


void finish() {

line.stop();

line.close();

System.out.println("Finished");

}

}