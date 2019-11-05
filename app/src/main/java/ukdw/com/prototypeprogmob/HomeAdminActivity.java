package ukdw.com.prototypeprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        ImageButton daftarDosenImgButton = (ImageButton)findViewById(R.id.imgButtonDaftarDosen);
        daftarDosenImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdminActivity.this,RecyclerViewDosenActivity.class);
                startActivity(i);
            }
        });

        ImageButton dataDiriImgButton = (ImageButton)findViewById(R.id.imgButtonDataDiri);
        dataDiriImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdminActivity.this,CrudDosenActivity.class);
                startActivity(i);
            }
        });

        Button logoutButton = (Button)findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeAdminActivity.this);
                builder.setMessage("Apakah anda yakin akan keluar?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeAdminActivity.this, "Tidak jadi keluar",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(HomeAdminActivity.this,LoginActivity.class);
                                startActivity(i);
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ImageButton daftarMatkulImgButton = (ImageButton)findViewById(R.id.imgBtnDaftarMatkul);
        daftarMatkulImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdminActivity.this,RecyclerViewMatkulActivity.class);
                startActivity(i);
            }
        });

        ImageButton kelolaKrsImgButton = (ImageButton)findViewById(R.id.imgBtnKelolaKrs);
        kelolaKrsImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdminActivity.this,RecyclerViewKrsActivity.class);
                startActivity(i);
            }
        });

        ImageButton daftarMhsImgButton = (ImageButton)findViewById(R.id.imgButtonDaftarMhs);
        daftarMhsImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeAdminActivity.this,RecyclerViewMahasiswaActivity.class);
                startActivity(i);
            }
        });
    }
}
