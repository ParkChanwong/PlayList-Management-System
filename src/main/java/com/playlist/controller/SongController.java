package com.playlist.controller;

import com.playlist.model.Genre;
import com.playlist.model.Song;
import com.playlist.repository.SongRepository;

public class SongController {

    SongRepository songRepository = new SongRepository();

    public boolean addSong(String title, String artist, Genre genre) {
        boolean isSameSong = songRepository.findAll().stream().anyMatch(song -> song.getTitle().equals(title) && song.getArtist().equals(artist));

        if (isSameSong) {
            return false;
        }

        Song song = new Song(title, artist, genre);
        songRepository.save(song);

        return true;
    }

    public void searchSong() {
    }

    public void modifySong() {
    }

    public void deleteSong() {
    }
}
