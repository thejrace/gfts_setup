
/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */
import interfaces.ActionCallback;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequest;
import utils.Common;
import utils.FileDownload;

import java.io.File;

public class Setup extends ActionCommon{

    /**
     * Installation path.
     */
    private String installPath;

    /**
     * Constructor
     *
     * @param installPath
     */
    public Setup( String installPath ){
        this.installPath = installPath + "/" + Common.STATIC_LOCATION_SUFFIX + "/";
        System.out.println(installPath);
    }

    /**
     * Downloads and installs the application.
     *
     * @param callback
     */
    public void action( ActionCallback callback ){
        Thread thread = new Thread(() -> {

            setStatusProp("Yapılandırma dosyaları indiriliyor..");

            Common.createDirectory(installPath);

            JSONArray apiURLS = SharedConfig.DATA.getJSONArray("api_urls");
            for( int k = 0; k < apiURLS.length(); k++ ){
                try {
                    JSONObject setupData = new JSONObject(APIRequest.GET(apiURLS.getString(k)));
                    setStatusProp("Son versiyon indiriliyor..");
                    FileDownload.downloadFileFromUrl(setupData.getString("download_url"), installPath + "GFTS.exe", new ActionCallback() {
                        @Override
                        public void success(String msg) {
                            setStatusProp("Yeni versiyon indirildi!");
                        }

                        @Override
                        public void error(String msg) {
                            setStatusProp("Yeni versiyon indirilirken bir hata oluştu!");
                        }
                    });
                    setStatusProp("Yardımcı dosyalar indiriliyor..");
                    FileDownload.downloadFileFromUrl(setupData.getString("helper_download_url"), installPath + "fts_update_helper.jar", new ActionCallback() {
                        @Override
                        public void success(String msg) {
                            setStatusProp("Yardımcı dosyalar indirildi!");
                        }

                        @Override
                        public void error(String msg) {
                            setStatusProp("Yardımcı dosyalar indirilirken bir hata oluştu!");
                        }
                    });
                    setStatusProp("Yapılandırma dosyaları oluşturuluyor");

                    if( !Common.checkFile(  installPath + "settings.json"  ) ){
                        if( !Common.createFile(  installPath + "settings", setupData.getJSONObject("settings").toString() ) ){
                            setStatusProp("settings.json oluşturulamadı.");
                            callback.error("");
                            return;
                        }
                    }

                    if( !Common.checkFile(  installPath + "app_config.json"  ) ){
                        JSONObject appConfig = SharedConfig.DATA.getJSONObject("app_config");
                        appConfig.put("init", true);
                        appConfig.put("installDir", installPath);
                        appConfig.put("download_url", setupData.getString("download_url"));
                        if( !Common.createFile(  installPath + "app_config", appConfig.toString() ) ){
                            setStatusProp("app_config.json oluşturulamadı.");
                            callback.error("");
                            return;
                        }
                    }
                    setStatusProp("Kurum başarılı!");
                    callback.success("");
                    break;
                } catch( Exception e ){

                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
