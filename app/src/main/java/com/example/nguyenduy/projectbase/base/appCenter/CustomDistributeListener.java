package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.microsoft.appcenter.distribute.Distribute;
import com.microsoft.appcenter.distribute.DistributeListener;
import com.microsoft.appcenter.distribute.ReleaseDetails;
import com.microsoft.appcenter.distribute.UpdateAction;

public class CustomDistributeListener implements DistributeListener {

    private static final String TAG = MethodUtils.getTagClass(CustomDistributeListener.class);

    @Override
    public boolean onReleaseAvailable(Activity activity, ReleaseDetails releaseDetails) {
        Toast.makeText(activity, "AppCenter: có new Version", Toast.LENGTH_SHORT).show();
        LogUtils.i(TAG + "has new Version");
        String versionName = releaseDetails.getShortVersion();
        int versionCode = releaseDetails.getVersion();
        Uri releaseNotesUrl = releaseDetails.getReleaseNotesUrl();
        String releaseNotes = releaseDetails.getReleaseNotes();

        // Build our own dialog title and message
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setTitle("Version " + versionName + " available!");
        dialogBuilder.setMessage(releaseNotes);

        // Mimic default SDK buttons
        dialogBuilder.setPositiveButton(com.microsoft.appcenter.distribute.R.string.appcenter_distribute_update_dialog_download, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // This method is used to tell the SDK what button was clicked
                Distribute.notifyUpdateAction(UpdateAction.UPDATE);
            }
        });

        // We can postpone the release only if the update is not mandatory
        if (!releaseDetails.isMandatoryUpdate()) {
            dialogBuilder.setNegativeButton(com.microsoft.appcenter.distribute.R.string.appcenter_distribute_update_dialog_postpone, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // This method is used to tell the SDK what button was clicked
                    Distribute.notifyUpdateAction(UpdateAction.POSTPONE);
                }
            });
        }
        dialogBuilder.setCancelable(false); // if it's cancelable you should map cancel to postpone, but only for optional updates
        dialogBuilder.create().show();

        // Return true if you are using your own dialog, false otherwise
        return true;
    }
}
