package lesigh.sharequick.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

public class SharingTool {

    public static void sharePicture(String filename, Context context){
        final String type = "image/*";
        Intent share = new Intent(Intent.ACTION_SEND);
        File media = new File(filename);
        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", media);
        share.setType(type);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.putExtra(Intent.EXTRA_STREAM, photoURI);

        context.startActivity(share);
    }

    public static void sharePost(String postTitle, String textToPost, String filename, Context context){
        final String type = "image/*";
        Intent share = new Intent(Intent.ACTION_SEND);
        File media = new File(filename);
        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", media);
        share.setType(type);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.putExtra(Intent.EXTRA_STREAM, photoURI);
        share.putExtra(Intent.EXTRA_SUBJECT, postTitle);
        share.putExtra(Intent.EXTRA_TEXT, textToPost);
        context.startActivity(share);

    }


}
