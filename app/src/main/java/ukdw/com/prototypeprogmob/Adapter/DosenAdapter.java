package ukdw.com.prototypeprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import ukdw.com.prototypeprogmob.R;
import ukdw.com.prototypeprogmob.Model.Dosen;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder>{

    ArrayList<Dosen> dosenArrayList;

    public DosenAdapter(ArrayList<Dosen> dosenArrayList) {
        this.dosenArrayList = dosenArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNidn.setText(dosenArrayList.get(position).getNidn());
        holder.txtNama.setText(dosenArrayList.get(position).getNama());
        holder.txtGelar.setText(dosenArrayList.get(position).getGelar());
        holder.txtEmail.setText(dosenArrayList.get(position).getEmail());
        holder.txtAlamat.setText(dosenArrayList.get(position).getAlamat());
    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (dosenArrayList != null) ?dosenArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNidn, txtNama, txtGelar, txtEmail, txtAlamat;

        public ViewHolder(View view){
            super(view);
            txtNidn= view.findViewById(R.id.txt_kode_kelas);
            txtNama = view.findViewById(R.id.txt_matkul_kelas);
            txtGelar = view.findViewById(R.id.txt_hari_kelas);
            txtEmail = view.findViewById(R.id.txt_sesi_kelas);
            txtAlamat = view.findViewById(R.id.txt_dosen_kelas);
        }
    }
}
