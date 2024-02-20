package com.chiimboxFood.fraank.chimbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import static com.bumptech.glide.Glide.with;

public class MenuUsuario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        setTitle("Menu");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();




        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new FragmentPrimer()).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Si tienes alguna duda o sugerencia nos puedes contactar al correo chimboxfoodcintalapa@gmail.chimbox", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        ((TextView) header.findViewById(R.id.nombreusuario)).setText(user.getDisplayName());
        ((TextView) header.findViewById(R.id.correousuario)).setText(user.getEmail());
        Picasso.with(this)
                .load(user.getPhotoUrl())
                .resize(175, 175)
                .into((ImageView) header.findViewById(R.id.ImagenUsuario));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.nav_home) {
            //Fragmento Primero Home
            startActivity(new Intent(getApplicationContext(),MenuUsuario.class));
            startActivity(new Intent(getApplicationContext(),NewsActivity.class));
        } else if (id == R.id.nav_restaurant) {
            //Fragmento Segundo Restaurantes
            startActivity(new Intent(getApplicationContext(),MenuUsuario.class));
            startActivity(new Intent(getApplicationContext(),Restaurantes.class));
        } else if (id == R.id.nav_fondas) {
            //Fragmento Tercero Fondas
            startActivity(new Intent(getApplicationContext(),MenuUsuario.class));
            startActivity(new Intent(getApplicationContext(),Fondas.class));
        } else if (id == R.id.nav_recomendados) {
            //Fragmento Cuarto Más recomendados
            startActivity(new Intent(getApplicationContext(),MenuUsuario.class));
            startActivity(new Intent(getApplicationContext(),Recomendados.class));
        } else if (id == R.id.nav_quienes) {
            //Fragmento Quinto Quienes somos
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragmentQuinto()).commit();
        } else if (id == R.id.nav_myv) {
            //Fragmento Sexto MISIÓN Y VISIÓN
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragmentSexto()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}

