package com.example.mc.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mc.DataAdapterGoods;
import com.example.mc.DataAdapterGoods.OnNoteListener;
import com.example.mc.DatabaseHelper;
import com.example.mc.Good;
import com.example.mc.GoodActivity;
import com.example.mc.R;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnNoteListener {

    private AutoCompleteTextView search;
    private List<Good> goods = new ArrayList<>();
    private SQLiteDatabase db;
    private RecyclerView recyclerView;
    private Spinner sort_spinner;
    private String request;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        DatabaseHelper DBHelperMain = new DatabaseHelper(root.getContext());
        try {
            DBHelperMain.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        db = DBHelperMain.getWritableDatabase();

        sort_spinner = (Spinner) root.findViewById(R.id.sort);
        String selected_sort = sort_spinner.getSelectedItem().toString();
        request = " ORDER BY price";
        sort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                if(position == 1){
                    request = " ORDER BY name";
                } else {
                    request = " ORDER BY price";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                request = "";
            }

        });
        Button btn = (Button) root.findViewById(R.id.button);
        search = (AutoCompleteTextView) root.findViewById(R.id.autoCompleteTextView2);
        recyclerView = (RecyclerView) root.findViewById(R.id.list);
        DataAdapterGoods adapter_Goods = new DataAdapterGoods(root.getContext(), goods, this);

        root.findViewById(R.id.dummyfocus).setFocusableInTouchMode(true);
        root.findViewById(R.id.dummyfocus).requestFocus();

        Cursor cursor = db.rawQuery("SELECT * FROM goods"+request, null);
        cursor.moveToFirst();
        goods.clear();
        while (!cursor.isAfterLast()) {
            int resID;
            resID = getResources().getIdentifier(cursor.getString(5) , "drawable", "com.example.mc");
            goods.add(new Good(cursor.getString(0), cursor.getString(2),resID, "Good Bread", Double.toString(cursor.getDouble(3))+'\u20BD'));
            cursor.moveToNext();
        }

        final String[] goodsSearch = new String[goods.size()];
        for (int i = 0; i<goods.size(); i++){
            goodsSearch[i] = goods.get(i).getName();
        }
        cursor.close();
        search.setAdapter(new ArrayAdapter(root.getContext(), android.R.layout.select_dialog_item, goodsSearch));
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    goods.clear();
                    recyclerView.requestLayout();
                    String searchtext = search.getText().toString();
                    searchtext = '%'+searchtext+'%';
                    Cursor searchCursor = db.rawQuery("SELECT * FROM goods WHERE name LIKE \""+searchtext+"\" COLLATE NOCASE"+request, null);
                    searchCursor.moveToFirst();
                    while(!searchCursor.isAfterLast()){
                        int resID;
                        resID = getResources().getIdentifier(searchCursor.getString(5) , "drawable", "com.example.mc");
                        goods.add(new Good(searchCursor.getString(0),searchCursor.getString(2),resID, "Good Bread", Double.toString(searchCursor.getDouble(3))+'\u20BD'));
                        searchCursor.moveToNext();
                    }
                    recyclerView.setVisibility(View.GONE);
                    RecyclerView rv = (RecyclerView)root.findViewById(R.id.list2);
                    rv.setVisibility(View.VISIBLE);
                    final DataAdapterGoods adapter = new DataAdapterGoods(root.getContext(), goods, HomeFragment.this);
                    rv.setAdapter(adapter);
                    searchCursor.close();

                    handled = true;
                }
                return handled;
            }
        });
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goods.clear();
                recyclerView.requestLayout();
                if(search.hasFocus()){
                    String searchtext = search.getText().toString();
                    searchtext = '%'+searchtext+'%';
                    Cursor searchCursor = db.rawQuery("SELECT * FROM goods WHERE name LIKE \""+searchtext+"\" COLLATE NOCASE"+request, null);
                    searchCursor.moveToFirst();
                    while(!searchCursor.isAfterLast()){
                        int resID;
                        resID = getResources().getIdentifier(searchCursor.getString(5) , "drawable", "com.example.mc");
                        goods.add(new Good(searchCursor.getString(0),searchCursor.getString(2),resID, "Good Bread", searchCursor.getString(3)));
                        searchCursor.moveToNext();
                    }
                    recyclerView.setVisibility(View.GONE);
                    RecyclerView rv = (RecyclerView)root.findViewById(R.id.list2);
                    rv.setVisibility(View.VISIBLE);
                    final DataAdapterGoods adapter;
                    adapter = new DataAdapterGoods(root.getContext(), goods, HomeFragment.this);
                    rv.setAdapter(adapter);
                    searchCursor.close();

                } else {
                    search.requestFocus();
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }


            }
        });

        recyclerView.setAdapter(adapter_Goods);


        Button btnFilter = (Button)root.findViewById(R.id.buttonFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout filter = (LinearLayout)root.findViewById(R.id.filter);
                if (filter.getVisibility() == View.VISIBLE) filter.setVisibility(View.INVISIBLE);
                else filter.setVisibility(View.VISIBLE);
            }
        });




        return root;
    }

    @Override
    public void onNoteClick(int position) {
        goods.get(position);
        Intent intent = new Intent(this.getActivity().getApplication(), GoodActivity.class);
        intent.putExtra("good_name", goods.get(position).getName());
        intent.putExtra("good_company", goods.get(position).getCompany());
        intent.putExtra("good_description", goods.get(position).getDescription());
        intent.putExtra("good_image", goods.get(position).getImage());
        intent.putExtra("good_price", goods.get(position).getPrice());
        startActivity(intent);
    }
}