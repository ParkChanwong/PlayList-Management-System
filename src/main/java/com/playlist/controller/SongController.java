package com.playlist.controller;

import com.playlist.model.Genre;
import com.playlist.model.Song;
import com.playlist.repository.SongRepository;

import java.util.List;

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

    public List<Song> searchAllSong() { return songRepository.findAll(); }
    public List<Song> searchTitleSong(String title) { return songRepository.findByTitle(title); }
    public List<Song> searchArtistSong(String artist) {return songRepository.findByArtist(artist); }
    public List<Song> searchGenre(Genre genre) { return songRepository.findByGenre(genre); }
    public List<Song> searchById(int id) { return songRepository.findById(id); }

    public void modifySong() {
    }

    public boolean deleteSong(int id) {
        if (searchAllSong().stream().anyMatch(song -> song.getId() == id)) {
            songRepository.deleteSongById(id);
            return true;
        } else {
            return false;
        }
    }
}
