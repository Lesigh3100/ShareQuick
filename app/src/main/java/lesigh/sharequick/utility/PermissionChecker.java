package lesigh.sharequick.utility;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class PermissionChecker {
   private final int MY_PERMISSIONS_GRANTED = 1244;
   private Activity activity;

    public PermissionChecker(Activity activity) {
        this.activity = activity;
    }

    // a string array of all the required permissions
    private static final String[] appPermissions = {
            CAMERA, READ_EXTERNAL_STORAGE
    };

    // returns a string of all the permissions that still need to be granted
    private String[] allPermissions(String[] needed) {
        ArrayList<String> results = new ArrayList<>();
        for (String permission : needed) {
            if (!permissionGranted(permission)) {
                results.add(permission);
            }
        }
        return (results.toArray(new String[results.size()]));
    }

    // returns true if the single permission is granted
    private boolean permissionGranted(String permission) {
        return (ContextCompat.checkSelfPermission(activity, permission)) == PackageManager.PERMISSION_GRANTED;
    }

    // returns true if all permissions are granted
    public boolean allPermissionsGranted() {
        return (permissionGranted(READ_EXTERNAL_STORAGE) && permissionGranted(CAMERA));
    }

    // checks if every necessary permission is granted & asks for any that are not yet granted
    public void checkAllPermissions() {
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(activity, allPermissions(appPermissions), MY_PERMISSIONS_GRANTED);
        }
    }
}
