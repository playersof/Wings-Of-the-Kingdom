package it.unicam.cs.mdpg.rpg129029.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * MediaPlayer per soundtrack gioco, l'audio appena termina si ripete all'infinito in loop.
 */

public class MusicaSottofondo {
    private static MediaPlayer mediaPlayer;

    public static void avvia() {
        String path = MusicaSottofondo.class.getResource("/audio/musica_sottofondo.mp3").toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5); // volume da 0.0 a 1.0
        mediaPlayer.play();
    }

    public static void ferma() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
