package com.playlist.model;

/**
 * 노래 DTO다.
 * 노래에 관한 정보만 담는다.
 */
public class Song {
    private int id;
    private String title;
    private String artist;
    private Genre genre;

    // 노래의 기본 생성자
    public Song() {}

    // 노래의 매개변수 생성자
    public Song(String title, String artist, Genre genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    // Getter / Setter Start
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    // Getter / Setter End

    @Override
    public String toString() {
        return "Song [" +
                "id=" + id +
                ", title=" + title +
                ", artist=" + artist +
                ", genre=" + genre +
                "]";
    }
}
