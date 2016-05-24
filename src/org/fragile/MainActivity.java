package org.fragile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    final Context context = this;
    public Random randomGenerator = new Random();
    public ArrayList<String> usersList = new ArrayList<String>();
    private ImageView img;
    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        usersList.add("emacraf");
        usersList.add("elukzbr");
        usersList.add("epilmic");
        usersList.add("emanied");
        usersList.add("eprzwic");
        usersList.add("epawtom");
        usersList.add("epawole");
        updateTextView(usersList.toString());
        img= (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.android);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.user_add: {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt_add, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder.setCancelable(false).setPositiveButton("Dodaj",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                addUser(userInput.getText().toString());
                            }
                        }).setNegativeButton("Anuluj",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                return true;
            }
            case R.id.user_remove: {
                //Toast.makeText(this, "Save is Selected", Toast.LENGTH_SHORT).show();
                
                final ArrayList<String> usersToRemove = new ArrayList<String>();
                final boolean bl[] = new boolean[usersList.size()];
                CharSequence[] cs = usersList.toArray(new CharSequence[usersList.size()]);
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt_remove, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setTitle("Wybierz uzytkownika : ");
                alertDialogBuilder.setMultiChoiceItems(cs, bl, new OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
                        if (isChecked) {
                            // If user select a item then add it in selected items
                            usersToRemove.add(usersList.get(whichButton));
                        } else if (usersToRemove.contains(usersList.get(whichButton))) {
                            // if the item is already selected then remove it
                            usersToRemove.remove(usersList.get(whichButton));
                        }
                    }

                }
                );
                alertDialogBuilder.setPositiveButton("Usun", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        for (int i = 0; i < usersToRemove.size(); i++) {
                            usersList.remove(usersToRemove.get(i));
                        }
                        updateTextView(usersList.toString());

                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(),
                                "You Have Cancel the Dialog box", Toast.LENGTH_LONG)
                                .show();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addUser(String user) {
        if (usersList.contains(user)) {
            Toast.makeText(this, "Takie uzytkownik juz istnieje", Toast.LENGTH_SHORT).show();
        } else {
            usersList.add(user);
            updateTextView(usersList.toString());
        }
    }

    public void removeUser(String user) {
        usersList.remove(user);
        updateTextView(usersList.toString());
    }

    public void losuj(View view) {
        String winner = "wszyscy";
        int index = randomGenerator.nextInt(usersList.size());
        winner = "Wylosowano: " + usersList.get(index).toString();

        TextView textView = (TextView) findViewById(R.id.winnerText);
        textView.setText(winner);
        
        img= (ImageView) findViewById(R.id.image);
        if(winner.contains("epawtom")){
            img.setImageResource(R.drawable.ptom);
        }else if(winner.contains("emacraf")){
            img.setImageResource(R.drawable.mraf);
        }else if(winner.contains("emanied")){
            img.setImageResource(R.drawable.mani);
        }else if(winner.contains("epawole")){
            img.setImageResource(R.drawable.pole);
        }else if(winner.contains("elukzbr")){
            img.setImageResource(R.drawable.luzb);
        }else if(winner.contains("eprzwic")){
            img.setImageResource(R.drawable.prwi);
        }else if(winner.contains("epilmic")){
            img.setImageResource(R.drawable.mcpi);
        }else{
            img.setImageResource(R.drawable.android);
        }
    }

    public void updateTextView(String toThis) {

        TextView textView = (TextView) findViewById(R.id.listUsers);
        textView.setText(toThis);         

    }

}
