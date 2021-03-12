package com.example.room_app;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.room_app.database.Album;
import com.example.room_app.database.AlbumSong;
import com.example.room_app.database.MusicDao;
import com.example.room_app.database.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mAddBtn;
    private Button mGetBtn;
    private TextView textView;
    private MusicDao musicDao;

    // добавить базу данных Room ----
    // вставить данные / извлечь данные ---
    // добавить контент провайдер над Room ---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicDao = ((AppDelegate) getApplicationContext()).getMusicDatabase().getMusicDao();

        textView = findViewById(R.id.tv_main);

        mAddBtn = (findViewById(R.id.add));
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<AlbumSong> albumSongs = createAlbumSongs();
//                for (AlbumSong albumSong:albumSongs){
//                    musicDao.insertAlbumSong(albumSong);
//                }
                musicDao.insertAlbumSong(new AlbumSong(0,0,0));

                //musicDao.insertAlbumSongs(createAlbumSongs());
            }
        });

        mGetBtn = findViewById(R.id.get);
        mGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(musicDao.getAlbumSongs());
            }
        });

    }

    private List<Album> createAlbums() {
        int initSize = 3;
        List<Album> albums = new ArrayList<>(initSize);
        for (int i = 0; i < initSize; i++) {
            albums.add(new Album(i, "album " + i, "release" + System.currentTimeMillis()));
        }

        return albums;
    }

    private List<Song> createSongs() {
        int initSize = 9;
        List<Song> songs = new ArrayList<>(initSize);
        for (int i = 0; i < initSize; i++) {
            songs.add(new Song(i, "song " + i, "duration" + System.currentTimeMillis()));
        }

        return songs;
    }

    private List<AlbumSong> createAlbumSongs(){

        List<Album>albums = createAlbums();
        List<Song>songs = createSongs();

        musicDao.insertSongs(createSongs());
        musicDao.insertAlbums(createAlbums());

        int initSize = 3;
        List<AlbumSong> albumSongsList = new ArrayList<>(initSize);
        for (int i = 0; i < initSize; i++) {
            int start = i*3;
            int stop = (i+1)*3;
            for (int j = start; j<stop;j++){
                albumSongsList.add(new AlbumSong(j, albums.get(i).getId(), songs.get(j).getId()));
            }

        }
        return albumSongsList;
    }

    private void showToast(List<AlbumSong> albumSongs) {
        StringBuilder builder = new StringBuilder();
        for (AlbumSong albumSong : albumSongs) {
            builder.append(albumSong.toString()).append("\n");
        }

        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
        textView.setText(builder.toString());

    }
}
