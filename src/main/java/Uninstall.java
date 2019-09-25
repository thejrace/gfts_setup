/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import interfaces.ActionCallback;
import utils.Common;

import java.io.File;

public class Uninstall extends ActionCommon {
    /**
     * Uninstall the application
     *
     * @param staticDirPrefixVal
     * @param cb
     */
    public void action( String staticDirPrefixVal, ActionCallback cb ){
        final String staticDirPrefix = staticDirPrefixVal + "/"+ Common.STATIC_LOCATION_SUFFIX +"/";
        Thread uninstallThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if( Common.deleteDirectory( new File(staticDirPrefix)) ){
                    setStatusProp("Program kaldırıldı!");
                    cb.success("");
                } else {
                    setStatusProp("Program kaldırılken bir hata oluştu!");
                    cb.error("");
                }
            }
        });
        uninstallThread.setDaemon(true);
        uninstallThread.start();
    }
}
