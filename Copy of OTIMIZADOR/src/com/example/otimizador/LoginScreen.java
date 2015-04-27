package com.example.otimizador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginScreen extends Activity {
	
	   private EditText  username;
	   private EditText  password;
	   private ImageButton login;
	   private ImageButton search;
	   private Login log1;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_login);
	      username = (EditText)findViewById(R.id.editLogin);
	      password = (EditText)findViewById(R.id.editPass);
	      login = (ImageButton)findViewById(R.id.buttonGo);
	      search = (ImageButton)findViewById(R.id.buttonSearch);
	   }

	   public void Login(View view)
	   {
		    String s = username.getText().toString();
			DBHelperLogin dbHelper = new DBHelperLogin(getApplicationContext());
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor c = db.query("login_valido", 
					   new String[]{"idLogin","name","email","password_2","facebook","registerDate"},
					   "name=?",new String[]{s}, null, null, null);
			if (c.getCount() > 0)
			{
				c.moveToFirst();
				log1.setIdLogin(c.getLong(0));
				log1.setName(c.getString(1));
				log1.setEmail(c.getString(2));
				log1.setPassword_2(c.getString(3));
				log1.setFacebook(c.getInt(4));
				log1.setRegisterDate(c.getString(5));
			}
			else
			{
				log1 = new Login();
				log1.setName(s);
				log1.setEmail("user@robotica.com");
				log1.setPassword_2("1234");
				log1.setFacebook(1231546);

			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			        Date date = new Date();

				log1.setRegisterDate(dateFormat.format(date));
				// Instancia o DAO para persistir o objeto
				LoginDAO mdao = new LoginDAO(getApplicationContext());
				// Salva o objeto no banco de dados
				mdao.salva(log1);
				Toast.makeText(getApplicationContext(), "Login Salvo! "+log1.getIdLogin(), Toast.LENGTH_SHORT).show();
			}
			c.close();
	      if(username.getText().toString().equals(log1.getName()) && password.getText().toString().equals(log1.getPassword_2()))
	      {
	    	  Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
	      }	
		  else
		  {
		      Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();	
		  }
	   }
	   public void Search(View view)
	   {
		   	Intent intent = new Intent(this, ListaLoginActivity.class);
			startActivity(intent);
			LoginScreen.this.finish();
	   }
	   public void Add(View view)
	   {
		   //
	   }
	   
	   public void showConfirmDialog(String acaoEscolhida)
	   {
		   final Dialog dialog = new Dialog(LoginScreen.this);
			dialog.setContentView(R.layout.confirm_dialog);
			dialog.setTitle("Confirmação");
			
			EditText acao = (EditText)dialog.findViewById(R.id.textAcao);
			Button confirmarPasso = (Button)dialog.findViewById(R.id.buttonConfirmar);
			Button cancelarPasso = (Button)dialog.findViewById(R.id.buttonCancelar);
			
			acao.setText(acaoEscolhida);
			confirmarPasso.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v)
				{
	                     dialog.dismiss();			 
						 Toast.makeText(getApplicationContext(), "Ação Confirmada!", Toast.LENGTH_SHORT).show();
				}
			});
			cancelarPasso.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					Toast.makeText(LoginScreen.this, "Ação Cancelada!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                   
				}
			});

			dialog.show();
			
	   }
	   
		 @Override
		 public boolean onCreateOptionsMenu (Menu menu)
		 {
			 getMenuInflater().inflate(R.menu.menu, menu);
			 return true ;
		 }
		
		 @Override
		public boolean onOptionsItemSelected(MenuItem item) 
		 {
			 if (item.getItemId() == R.id.LogOut) 
			 {
				 showConfirmDialog("Log Out");
				 //Intent intent = new Intent(this, Help.class);
				 //startActivity(intent);
				 LoginScreen.this.finish();
			 }
		 return super.onOptionsItemSelected(item);
		 }

	}