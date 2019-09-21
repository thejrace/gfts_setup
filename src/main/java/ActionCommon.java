/*
 *  Gitas Fleet Tracking System Setup 2019
 *
 *  Contributors:
 *      - Ahmet Ziya Kanbur
 *
 */

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

public class ActionCommon {

    protected StringProperty mStatusProp = new SimpleStringProperty();

    public ObservableStringValue getStatusProp(){
        return mStatusProp;
    }

    protected void setStatusProp( String msg ){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mStatusProp.setValue(msg);
            }
        });
    }

}



