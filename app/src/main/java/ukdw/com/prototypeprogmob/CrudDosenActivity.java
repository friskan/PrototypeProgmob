package ukdw.com.prototypeprogmob;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import ukdw.com.prototypeprogmob.Model.DefaultResult;
import ukdw.com.prototypeprogmob.Model.Dosen;
import ukdw.com.prototypeprogmob.Network.GetDataService;
import ukdw.com.prototypeprogmob.Network.RetrofitClientInstance;
import ukdw.com.prototypeprogmob.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class CrudDosenActivity extends AppCompatActivity {

    EditText editNama, editNidn, editEmail, editGelar, editAlamat, foto;
    String image;
    GetDataService service;
    ProgressDialog progressDialog;
    ImageView imgViewDosen;

    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;//permission codenya 58
    private boolean isUpdate = false;
    private String idDosen = "";
    private String stringImg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);
        this.setTitle("KRS SI - Halo Admin");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_ACCESS_REQUEST_CODE);
        }

        editNama = (EditText) findViewById(R.id.editTextNama);
        editNidn = (EditText) findViewById(R.id.editTextNidn);
        editAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editGelar = (EditText) findViewById(R.id.editTextGelar);
        foto = (EditText) findViewById(R.id.editTextFoto);
        imgViewDosen = findViewById(R.id.imgUploadDosen);

        Button uploadFotoDosen = findViewById(R.id.uploadDosenButton);
        uploadFotoDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        progressDialog = new ProgressDialog(this);

        checkUpdate();
        Button simpanButton = (Button) findViewById(R.id.simpanButton);
        if (isUpdate) {
            simpanButton.setText("Update");
        }

        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);
                builder.setMessage("Anda ingin simpan data?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CrudDosenActivity.this, "Tidak jadi simpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                if (!isUpdate) {
                                    progressDialog.setMessage("Send Data");
                                    progressDialog.show();

                                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                                    Call<DefaultResult> call = service.insert_foto_dosen(
                                            editNidn.getText().toString(),
                                            editNama.getText().toString(),
                                            editGelar.getText().toString(),
                                            editEmail.getText().toString(),
                                            editAlamat.getText().toString(),
                                            /*foto.getText().toString()*/
                                            stringImg,
                                            "72170157");
                                    call.enqueue(new Callback<ukdw.com.prototypeprogmob.Model.DefaultResult>() {
                                        @Override
                                        public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                            progressDialog.dismiss();
                                            Toast.makeText(CrudDosenActivity.this, "Data Tersimpan",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(CrudDosenActivity.this, RecyclerViewDosenActivity.class);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultResult> call, Throwable t) {
                                            Toast.makeText(CrudDosenActivity.this, "Gagal Simpan",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    progressDialog.setMessage("Send Data");
                                    progressDialog.show();

                                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                                    Call<DefaultResult> call = service.update_foto_dosen(
                                            idDosen,
                                            editNidn.getText().toString(),
                                            editNama.getText().toString(),
                                            editGelar.getText().toString(),
                                            editEmail.getText().toString(),
                                            editAlamat.getText().toString(),
                                            /*foto.getText().toString()*/
                                            stringImg,
                                            "72170101");
                                    call.enqueue(new Callback<ukdw.com.prototypeprogmob.Model.DefaultResult>() {
                                        @Override
                                        public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                                            progressDialog.dismiss();
                                            Toast.makeText(CrudDosenActivity.this, "Data Terubah",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(CrudDosenActivity.this, RecyclerViewDosenActivity.class);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultResult> call, Throwable t) {
                                            Toast.makeText(CrudDosenActivity.this, "Gagal Terubah",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                                /*Intent i = new Intent(CrudDosenActivity.this,HomeAdminActivity.class);
                                startActivity(i);*/
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    void checkUpdate() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        //get data via the key
        isUpdate = extras.getBoolean("is_update");
        idDosen = extras.getString("id_dosen");
        editNama.setText(extras.getString("nama"));
        editNidn.setText(extras.getString("nidn"));
        editAlamat.setText(extras.getString("alamat"));
        editEmail.setText(extras.getString("email"));
        editGelar.setText(extras.getString("gelar"));
        foto.setText(extras.getString("foto"));
        stringImg = extras.getString("foto");
        Picasso.with(CrudDosenActivity.this).load("https://kpsi.fti.ukdw.ac.id/progmob/%7B%7Bfotoanda%7D%7D%22" + extras.getString("foto"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case FILE_ACCESS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    //PERMISSION_GRANTED
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    imgViewDosen.setImageURI(selectedImage);
                    //proses konversi
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            /*mendapatkan realpath*/   filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodeableString = cursor.getString(columnIndex);
                    foto.setText(imgDecodeableString);
                    cursor.close();
                    //convert ke bitmap, lalu array, lalu stringnya pakai base64
                    Bitmap bm = BitmapFactory.decodeFile(imgDecodeableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();

                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;
            }
    }
}



       /* private void requestUpdateDosen(){
        editId = (EditText)findViewById(R.id.editTextIdDosen);
        editNama = (EditText)findViewById(R.id.editTextNama);
        editNidn = (EditText)findViewById(R.id.editTextNidn);
        editAlamat = (EditText)findViewById(R.id.editTextAlamat);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        editGelar = (EditText)findViewById(R.id.editTextGelar);

        editId.setText(mIntent.getStringExtra("id"));
        editId.setTag(editId.getKeyListener());
        editId.setKeyListener(null);
        editNama.setText(mIntent.getStringExtra("nama"));
        editNidn.setText(mIntent.getStringExtra("nidn"));
        editAlamat.setText(mIntent.getStringExtra("alamat"));
        editEmail.setText(mIntent.getStringExtra("email"));
        editGelar.setText(mIntent.getStringExtra("gelar"));

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog =  ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<Dosen> call =  service.update_dosen(editId.getText().toString(),editNama.getText().toString(),editNidn.getText().toString(),
                editAlamat.getText().toString(),editEmail.getText().toString(),editGelar.getText().toString(),"https://picsum.photos/id/1005/5760/3840",
                "72170119");
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                progressDialog.dismiss();
                Toast.makeText(CrudDosenActivity.this,"Berhasil Update",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CrudDosenActivity.this, RecyclerViewDosenActivity.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudDosenActivity.this,"Error ",Toast.LENGTH_SHORT);
            }
        });
    }
}*/
