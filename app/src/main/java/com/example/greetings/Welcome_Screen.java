package com.example.greetings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Welcome_Screen<items, object> extends AppCompatActivity {
    private String key1 = "";
    public static final String key2 = "LIST";

    public static class Grocery {
        private String name;
        private String price;

        public Grocery(String name, String price) {
            this.name = name;
            this.price = price;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPrice() {
            return price;
        }
        public void setPrice(String price) {
            this.price = price;
        }
        public String toString() {
            return name+ " " +price;
        }
    }

    //Arraylist for the grocery data
    ArrayList<Grocery> items = new ArrayList<>();
    //ArrayList itemNew = new ArrayList();
    //ArrayList<String> itemNew = new ArrayList<>();



    //ArrayList<Grocery> items;
    ListView listView;
    //ArrayAdapter<Grocery> adapter;
    ArrayAdapter arrayAdapter;



    public void onCreate(View v) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_welcome_screen));
        Intent intent = getIntent();
        String message = intent.getStringExtra("USER_NAME");
        TextView resulttext = findViewById(R.id.resulttext);
        resulttext.setText("Hello, "+ message);


        //Adding Default Items to the list
        items.add(new Grocery("Milk","3.00"));
        items.add(new Grocery("Cereal", "4.50"));


        //Find view by id
        listView = findViewById(R.id.listview);
        //items = new ArrayList<>();
        //GroceryListAdapter adapter = new GroceryListAdapter(this, R.layout.activity_welcome_screen, items);
        arrayAdapter = new ArrayAdapter(Welcome_Screen.this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(Welcome_Screen.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override

                    //Function for removing an item
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove_item:
                                Toast.makeText(Welcome_Screen.this, "Item Removed", Toast.LENGTH_LONG).show();
                                items.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.shopping_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Function to add a new item to the grocery array list
            case R.id.add_item:
                //Function to add an item
                addItems();

                Toast.makeText(this,"Add an item to your shopping list",Toast.LENGTH_LONG).show();
                break;

                //Function to view all items in the grocery array list
            case R.id.view_item:

                Toast.makeText(this,"Your item list is displayed below",Toast.LENGTH_LONG).show();
                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    public void checkout(View v) {
        Intent i = new Intent(Welcome_Screen.this, checkout.class);
        startActivity(i);
    }

    //ADD ITEMS METHOD
    private void addItems() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Welcome_Screen.this);
        builder.setTitle("Add a new item");

        View v = LayoutInflater.from(Welcome_Screen.this).inflate(R.layout.activity_grocery_screen, null, false);
        //builder.setView(R.layout.activity_welcome_screen);
        builder.setView(v);

        EditText name = v.findViewById(R.id.nameID);
        EditText price = v.findViewById(R.id.priceID);

        String nameString = name.getText().toString();
        String priceString = price.getText().toString();
        //double priceDouble = Double.parseDouble(price.getText().toString());

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!name.getText().toString().isEmpty() && !price.getText().toString().isEmpty()) {
                    items.add(new Grocery(name.getText().toString().trim(), price.getText().toString()));
                    arrayAdapter.notifyDataSetChanged();
                }else {
                    name.setError("Make sure to enter a valid item name");
                    price.setError("Make sure to enter a valid item price");
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    //Toast function
    Toast t;

    private void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(this, s, Toast.LENGTH_LONG);
        t.show();
    }
}