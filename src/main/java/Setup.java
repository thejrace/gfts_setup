
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

public class Setup extends ActionCommon{

    private String installPath;

    public Setup( String installPath ){
        this.installPath = installPath + "gfts/";
    }

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

                        }

                        @Override
                        public void error(String msg) {

                        }
                    });

                    setStatusProp("Yardımcı dosyalar indiriliyor..");
                    FileDownload.downloadFileFromUrl(setupData.getString("helper_download_url"), installPath + "gfts_update_helper.jar", new ActionCallback() {
                        @Override
                        public void success(String msg) {

                        }

                        @Override
                        public void error(String msg) {

                        }
                    });

                    setStatusProp("Yapılandırma dosyaları oluşturuluyor");
                    if( !Common.checkFile(  installPath + "/gfts/app_config.json"  ) ){
                        if( !Common.createFile(  "api_user", "{ \"init\" : true }" ) ){
                            setStatusProp("app_config.json oluşturulamadı.");
                            callback.error("");
                            return;
                        }
                    }

                    break;
                } catch( Exception e ){

                }
            }

        });
        thread.setDaemon(true);
        thread.start();
    }
}
