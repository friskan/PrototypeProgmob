package ukdw.com.prototypeprogmob.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.net.URL;

import ukdw.com.prototypeprogmob.R;
import ukdw.com.prototypeprogmob.Model.Dosen;
import com.squareup.picasso.Picasso;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder>{

    ArrayList<Dosen> dosenArrayList;
    Context context;

    public DosenAdapter(ArrayList<Dosen> dosenArrayList)
    {
        this.dosenArrayList = dosenArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen,parent,false);

        context = parent.getContext();
        return new DosenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNidn.setText(dosenArrayList.get(position).getNidn());
        holder.txtNama.setText(dosenArrayList.get(position).getNama());
        holder.txtGelar.setText(dosenArrayList.get(position).getGelar());
        holder.txtEmail.setText(dosenArrayList.get(position).getEmail());
        holder.txtAlamat.setText(dosenArrayList.get(position).getAlamat());
        //holder.imgViewDosen.setImageResource(dosenArrayList.get(position).getFoto());
        holder.imgViewDosen.getLayoutParams().width = 100;
        holder.imgViewDosen.getLayoutParams().height = 100;
        if(dosenArrayList.get(position).getFoto() !=null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/" + dosenArrayList.get(position).getFoto())
                    .into(holder.imgViewDosen);
        }

        holder.imgViewDosen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nama = dosenArrayList.get(position).getNama().toString();
                Toast.makeText(context, nama + " is selected", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() { //untuk menghitung jumlah data yang ada//
        return (dosenArrayList != null) ?dosenArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{
        private TextView txtNidn, txtNama, txtGelar, txtEmail, txtAlamat;
        ImageButton imgViewDosen;

        public ViewHolder(View view){
            super(view);
            txtNidn= view.findViewById(R.id.editTextNidn);
            txtNama = view.findViewById(R.id.editTextNama);
            txtGelar = view.findViewById(R.id.editTextGelar);
            txtEmail = view.findViewById(R.id.editTextEmail);
            txtAlamat = view.findViewById(R.id.editTextAlamat);
            imgViewDosen = view.findViewById(R.id.imgUploadDosen);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Dosen");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Dosen");
        }
    }
}
