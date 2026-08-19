package com.playlist.controller;

import com.playlist.model.Genre;
import com.playlist.model.Song;
import com.playlist.repository.SongRepository;

import java.util.List;

public class SongController {

    SongRepository songRepository = new SongRepository();

    public void addSong(String title, String artist, Genre genre) {
        Song song = new Song(title, artist, genre);
        songRepository.save(song);
    }

    public List<Song> searchAllSong() { return songRepository.findAll(); }
    public List<Song> searchTitleSong(String title) { return songRepository.findByTitle(title); }
    public List<Song> searchArtistSong(String artist) {return songRepository.findByArtist(artist); }
    public List<Song> searchGenre(Genre genre) { return songRepository.findByGenre(genre); }
    public List<Song> searchById(int id) { return songRepository.findById(id); }

    public void deleteSong(int id) { songRepository.deleteSongById(id); }

    public void updateSong(int id, String title, String artist, Genre genre) { songRepository.updateSongById(id, title, artist, genre); }
}
