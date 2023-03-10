package com.empresa.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.empresa.atmconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               enviarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
               R.id.nav_principal, R.id.nav_servico,R.id.nav_clientes,R.id.nav_contatos, R.id.nav_sobre)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void enviarEmail(){
        String celular = "tel:1199635286";
        String imagem = "https://img.freepik.com/fotos-gratis/praia-tropical_74190-188.jpg?w=2000";
        String endereco = "https://www.google.com.br/maps/@-15.8477658,-48.0506021,15z";
        String email = "";

//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(celular));
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(intent.EXTRA_EMAIL,new String[]{"atendimento@atmConsultoria.com.br"});
        intent.putExtra(intent.EXTRA_SUBJECT,"Contado pelo app");
        intent.putExtra(intent.EXTRA_TEXT,"Mensagem autom??tica");

        intent.setType("message/rfc822");
//        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"Compartilhar"));
    }
}