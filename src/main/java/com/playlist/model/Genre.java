package com.playlist.model;

/**
 * 프로그램에서 선택할 수 있는 노래 장르와 화면 표시명을 관리한다.
 */
public enum Genre {
    POP("팝송"),
    ROCK("락"),
    HIP_HOP("힙합"),
    RNB("R&B"),
    BALLAD("발라드"),
    JAZZ("재즈"),
    CLASSICAL("클래식"),
    EDM("EDM"),
    INDIE("인디"),
    K_POP("K-팝"),
    J_POP("J-팝");

    /** 메뉴와 노래 목록에서 사용자에게 보여줄 장르명이다. */
    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    /** 장르의 화면 표시명을 반환한다. */
    public String getDisplayName() {
        return displayName;
    }
}
