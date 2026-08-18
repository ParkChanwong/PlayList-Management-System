package com.playlist.repository;

import com.playlist.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private final List<Song> songs = new ArrayList<>();

    public void save(Song song) {
        song.setId(songs.size() + 1);
        songs.add(song);
    }

    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }
}
