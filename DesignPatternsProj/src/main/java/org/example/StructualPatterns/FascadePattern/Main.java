package org.example.StructualPatterns.FascadePattern;

// Subsystem: Audio Player
class AudioPlayer {
    public void playAudio(String audioFile) {
        System.out.println("Playing audio: " + audioFile);
    }
}

// Subsystem: Video Player
class VideoPlayer {
    public void playVideo(String videoFile) {
        System.out.println("Playing video: " + videoFile);
    }
}

// Subsystem: Subtitle Renderer
class SubtitleRenderer {
    public void renderSubtitle(String subtitleFile) {
        System.out.println("Rendering subtitle: " + subtitleFile);
    }
}

// Facade: Multimedia Player Facade
class MultimediaPlayerFacade {
    private AudioPlayer audioPlayer;
    private VideoPlayer videoPlayer;
    private SubtitleRenderer subtitleRenderer;

    public MultimediaPlayerFacade() {
        this.audioPlayer = new AudioPlayer();
        this.videoPlayer = new VideoPlayer();
        this.subtitleRenderer = new SubtitleRenderer();
    }

    public void playMedia(String audioFile, String videoFile, String subtitleFile) {
        audioPlayer.playAudio(audioFile);
        videoPlayer.playVideo(videoFile);
        subtitleRenderer.renderSubtitle(subtitleFile);
    }
}

public class Main {
    public static void main(String[] args) {
        // Client uses the Facade to play multimedia files
        MultimediaPlayerFacade playerFacade = new MultimediaPlayerFacade();
        playerFacade.playMedia("song.mp3", "movie.mp4", "subtitles.srt");
    }
}
