package com.example.stengel.dbtest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnLogin_onClick(View oView){

            TextView txtUsername;
            String sUsername;
            TextView txtPassword;
            String sPassword;
            boolean bUserFound = false;
            DBManager oDBManager = new DBManager(this);

            txtUsername = (TextView)findViewById(R.id.edTxtUserName);
            sUsername = txtUsername.getText().toString();
            txtPassword = (TextView)findViewById(R.id.edTxtPassword);
            sPassword = txtPassword.getText().toString();


            oDBManager.open();
            Cursor oDBCursor = oDBManager.getAllContacts();

            if(oDBCursor.moveToFirst()){
                do{
                    if(oDBCursor.getString(1).equalsIgnoreCase(sUsername)){

                        bUserFound = true;

                        if(oDBCursor.getString(2).equalsIgnoreCase(sPassword)){
                            Toast.makeText(this,"Successful login!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this,"Password is wrong!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }while(oDBCursor.moveToNext());

                if(!bUserFound){
                    Toast.makeText(this,"User not found!",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"No users in DB!",Toast.LENGTH_SHORT).show();
            }

            oDBManager.close();
        }

    // If creating a new account enable (make visible) icons and colors
    public void btnCreateAccount_onClick(View oView) {

        View bView = findViewById(R.id.hScrollView);
        View bView2 = findViewById(R.id.hScrollView2);
        View txtIcon = findViewById(R.id.textIcon);
        View txtColor = findViewById(R.id.textColor);
        View bCreate = findViewById(R.id.btnCreateNewAccount);
        View bCreateAccount = findViewById(R.id.btnCreateAccount);
        View bLoginbutton = findViewById(R.id.btnLogin);

        // -- set both to visible - possibly do same for Create Button and disable Create Account button
        bView.setVisibility(bView.VISIBLE);                 // Set icons scrollbar visible
        bView2.setVisibility(bView2.VISIBLE);               // Set Color scrollbar visible
        txtIcon.setVisibility(txtIcon.VISIBLE);             // Set Pick Icon text visible
        txtColor.setVisibility(txtColor.VISIBLE);           // Set Pick Color text visible
        bCreate.setVisibility(bCreate.VISIBLE);             // Set Create button visible
        bCreateAccount.setVisibility(bCreateAccount.GONE);  // Disabling the Create Account Button
        bLoginbutton.setVisibility(bLoginbutton.GONE);
        }

    // create account and add username, password, icon, and color.
    // will need to add icon and color options to database
    public void btnCreateNewAccount_onClick(View oView){
        DBManager oDBManager = new DBManager(this);

        oDBManager.open();
        TextView txtUsername = (TextView)findViewById(R.id.edTxtUserName);
        TextView txtPassword = (TextView)findViewById(R.id.edTxtPassword);
        //increase size of database and add icon and colors
        oDBManager.insertContact(txtUsername.getText().toString(), txtPassword.getText().toString());

        txtUsername.setText("");            // clear the username
        txtPassword.setText("");            // clear the password

        // make sure text fields are cleared now (username and password fields)
        oDBManager.close();

        View bView = findViewById(R.id.hScrollView);
        View bView2 = findViewById(R.id.hScrollView2);
        View txtIcon = findViewById(R.id.textIcon);
        View txtColor = findViewById(R.id.textColor);
        View bCreate = findViewById(R.id.btnCreateNewAccount);
        View bCreateAccount = findViewById(R.id.btnCreateAccount);
        View bLoginbutton = findViewById(R.id.btnLogin);

        // -- set both to visible - possibly do same for Create Button and disable Create Account button
        bView.setVisibility(bView.GONE);                 // Set icons scrollbar visible
        bView2.setVisibility(bView2.GONE);               // Set Color scrollbar visible
        txtIcon.setVisibility(txtIcon.GONE);             // Set Pick Icon text visible
        txtColor.setVisibility(txtColor.GONE);           // Set Pick Color text visible
        bCreate.setVisibility(bCreate.GONE);             // Set Create button visible
        bCreateAccount.setVisibility(bCreateAccount.VISIBLE);  // Disabling the Create Account Button
        bLoginbutton.setVisibility(bLoginbutton.VISIBLE);
    }
}//create account method
