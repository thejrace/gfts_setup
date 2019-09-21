
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
import utils.FileDownload;

public class Setup extends ActionCommon{

    private String installPath;

    public Setup( String installPath ){
        this.installPath = installPath;
    }

    public void action( ActionCallback callback ){
        Thread thread = new Thread(() -> {

            // check paths, create if not exist
            Thread setupThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    setStatusProp("Yapılandırma dosyaları indiriliyor..");
                    JSONArray apiURLS = SharedConfig.DATA.getJSONArray("api_urls");
                    for( int k = 0; k < apiURLS.length(); k++ ){
                        try {
                            // setup data contains;
                            // - helper download url
                            // - config json data
                            // - application download url
                            JSONObject setupData = new JSONObject(APIRequest.GET(apiURLS.getString(k)));

                            System.out.println(setupData);

                            break;
                        } catch( Exception e ){

                        }
                    }

                }
            });
            setupThread.setDaemon(true);
        });
        thread.setDaemon(true);
        thread.start();
    }
}
