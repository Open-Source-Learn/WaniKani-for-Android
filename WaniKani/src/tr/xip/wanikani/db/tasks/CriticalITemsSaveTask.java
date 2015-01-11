package tr.xip.wanikani.db.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import tr.xip.wanikani.api.response.CriticalItem;
import tr.xip.wanikani.api.response.UnlockItem;
import tr.xip.wanikani.db.DatabaseManager;
import tr.xip.wanikani.db.tasks.callbacks.CriticalItemsSaveTaskCallbacks;
import tr.xip.wanikani.db.tasks.callbacks.RecentUnlocksSaveTaskCallbacks;

/**
 * Created by Hikari on 1/7/15.
 */
public class CriticalItemsSaveTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    private List<CriticalItem> list;

    private CriticalItemsSaveTaskCallbacks mCallbacks;

    public CriticalItemsSaveTask(Context context, List<CriticalItem> list, CriticalItemsSaveTaskCallbacks callbacks) {
        this.context = context;
        this.mCallbacks = callbacks;
        this.list = list ;
    }

    public void executeSerial() {
        execute();
    }

    public void executeParallel() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected Void doInBackground(Void... params) {
        new DatabaseManager(context).saveCriticalItems(list);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (mCallbacks != null)
            mCallbacks.onCriticalItemssSaveTaskFinished();
    }
}