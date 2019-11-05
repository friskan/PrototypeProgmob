package ukdw.com.prototypeprogmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ukdw.com.prototypeprogmob.Adapter.MahasiswaAdapter;
import ukdw.com.prototypeprogmob.Model.Mahasiswa;

public class RecyclerViewMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_mhs);

        addData();

        recyclerView = findViewById(R.id.recyclerViewMhs);
        mahasiswaAdapter = new MahasiswaAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(RecyclerViewMahasiswaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    public void klikCrudMahasiswa(View view)
    {
        Intent i = new Intent(RecyclerViewMahasiswaActivity.this,CrudMahasiswaActivity.class);
        startActivity(i);
    }
    private void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("72170121",
                "Meyke Vanny Saptenno", "vanny@email.com", "Jl. Lavender No. 18"));
        mahasiswaArrayList.add(new Mahasiswa("72170145",
                "Elisa Novensiana", "elisa@email.com", "Jl. Lily No. 4"));
        mahasiswaArrayList.add(new Mahasiswa("72170157",
                "Friska F. Nainggolan", "friska@email.com", "Jl. Tulip No. 8"));
    }

}
