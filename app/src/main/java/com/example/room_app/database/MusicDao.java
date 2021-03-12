package com.example.room_app.database;

import android.database.Cursor;
import androidx.room.*;

import java.util.List;

@Dao
public interface MusicDao {

    /*
        albums
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbum(Album albums);

    @Query("select * from album")
    List<Album> getAlbums();

    @Query("select * from album")
    Cursor getAlbumsCursor();

    @Query("select * from album where id = :albumId")
    Cursor getAlbumWithIdCursor(int albumId);

    @Delete
    void deleteAlbum(Album album);

    @Query("DELETE FROM album where id = :albumId")
    int deleteAlbumById(int albumId);

    @Update
    int updateAlbumInfo(Album album);

    /*
        songs
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Update
    int updateSong(Song song);

    @Insert
    void insertSong(Song song);

    @Delete
    int deleteSong(Song song);

    @Query("delete from song where song.id =:songId")
    int deleteSongById(int songId);

    @Query("select * from song")
    List<Song> getSongs();

    @Query("select * from song")
    Cursor getSongsCursor();

    @Query("select * from song where song.id = :songId")
    Cursor getSongWithIdCursor(int songId);

    @Query("select * from song inner join albumsong on song.id = albumsong.song_id where album_id = :albumId")
    List<Song> getSongsFromAlbum(int albumId);

    /*
        albums_songs
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLinksAlbumSongs(List<AlbumSong> linksAlbumSongs);

    @Query("delete from albumsong where album_id = :albumId")
    int deleteLinksAlbumSongsByAlbumId(int albumId);

    @Insert
    void insertAlbumSong(AlbumSong albumSong);

    @Query("select * from albumsong")
    Cursor getAlbumSongsCursor();

    @Query("select * from albumsong")
    List<AlbumSong> getAlbumSongs();

    @Query("delete from albumsong where id =:id")
    int deleteAlbumSong(int id);

}

