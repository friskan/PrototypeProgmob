package ukdw.com.prototypeprogmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import android.app.ProgressDialog;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ukdw.com.prototypeprogmob.Model.DefaultResult;
import ukdw.com.prototypeprogmob.Network.GetDataService;
import ukdw.com.prototypeprogmob.Network.RetrofitClientInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ukdw.com.prototypeprogmob.Adapter.DosenAdapter;
import ukdw.com.prototypeprogmob.Model.Dosen;
public class RecyclerViewDosenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenArrayList;
    ProgressDialog progressDialog;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dosenArrayList.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data") {
            Intent intent = new Intent(RecyclerViewDosenActivity.this, CrudDosenActivity.class);
            intent.putExtra("id_dosen", dosen.getId());
            intent.putExtra("nidn", dosen.getNidn());
            intent.putExtra("nama", dosen.getNama());
            intent.putExtra("gelar", dosen.getGelar());
            intent.putExtra("email", dosen.getEmail());
            intent.putExtra("alamat", dosen.getAlamat());
            intent.putExtra("foto", dosen.getFoto());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(RecyclerViewDosenActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                    dosen.getId(), "72170157"
            );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewDosenActivity.this, "Berhasil Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RecyclerViewDosenActivity.this, RecyclerViewDosenActivity.class);
                    finish();
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewDosenActivity.this, "Gagal Menghapus Data Dosen", Toast.LENGTH_LONG).show();

                }
            });


        }

        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDosenActivity.this,CrudDosenActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170157");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();
                dosenArrayList = response.body();
                recyclerView = findViewById(R.id.recyclerView);
                dosenAdapter = new DosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new
                        LinearLayoutManager(RecyclerViewDosenActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDosenActivity.this, "Login Gagal, Coba Lagi", Toast.LENGTH_SHORT).show();

            }
        });

        registerForContextMenu(recyclerView);

        dosenAdapter = new DosenAdapter(dosenArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDosenActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dosenAdapter);
    }


        /*ImageButton imgButtonCrudDosen = (ImageButton) findViewById(R.id.imgButtonCrudDosen);
        imgButtonCrudDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecyclerViewDosenActivity.this,CrudDosenActivity.class);
                startActivity(i);
            }
        });*/

                //addData();
                /*recyclerView = findViewById(R.id.recyclerView);
        dosenAdapter = new DosenAdapter(dosenArrayList);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(RecyclerViewDosenActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dosenAdapter);
    }*/



    public void klikCrudDosen(View view)
    {
        Intent i = new Intent(RecyclerViewDosenActivity.this,CrudDosenActivity.class);
        startActivity(i);
    }
    /*private void addData(){
        dosenArrayList = new ArrayList<>();
        dosenArrayList.add(new Dosen("10211",
                "Katon Wijana", "Master of Computer Sains", "katon@email.com",
                "Jl. Mawar No. 11", ""));
        dosenArrayList.add(new Dosen("20134",
                "Jong Jek Siang", "Magister Sains", "jjs@email.com",
                "Jl. Melati No. 9", ""));
        dosenArrayList.add(new Dosen("40322",
                "Yetli Oslan", "Magister Ilmu Komputer", "yetli@email.com",
                "Jl. Teratai No. 2", ""));
        dosenArrayList.add(new Dosen("50451",
                "Budi Sutedjo", "Magister Manajemen Sistem Informasi", "budi@email.com",
                "Jl. Kamboja No. 10", ""));
        dosenArrayList.add(new Dosen("40333",
                "Umi Proboyekti", "Magister Ilmu Komputer", "umi@email.com",
                "Jl. Tulip No. 3", "")); */

    }


