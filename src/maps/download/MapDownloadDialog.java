package maps.download;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.yourRoute.R;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 11/13/13
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapDownloadDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private Context context;
    private DialogInterface.OnClickListener listener;

    public MapDownloadDialog(Context context, DialogInterface.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle(R.string.load_map_title);
        builder.setMessage(R.string.load_map_body);

        builder.setPositiveButton(android.R.string.ok, listener);
        builder.setNegativeButton(android.R.string.cancel, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
       dialog.dismiss();
    }
}
