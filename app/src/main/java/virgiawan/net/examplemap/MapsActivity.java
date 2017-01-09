package virgiawan.net.examplemap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import static virgiawan.net.examplemap.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // variable GMap
    private GoogleMap mMap;

    /**
     * Untuk GMap yang terpenting :
     * 1. API Key untuk GMap (Bisa googling dengan keyword "get Gmap API key" atau "Gmap secret key")
     * - Bisa juga pake yg ada di sini. Key nya ada di res/value/google_maps_api.xml
     * 2. Fragment untuk menapung view dari map nya
     * 3. Harus implements OnMapReadyCallback
     * 4. Setelah itu pada onMapReady inisiasi variable dr Gmap untuk proses / manipulasi map kedepannya
     * 5. Di build gradle app, bagian defaultConfig tambahkan kode ini "multiDexEnabled true" (tanpa tanda petik)
     * **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inisiasi view
        setContentView(R.layout.activity_maps);

        // inisiasi fragment untuk menampung view dari gmap
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        // panggil gmap
        mapFragment.getMapAsync(this);
    }


    /*
    * Ketika GMap sudah terpanggil maka kode di dalam fungsi ini akan dieksekusi
    * */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // inisiasi variable GMap
        mMap = googleMap;


        // Example tempat lokalisasi sekitaran Jogja Semarang

        // inisiasi marker sarkem wkwkwk
        LatLng sarkem = new LatLng(-7.79009,110.3618989);
        // letakkan sarkem pada peta
        mMap.addMarker(new MarkerOptions().position(sarkem).title("Sarkem"));

        // inisiasi marker sembir wkwkwk
        LatLng sembir = new LatLng(-7.2906332,110.4928403);
        // letakkan sarkem pada peta
        mMap.addMarker(new MarkerOptions().position(sembir).title("Sembir"));

        // Buat boundari (jadi nanti map bakalan di zoom sampai marker kelihatan semua di layar)
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(sarkem);
        builder.include(sembir);

        // dibikin final biar bisa masuk scope setOnMapLoadedCallback
        final LatLngBounds bounds = builder.build();

        // pasang fokus kamera ke area bound
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                // bounds berasal dari baris 72, harus dibuat final
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
            }
        });
    }
}
