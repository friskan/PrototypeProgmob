package ukdw.com.prototypeprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ukdw.com.prototypeprogmob.Model.Mahasiswa;
import ukdw.com.prototypeprogmob.R;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{

    ArrayList<Mahasiswa> mahasiswaArrayList;

    public MahasiswaAdapter(ArrayList<Mahasiswa> mahasiswaArrayList) {
        this.mahasiswaArrayList = mahasiswaArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_mahasiswa,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNim.setText(mahasiswaArrayList.get(position).getNim());
        holder.txtNamaMhs.setText(mahasiswaArrayList.get(position).getNamaMhs());
        holder.txtEmailMhs.setText(mahasiswaArrayList.get(position).getEmailMhs());
        holder.txtAlamatMhs.setText(mahasiswaArrayList.get(position).getAlamatMhs());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (mahasiswaArrayList != null) ?mahasiswaArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNim, txtNamaMhs, txtEmailMhs, txtAlamatMhs;

        public ViewHolder(View view){
            super(view);
            txtNim= view.findViewById(R.id.txt_nim);
            txtNamaMhs = view.findViewById(R.id.txt_nama_mhs);
            txtEmailMhs = view.findViewById(R.id.txt_email_mhs);
            txtAlamatMhs = view.findViewById(R.id.txt_alamat_mhs);
        }
    }
}
