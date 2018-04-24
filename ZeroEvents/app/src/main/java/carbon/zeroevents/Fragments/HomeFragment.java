package carbon.zeroevents.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
<<<<<<< HEAD

import carbon.zeroevents.ListCards.DatabaseHelperOLD;
import carbon.zeroevents.ListCards.DetailActivity;
import carbon.zeroevents.ListCards.Item;
import carbon.zeroevents.ListCards.OnTapListener;
import carbon.zeroevents.ListCards.VocabularyAdapter;
import carbon.zeroevents.R;
=======
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import testerson.testy.fragment_and_tabtest.ListCards.CountDownCalculator2;
import testerson.testy.fragment_and_tabtest.ListCards.DatabaseHelperOLD;
import testerson.testy.fragment_and_tabtest.ListCards.DetailActivity;
import testerson.testy.fragment_and_tabtest.ListCards.GenericCallBack;
import testerson.testy.fragment_and_tabtest.ListCards.Item;
import testerson.testy.fragment_and_tabtest.ListCards.OnTapListener;
import testerson.testy.fragment_and_tabtest.ListCards.SetViewHolder;
import testerson.testy.fragment_and_tabtest.ListCards.VocabularyAdapter;
import testerson.testy.fragment_and_tabtest.R;
>>>>>>> 851002625edc4dd172df7d893d0aba6e3a70d2d2

/**
 * Created by Owner on 03/04/2018.
 */

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseHelperOLD databaseHelper;
    private ArrayList<Item> arrayList = new ArrayList<Item>();
    private Cursor cursor;
    private VocabularyAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.list_item, container, false);

<<<<<<< HEAD
        recyclerView = viewGroup.findViewById(R.id.recyclerview_id);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadDatabase();

        recyclerView.setAdapter(new VocabularyAdapter(getActivity(), arrayList));


        return viewGroup;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loadDatabase() {

        databaseHelper = new DatabaseHelperOLD(getActivity());

        try {
            databaseHelper.checkAndCopyDatabase();
            databaseHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        try {
            cursor = databaseHelper.QueryData("select * from FollowingMovies");

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        Item item = new Item();
                        item.setMovieTitle(cursor.getString(2));
                        item.setReleaseDateMillis(cursor.getString(4));
                        item.setMovieID(Integer.parseInt(cursor.getString(0)));

                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

//        adapter = new VocabularyAdapter(getActivity(), arrayList);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
    }

=======

        recyclerView = viewGroup.findViewById(R.id.recyclerview_id);
        loadDatabase();
        return viewGroup;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loadDatabase() {

        databaseHelper = new DatabaseHelperOLD(getActivity());

        try {
            databaseHelper.checkAndCopyDatabase();
            databaseHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        try {
            cursor = databaseHelper.QueryData("select * from FollowingMovies");

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        Item item = new Item();
                        item.setMovieTitle(cursor.getString(2));
                        item.setReleaseDateMillis(cursor.getString(4));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());


        adapter = new VocabularyAdapter(getActivity(), arrayList);
        adapter.setOnTapListener(new OnTapListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void OnTapView(int position) {
                Bundle bundle = new Bundle();

                Toast.makeText(getActivity(), "Click to " + position, Toast.LENGTH_SHORT).show();

                String movieTitle = arrayList.get(position).getMovieTitle();
                String dateMillis = arrayList.get(position).getReleaseDateMillis();


                Intent intent = new Intent(getActivity(), DetailActivity.class);
                bundle.putString("title", movieTitle);
                bundle.putString("dateMillis", dateMillis);

                intent.putExtras(bundle);

                startActivity(intent);


            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

>>>>>>> 851002625edc4dd172df7d893d0aba6e3a70d2d2
}
